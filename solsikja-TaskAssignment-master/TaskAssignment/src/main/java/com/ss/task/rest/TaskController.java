package com.ss.task.rest;

import com.ss.task.dto.TaskInfo;
import com.ss.task.dto.TaskUsers;
import com.ss.task.model.ReplyEntity;
import com.ss.task.model.TaskEntity;
import com.ss.task.model.TaskUserEntity;
import com.ss.task.model.UserEntity;
import com.ss.task.service.TaskService;
import com.ss.task.util.BaseController;
import com.ss.task.util.Util;
import com.ss.webutil.struct.BaseResult;
import com.ss.webutil.struct.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by liymm on 2015-03-23.
 */
@RestController
@RequestMapping("/rest/task/")
public class TaskController extends BaseController {

    @Autowired
    TaskService taskService;


    @RequestMapping("add")
    public ResultModel<TaskEntity> addTask(TaskEntity task, String sd, String ed, TaskUsers tu, HttpSession session) {

        UserEntity user = getUser(session);

        task.setStartDate(Util.parseDate(sd));
        task.setEndDate(Util.parseDate(ed));

        return taskService.addTask(user.getId(), task, tu.getTaskusers());
    }

    @RequestMapping("list/pub")
    public ResultModel<List<TaskEntity>> listPubTasks(Integer offset, Integer length) {
        return taskService.getAllPubTasks(offset, length);
    }

    @RequestMapping("list/pub/count")
    public ResultModel<Long> countPubTasks() {
        return taskService.getCountOfAllPubTasks();
    }

    @RequestMapping("list/finished")
    public ResultModel<List<TaskEntity>> listFinishedTasks(Integer offset, Integer length) {
        return taskService.getAllFinishedTasks(offset, length);
    }

    @RequestMapping("list/finished/count")
    public ResultModel<Long> countFinishedTasks() {
        return taskService.getCountOfAllFinishedTasks();
    }

    @RequestMapping("list/evaluate")
    public ResultModel<List<TaskEntity>> listEvaluateTasks(Integer offset, Integer length) {
        return taskService.getAllEvaluateTasks(offset, length);
    }

    @RequestMapping("list/evaluate/count")
    public ResultModel<Long> countEvaluateTasks() {
        return taskService.getCountOfAllEvaluateTasks();
    }

    @RequestMapping("{id}")
    public ResultModel<TaskInfo> getTask(@PathVariable Integer id) {
        return taskService.getTask(id);
    }

    @RequestMapping("list/my/pub")
    public ResultModel<List<TaskEntity>> listPubTasksByPubUser(Integer offset, Integer length, HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getAllPubTasksByPubUser(user.getId(), offset, length);
    }

    @RequestMapping("list/my/pub/count")
    public ResultModel<Long> countPubTasksByPubUser(HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getCountOfAllPubTasksByPubUser(user.getId());
    }

    @RequestMapping("list/my/finished")
    public ResultModel<List<TaskEntity>> listFinishedTasksByPubUser(Integer offset, Integer length, HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getAllFinishedTasksByPubUser(user.getId(), offset, length);
    }

    @RequestMapping("list/my/finished/count")
    public ResultModel<Long> countFinishedTasksByPubUser(HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getCountOfAllFinishedTasksByPubUser(user.getId());
    }

    @RequestMapping("list/my/evaluate")
    public ResultModel<List<TaskEntity>> listEvaluateTasksByPubUser(Integer offset, Integer length, HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getAllEvaluateTasksByPubUser(user.getId(), offset, length);
    }

    @RequestMapping("list/my/evaluate/count")
    public ResultModel<Long> countEvaluateTasksByPubUser(HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getCountOfAllEvaluateTasksByPubUser(user.getId());
    }

    @RequestMapping("list/member/pub")
    public ResultModel<List<TaskUserEntity>> listPubTasksByMember(Integer offset, Integer length, HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getAllPubTasksByMember(user.getId(), offset, length);
    }

    @RequestMapping("list/member/pub/count")
    public ResultModel<Long> countPubTasksByMember(HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getCountOfAllPubTasksByMember(user.getId());
    }

    @RequestMapping("list/member/finished")
    public ResultModel<List<TaskUserEntity>> listFinishedTasksByMember(Integer offset, Integer length, HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getAllFinishedTasksByMember(user.getId(), offset, length);
    }

    @RequestMapping("list/member/finished/count")
    public ResultModel<Long> countFinishedTasksByMember(HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getCountOfAllFinishedTasksByMember(user.getId());
    }

    @RequestMapping("list/member/evaluate")
    public ResultModel<List<TaskUserEntity>> listEvaluateTasksByMember(Integer offset, Integer length, HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getAllEvaluateTasksByMember(user.getId(), offset, length);
    }

    @RequestMapping("list/member/evaluate/count")
    public ResultModel<Long> countEvaluateTasksByMember(HttpSession session) {
        UserEntity user = getUser(session);
        return taskService.getCountOfAllEvaluateTasksByMember(user.getId());
    }

    @RequestMapping("{id}/reply/list")
    public ResultModel<List<ReplyEntity>> listReplyByTask(@PathVariable Integer id, Integer offset, Integer length) {
        return taskService.getAllRepliesByTask(id, offset, length);
    }

    @RequestMapping("{id}/reply/list/count")
    public ResultModel<Long> countReplyByTask(@PathVariable Integer id) {
        return taskService.getCountOfRepliesByTask(id);
    }

    @RequestMapping("reply")
    public ResultModel<ReplyEntity> replyTask(ReplyEntity reply, HttpSession session) {
        UserEntity user = getUser(session);
        reply.setUser(user);
        return taskService.replyTask(reply);
    }

    @RequestMapping("commit")
    public BaseResult commitTask(Integer tuid, String report) {
        return taskService.commit(tuid, report);
    }

    @RequestMapping("evaluate")
    public BaseResult evaluateTask(Integer tuid, String comment) {
        return taskService.evaluate(tuid, comment);
    }

    @RequestMapping("{id}/taskuser/list")
    public ResultModel<List<TaskUserEntity>> listTaskUser(@PathVariable Integer id) {
        return taskService.getAllTaskUserByTask(id);
    }

    @RequestMapping("taskuser/{id}")
    public ResultModel<TaskUserEntity> getTaskUser(@PathVariable Integer id) {
        return new ResultModel<>(taskService.loadTaskUser(id));
    }

//    @RequestMapping("submit")
//    public BaseResult submitTask(Integer tid, HttpSession session) {
//        UserEntity user = getUser(session);
//        return taskService.submit(user.getId(), tid);
//    }
}