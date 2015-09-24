package com.ss.task.rest;

import com.ss.task.dto.EvaluationList;
import com.ss.task.model.EvaluationEntity;
import com.ss.task.model.EvaluationReportEntity;
import com.ss.task.model.UserEntity;
import com.ss.task.service.EvaluationService;
import com.ss.task.util.BaseController;
import com.ss.webutil.struct.BaseResult;
import com.ss.webutil.struct.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiYm on 2015/7/22.
 */
@RestController
@RequestMapping("/rest/evaluation/")
public class EvaluationController extends BaseController {

    @Autowired
    EvaluationService evaluationService;

    @RequestMapping("mark")
    public BaseResult markScore(EvaluationList list, HttpSession session) {

        UserEntity evaUser = getUser(session);
        return evaluationService.markScore(list.getData(), evaUser.getId());
    }

    @RequestMapping("publish/week")
    public BaseResult evaWeekPublish(String date) {
        return evaluationService.generateWeekReport(Date.valueOf(date));
    }

    @RequestMapping("publish/month")
    public BaseResult evaMonthPublish(String date) {
        return evaluationService.generateMonthReport(Date.valueOf(date));
    }

    @RequestMapping("list")
    public ResultModel<List<EvaluationReportEntity>>  list(Integer offset, Integer length) {
        return evaluationService.getReportList(offset, length);
    }

    @RequestMapping("list/count")
    public ResultModel<Long> countOfList() {
        return evaluationService.countOfReportList();
    }
}
