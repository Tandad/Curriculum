package com.ss.task.service;

import com.ss.task.dao.*;
import com.ss.task.dto.EvaStatInfo;
import com.ss.task.dto.UserEvaStatInfo;
import com.ss.task.model.*;
import com.ss.task.util.ErrorInfo;
import com.ss.task.util.Util;
import com.ss.webutil.dao.BaseDAO;
import com.ss.webutil.struct.BaseResult;
import com.ss.webutil.struct.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

/**
 * Created by LiYm on 2015/7/23.
 */
@Service
public class EvaluationService {

    @Autowired
    EvaluationDAO evaluationDAO;
    @Autowired
    EvaTypeDAO evaTypeDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    EvaluationReportDAO evaluationReportDAO;
    @Autowired
    EvaluationDetailDAO evaluationDetailDAO;

    public ResultModel<Date> isMarkedThisWeek(Integer euid) {
        Date lastDate = evaluationDAO.getLastEvaluationDate(euid);

        Calendar today = Calendar.getInstance();
        today.setFirstDayOfWeek(Calendar.MONDAY);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        today.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date monday = new Date(today.getTimeInMillis());

        today.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        Date friday = new Date(today.getTimeInMillis());

        today.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date sunday = new Date(today.getTimeInMillis());

        System.out.println("lastDate :" + lastDate);
        System.out.println("Monday :" + monday);
        System.out.println("Friday :" + friday);
        System.out.println("Sunday :" + sunday);

        Date curr = Util.getCurrentDate();
        if (curr.after(friday)) {
            if (lastDate == null) {
                return new ResultModel<>();
            } else if (lastDate.before(monday)) {
                return new ResultModel<>();
            } else {
                return new ResultModel<Date>(ErrorInfo.HAS_EVALUATED).setData(lastDate);
            }
        } else {
            return new ResultModel<Date>(ErrorInfo.EVALUATE_NOT_START).setData(lastDate);
        }
    }

    public BaseResult markScore(List<EvaluationEntity> l, Integer euid) {

        UserEntity evaUser = userDAO.load(euid);
        if (evaUser == null) {
            return new BaseResult(ErrorInfo.INVALID_USER);
        }

        HashMap<Integer, EvaTypeEntity> typeMap = new HashMap<>();
        List<EvaTypeEntity> types = evaTypeDAO.getAllList();
        for (EvaTypeEntity et : types) {
            typeMap.put(et.getId(), et);
        }

        Date date = Util.getCurrentDate();

        for (EvaluationEntity eva : l) {

            EvaluationEntity e = new EvaluationEntity();

            UserEntity user = userDAO.load(eva.getUser().getId());
            if (user == null) {
                return new BaseResult(ErrorInfo.INVALID_USER);
            }

            e.setUser(user);
            e.setEvauser(evaUser);
            e.setComplete(eva.getComplete());
            e.setScore(eva.getScore());
            e.setType(typeMap.get(eva.getType().getId()));
            e.setEvadate(date);

            evaluationDAO.save(e);
        }

        return new BaseResult();
    }

    public ResultModel<List<EvaluationEntity>> getEvaListByEvaUserByDate(Integer uid, Date evadate) {
        return new ResultModel<>(evaluationDAO.getEvaListByEvaUserByDate(uid, evadate));
    }

    public List<EvaluationEntity> getWeekResult(java.sql.Date start, java.sql.Date end) {
        return evaluationDAO.getLeaderEvaListByWeek(start, end);
    }

    public List<UserEvaStatInfo> getMonthResult(java.sql.Date start, java.sql.Date end) {
        HashMap<Integer, UserEvaStatInfo> map = new HashMap<>();
        List<EvaStatInfo> l = evaluationDAO.getEvaStatistics(start, end);

        for (EvaStatInfo es : l) {
            UserEvaStatInfo ui = map.get(es.getUser().getId());
            if (ui == null) {
                ui = new UserEvaStatInfo();
                ui.setUser(es.getUser());
                ui.setTotal(0);
            }

            switch (es.getType().getId().intValue()) {
                case EvaTypeEntity.LEADER:
                    ui.setLeader(es.getScore());
                    ui.setCountOfLeader(es.getCount());
                    break;
                case EvaTypeEntity.OTHER_LEADER:
                    ui.setOtherLeader(es.getScore());
                    ui.setCountOfOtherLeader(es.getCount());
                    break;
                case EvaTypeEntity.OTHER_DEPT:
                    ui.setOtherDept(es.getScore());
                    ui.setCountOfOtherDept(es.getCount());
                    break;
            }

            ui.setTotal(ui.getTotal() + es.getWeightScore());

            map.put(ui.getUser().getId(), ui);
        }

        /* 周考核出现未完成任务的员工列表 */
        List<UserEntity> uncompleteUserList = evaluationDAO.getLeaderUncompleteUserListByDate(start, end);

        for (UserEntity user : uncompleteUserList) {
            List<EvaluationEntity> el = evaluationDAO.getLeaderEvaListByUserByDate(user.getId(), start, end);
            if (el.size() == 0)
                continue;

            /* 如果最后一次考核未完成任务，得分为均值 */
            EvaluationEntity e = el.get(0);
            if (e.getComplete() == EvaluationEntity.UNCOMPLETE)
                continue;

            UserEvaStatInfo ui = map.get(user.getId());
            if (ui == null)
                continue;

            /* 最后一次考核完成任务，得分为最后一次周考核的得分 */
            ui.setLeader(e.getScore());
            map.put(ui.getUser().getId(), ui);
        }

        List<UserEvaStatInfo> uesl = new ArrayList<>(map.values());
        Collections.sort(uesl);

//        System.out.println(uesl);

        return uesl;
    }

    public List<Date[]> getNotReportWeek() {
        Date last = evaluationReportDAO.getLastWeekReportDate();

        if (last == null)
            last = new Date(2015 - 1900, 6, 11);

        Calendar l = new GregorianCalendar();
        l.setTime(last);
        l.add(Calendar.DATE, Calendar.DAY_OF_WEEK);
        l.setTime(Util.getFirstDayOfWeek(l.getTimeInMillis())); /* 已有考核周的下一周周一 */

        Calendar c = Calendar.getInstance();
        c.setTime(Util.getLastDayOfWeek(c.getTimeInMillis())); /* 本周日 */

        List<Date[]> dateList = new ArrayList<>();
        while(l.before(c)) {
            Date[] d = new Date[2];
            d[0] = Util.getFirstDayOfWeek(l.getTimeInMillis());
            d[1] = Util.getLastDayOfWeek(l.getTimeInMillis());
            dateList.add(d);
            l.add(Calendar.DATE, Calendar.DAY_OF_WEEK);
        }

        return dateList;
    }

    public BaseResult generateWeekReport(Date date) {
        Date start = Util.getFirstDayOfWeek(date.getTime()); /* Monday */
        Date end = Util.getLastDayOfWeek(date.getTime());   /* Sunday */

        List<EvaluationEntity> list = evaluationDAO.getLeaderEvaListByWeek(start, end);

        EvaluationReportEntity report = new EvaluationReportEntity();
        report.setStartDate(start);
        report.setEndDate(end);
        report.setType(EvaluationReportEntity.WEEK);
        evaluationReportDAO.save(report);

        for (EvaluationEntity e : list) {
            EvaluationDetailEntity detail = new EvaluationDetailEntity();
            detail.setUser(e.getUser());
            detail.setLeader(e.getScore().doubleValue());
            detail.setTotal(e.getScore().doubleValue());
            detail.setReport(report);
            evaluationDetailDAO.save(detail);
        }

        return new BaseResult();
    }

    public List<Date[]> getNotReportMonth() {
        Date last = evaluationReportDAO.getLastMonthReportDate();

        if (last == null)
            last = new Date(2015 - 1900, 5, 11);

        Calendar l = new GregorianCalendar();
        l.setTime(last);
        l.add(Calendar.MONTH, 1);
        l.setTime(Util.getLastDayOfMonth(l.getTimeInMillis())); /* First Day Of the next Month */

        Calendar c = Calendar.getInstance();
        c.setTime(Util.getLastDayOfMonth(c.getTimeInMillis())); /* First Day Of this Month */

        List<Date[]> dateList = new ArrayList<>();

        while (c.after(l)) {

            Date[] d = new Date[2];
            d[0] = Util.getFirstDayOfMonth(l.getTimeInMillis());
            d[1] = Util.getLastDayOfMonth(l.getTimeInMillis());

            dateList.add(d);
            l.add(Calendar.MONTH, 1);
            l.setTime(Util.getLastDayOfMonth(l.getTimeInMillis()));
        }

        return dateList;
    }

    public BaseResult generateMonthReport(Date date) {
        Date start = Util.getFirstDayOfMonth(date.getTime());   /* First Day Of Month */
        Date end = Util.getLastDayOfMonth(date.getTime());      /* Last Day Of Month */

        System.out.println("start : " + start);
        System.out.println("end : " + end);

        List<UserEvaStatInfo> list = getMonthResult(start, end);

        EvaluationReportEntity report = new EvaluationReportEntity();
        report.setStartDate(start);
        report.setEndDate(end);
        report.setType(EvaluationReportEntity.MONTH);
        evaluationReportDAO.save(report);

        for (UserEvaStatInfo u : list) {
            EvaluationDetailEntity detail = new EvaluationDetailEntity();
            detail.setUser(u.getUser());
            detail.setLeader(u.getLeader());
            detail.setOtherLeader(u.getOtherLeader());
            detail.setOtherDept(u.getOtherDept());
            detail.setTotal(u.getTotal());
            detail.setReport(report);
            evaluationDetailDAO.save(detail);
        }

        return new BaseResult();
    }

    public ResultModel<List<EvaluationReportEntity>> getReportList(Integer offset, Integer length) {
        return new ResultModel<>(evaluationReportDAO.getAllListByOffsetWithOrder(offset, length, "id", BaseDAO.DESC));
    }

    public ResultModel<Long> countOfReportList() {
        return new ResultModel<>(evaluationDAO.getTotalCount());
    }

    public EvaluationReportEntity loadReport(Integer id) {
        return evaluationReportDAO.load(id);
    }

    public List<EvaluationDetailEntity> getEvaDetails(Integer rid) {
        return evaluationDetailDAO.listByReport(rid);
    }
}
