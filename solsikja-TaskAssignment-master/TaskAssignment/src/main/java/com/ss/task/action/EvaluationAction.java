package com.ss.task.action;

import com.ss.task.action.auth.UserAuthPassport;
import com.ss.task.dto.EvaluateUserInfo;
import com.ss.task.model.EvaluationReportEntity;
import com.ss.task.model.UserEntity;
import com.ss.task.service.EvaluationService;
import com.ss.task.service.UserService;
import com.ss.task.util.Util;
import com.ss.webutil.struct.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by LiYm on 2015/7/14.
 */
@Controller
@RequestMapping("evaluation")
@UserAuthPassport
public class EvaluationAction {

    @Autowired
    UserService userService;
    @Autowired
    EvaluationService evaluationService;

    @RequestMapping("mark")
    public String leaderMarkPage(HttpSession session, ModelMap modelMap) {

        UserEntity user = (UserEntity)session.getAttribute("user");

        ResultModel<java.sql.Date> isMarked = evaluationService.isMarkedThisWeek(user.getId());

        if (isMarked.failed()) {
            modelMap.addAttribute("info", isMarked.getInfo());

            java.sql.Date date = isMarked.getData();

            if (date != null) {
                modelMap.addAttribute("list", evaluationService.getEvaListByEvaUserByDate(user.getId(), date).getData());
                modelMap.addAttribute("date", date);
            }

            return "evaluation/markerror";
        } else {
            ResultModel<EvaluateUserInfo> r = userService.getEvaluateUserList(user.getId());

            modelMap.addAttribute("underling", r.getData().getUnderling());

            /* 判断是不是本月的最后一周，如果是则进行其他领导评分和其他部门评分 */
            Calendar thisWeekend = Calendar.getInstance();
            thisWeekend.setFirstDayOfWeek(Calendar.MONDAY);
            thisWeekend.set(Calendar.HOUR_OF_DAY, 0);
            thisWeekend.set(Calendar.MINUTE, 0);
            thisWeekend.set(Calendar.SECOND, 0);
            thisWeekend.set(Calendar.MILLISECOND, 0);
            thisWeekend.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

            Calendar nextWeekend = (Calendar)thisWeekend.clone();
            nextWeekend.add(Calendar.DATE, Calendar.DAY_OF_WEEK);

            /* 本月最后一周 */
            if (nextWeekend.get(Calendar.MONTH) != thisWeekend.get(Calendar.MONTH)) {
                modelMap.addAttribute("otherUnderling", r.getData().getOtherUnderling());
                modelMap.addAttribute("colleague", r.getData().getColleague());
            }

            return "evaluation/mark";
        }
    }

    @RequestMapping("preview/week")
    public String weekPreviewPage(String date, ModelMap modelMap) {

        List<Date[]> dateList = evaluationService.getNotReportWeek();

        System.out.println(dateList);

        java.sql.Date start = null, end = null;

        if (date == null) {
            if (dateList.size() != 0) {
                start = dateList.get(0)[0];
                end = dateList.get(0)[1];
            } else {
                long curr = System.currentTimeMillis();
                start = Util.getFirstDayOfWeek(curr);
                end = Util.getLastDayOfWeek(curr);
            }
        } else {
            start = Util.getFirstDayOfWeek(java.sql.Date.valueOf(date).getTime());
            end = Util.getLastDayOfWeek(start.getTime());
        }

        if (start != null) {
            modelMap.addAttribute("enable", new java.util.Date().after(end));
            modelMap.addAttribute("start", start);
            modelMap.addAttribute("end", end);
            modelMap.addAttribute("datelist", dateList);
            modelMap.addAttribute("list", evaluationService.getWeekResult(start, end));
        }

        return "evaluation/preview/week";
    }

    @RequestMapping("preview/month")
    public String monthPreviewPage(String date, ModelMap modelMap) {
        List<Date[]> dateList = evaluationService.getNotReportMonth();
        java.sql.Date start, end = null;

        System.out.println(dateList);

        if (date == null) {
            if (dateList.size() != 0) {
                end = dateList.get(0)[1];
                start = dateList.get(0)[0];
            } else {
                long curr = System.currentTimeMillis();
                end = Util.getLastDayOfMonth(curr);
                start = Util.getFirstDayOfMonth(curr);
            }
        } else {
            end = Util.getLastDayOfMonth(java.sql.Date.valueOf(date).getTime());
            start = Util.getFirstDayOfMonth(end.getTime());
        }

        if (end != null) {
            modelMap.addAttribute("enable", new java.util.Date().after(end));
            modelMap.addAttribute("start", start);
            modelMap.addAttribute("end", end);
            modelMap.addAttribute("datelist", dateList);
            modelMap.addAttribute("list", evaluationService.getMonthResult(start, end));
        }

        return "evaluation/preview/month";
    }

    @RequestMapping("report")
    public String reportPage(Integer id, ModelMap modelMap) {

        EvaluationReportEntity report = evaluationService.loadReport(id);

        modelMap.addAttribute("report", report);
        modelMap.addAttribute("list", evaluationService.getEvaDetails(id));

        if (report.getType() == EvaluationReportEntity.WEEK)
            return "evaluation/report/week";
        else
            return "evaluation/report/month";
    }

    @RequestMapping("list")
    public String listPage() {
        return "evaluation/list";
    }
}
