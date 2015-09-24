package com.ss.task.service;

import com.ss.task.dao.*;
import com.ss.task.dto.TaskInfo;
import com.ss.task.model.*;
import com.ss.task.util.ErrorInfo;
import com.ss.task.util.Util;
import com.ss.webutil.dao.BaseDAO;
import com.ss.webutil.struct.BaseResult;
import com.ss.webutil.struct.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liymm on 2015-03-23.
 */
@Service
public class TaskService {

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    TaskUserDAO taskUserDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    ReplyDAO replyDAO;

    @Autowired
    TaskTypeDAO taskTypeDAO;

    public ResultModel<TaskEntity> addTask(Integer uid, TaskEntity task, List<TaskUserEntity> members) {

        UserEntity leader = userDAO.load(uid);
        if (leader == null)
            return new ResultModel<TaskEntity>(ErrorInfo.INVALID_USER);

        TaskTypeEntity taskType = taskTypeDAO.load(task.getType().getId());

        TaskEntity t = new TaskEntity();

        t.setTitle(task.getTitle());
        t.setLeader(leader);
        t.setContent(task.getContent());
        t.setStartDate(task.getStartDate());
        t.setEndDate(task.getEndDate());
        t.setPubDate(Util.getCurrentDate());
        t.setStatus(TaskEntity.TASK_PUB);
        t.setType(taskType);

        taskDAO.save(t);

        for (TaskUserEntity member : members) {
            UserEntity u = userDAO.load(member.getUser().getId());
            if (u == null)
                continue;

            TaskUserEntity tu = new TaskUserEntity();
            tu.setUser(u);
            tu.setDetail(member.getDetail());
            tu.setTask(t);
            tu.setStatus(TaskUserEntity.NOT_SUBMIT);
            tu.setComment(null);
            taskUserDAO.save(tu);
        }

        return new ResultModel(t);
    }

    /* 所有 */
    public ResultModel<List<TaskEntity>> getAllPubTasks(Integer offset, Integer length) {
        return new ResultModel(taskDAO.findByPropertyByOffsetWithOrder("status", TaskEntity.TASK_PUB, offset, length, "id", BaseDAO.DESC));
    }

    public ResultModel<Long> getCountOfAllPubTasks() {
        return new ResultModel(taskDAO.getTotalCountByProperty("status", TaskEntity.TASK_PUB));
    }

    public ResultModel<List<TaskEntity>> getAllFinishedTasks(Integer offset, Integer length) {
        return new ResultModel(taskDAO.findByPropertyByOffsetWithOrder("status", TaskEntity.TASK_FINISHED, offset, length, "id", BaseDAO.DESC));
    }

    public ResultModel<Long> getCountOfAllFinishedTasks() {
        return new ResultModel(taskDAO.getTotalCountByProperty("status", TaskEntity.TASK_FINISHED));
    }

    public ResultModel<List<TaskEntity>> getAllEvaluateTasks(Integer offset, Integer length) {
        return new ResultModel(taskDAO.findByPropertyByOffsetWithOrder("status", TaskEntity.TASK_EVALUATE, offset, length, "id", BaseDAO.DESC));
    }

    public ResultModel<Long> getCountOfAllEvaluateTasks() {
        return new ResultModel(taskDAO.getTotalCountByProperty("status", TaskEntity.TASK_EVALUATE));
    }

    /* 我发布 */
    public ResultModel<List<TaskEntity>> getAllPubTasksByPubUser(Integer uid, Integer offset, Integer length) {
        return new ResultModel(taskDAO.findByPropertiesByOffsetWithOrder(
                new String[]{"status", "leader.id"}, new Integer[]{TaskEntity.TASK_PUB, uid}, offset, length, "id", BaseDAO.DESC));
    }

    public ResultModel<Long> getCountOfAllPubTasksByPubUser(Integer uid) {
        return new ResultModel(taskDAO.getTotalCountByProperties(new String[]{"status", "leader.id"}, new Integer[]{TaskEntity.TASK_PUB, uid}));
    }

    public ResultModel<List<TaskEntity>> getAllFinishedTasksByPubUser(Integer uid, Integer offset, Integer length) {
        return new ResultModel(taskDAO.findByPropertiesByOffsetWithOrder(
                new String[]{"status", "leader.id"}, new Integer[]{TaskEntity.TASK_FINISHED, uid}, offset, length, "id", BaseDAO.DESC));
    }

    public ResultModel<Long> getCountOfAllFinishedTasksByPubUser(Integer uid) {
        return new ResultModel(taskDAO.getTotalCountByProperties(new String[]{"status", "leader.id"}, new Integer[]{TaskEntity.TASK_FINISHED, uid}));
    }

    public ResultModel<List<TaskEntity>> getAllEvaluateTasksByPubUser(Integer uid, Integer offset, Integer length) {
        return new ResultModel(taskDAO.findByPropertiesByOffsetWithOrder(
                new String[]{"status", "leader.id"}, new Integer[]{TaskEntity.TASK_EVALUATE, uid}, offset, length, "id", BaseDAO.DESC));
    }

    public ResultModel<Long> getCountOfAllEvaluateTasksByPubUser(Integer uid) {
        return new ResultModel(taskDAO.getTotalCountByProperties(new String[]{"status", "leader.id"}, new Integer[]{TaskEntity.TASK_EVALUATE, uid}));
    }

    /* 我执行 */
    public ResultModel<List<TaskUserEntity>> getAllPubTasksByMember(Integer uid, Integer offset, Integer length) {
        return new ResultModel(taskUserDAO.findByPropertiesByOffsetWithOrder(
                new String[]{"task.status", "user.id"}, new Integer[]{TaskEntity.TASK_PUB, uid}, offset, length, "id", BaseDAO.DESC));
    }

    public ResultModel<Long> getCountOfAllPubTasksByMember(Integer uid) {
        return new ResultModel(taskUserDAO.getTotalCountByProperties(new String[]{"task.status", "user.id"}, new Integer[]{TaskEntity.TASK_PUB, uid}));
    }

    public ResultModel<List<TaskUserEntity>> getAllFinishedTasksByMember(Integer uid, Integer offset, Integer length) {
        return new ResultModel(taskUserDAO.findByPropertiesByOffsetWithOrder(
                new String[]{"task.status", "user.id"}, new Integer[]{TaskEntity.TASK_FINISHED, uid}, offset, length, "id", BaseDAO.DESC));
    }

    public ResultModel<Long> getCountOfAllFinishedTasksByMember(Integer uid) {
        return new ResultModel(taskUserDAO.getTotalCountByProperties(new String[]{"task.status", "user.id"}, new Integer[]{TaskEntity.TASK_FINISHED, uid}));
    }

    public ResultModel<List<TaskUserEntity>> getAllEvaluateTasksByMember(Integer uid, Integer offset, Integer length) {
        return new ResultModel(taskUserDAO.findByPropertiesByOffsetWithOrder(
                new String[]{"task.status", "user.id"}, new Integer[]{TaskEntity.TASK_EVALUATE, uid}, offset, length, "id", BaseDAO.DESC));
    }

    public ResultModel<Long> getCountOfAllEvaluateTasksByMember(Integer uid) {
        return new ResultModel(taskUserDAO.getTotalCountByProperties(new String[]{"task.status", "user.id"}, new Integer[]{TaskEntity.TASK_EVALUATE, uid}));
    }

    public ResultModel<List<TaskUserEntity>> getAllTaskUserByTask(Integer tid) {
        return new ResultModel<>(taskUserDAO.findByProperty("task.id", tid));
    }

    public ResultModel<TaskInfo> getTask(Integer id) {
        TaskInfo task = new TaskInfo();
        task.setTask(taskDAO.load(id));
        task.setMembers(taskUserDAO.findByProperty("task.id", id));
        return new ResultModel(task);
    }

    public ResultModel<ReplyEntity> replyTask(ReplyEntity reply) {

        ReplyEntity r = new ReplyEntity();
        r.setContent(reply.getContent());

        UserEntity user = userDAO.load(reply.getUser().getId());
        if (user == null)
            return new ResultModel<>(ErrorInfo.INVALID_USER);

        r.setUser(user);

        TaskEntity task = taskDAO.load(reply.getTask().getId());
        if (task == null)
            return new ResultModel<>(ErrorInfo.INVALID_PARAMETER);

        r.setTask(task);

        if (reply.getReplyuser() != null) {
            UserEntity ru = userDAO.load(reply.getReplyuser().getId());
            if (ru != null) {
                r.setReplyuser(ru);
            }
        }

        r.setPubtime(new Date());

        replyDAO.save(r);

        return new ResultModel(r);
    }

    public ResultModel<List<ReplyEntity>> getAllRepliesByTask(Integer tid, Integer offset, Integer length) {
        List<ReplyEntity> l = replyDAO.findByPropertyByOffsetWithOrder("task.id", tid, offset, length, "id", BaseDAO.DESC);
        return new ResultModel(l);
    }

    public ResultModel<Long> getCountOfRepliesByTask(Integer tid) {
        Long l = replyDAO.getTotalCountByProperty("task.id", tid);
        return new ResultModel(l);
    }

    public BaseResult submit(Integer uid, Integer tid) {
        List<TaskUserEntity> l = taskUserDAO.findByProperties(new String[]{"user.id", "task.id"}, new Integer[]{uid,tid});
        for (TaskUserEntity tu : l) {
            tu.setStatus(TaskUserEntity.SUBMIT);
            taskUserDAO.update(tu);
        }
        return new BaseResult();
    }

    public BaseResult commit(Integer tuid, String report) {
        TaskUserEntity tu = taskUserDAO.load(tuid);
        if (tu == null) {
            return new BaseResult(ErrorInfo.INVALID_TASK);
        }

        tu.setReport(report);
        tu.setStatus(TaskUserEntity.SUBMIT);
        tu.setSubdate(new java.sql.Date(System.currentTimeMillis()));
        taskUserDAO.update(tu);

        /* 检查是否所有的人员都已经提交任务，如果提交则将任务的状态变为已完成*/
        checkTaskFinished(tu.getTask().getId());

        return new BaseResult();
    }

    boolean checkTaskFinished(Integer tid) {
        TaskEntity task = taskDAO.load(tid);
        if (task.getStatus() != TaskEntity.TASK_PUB)
            return false;

        List<TaskUserEntity> l = taskUserDAO.findByProperty("task.id", tid);

        for (TaskUserEntity tu : l) {
            if (tu.getStatus() != TaskUserEntity.SUBMIT)
                return false;
        }

        task.setStatus(TaskEntity.TASK_FINISHED);
        taskDAO.update(task);
        return true;
    }

    public BaseResult evaluate(Integer tuid, String comment) {
        TaskUserEntity tu = taskUserDAO.load(tuid);
        if (tu == null) {
            return new BaseResult(ErrorInfo.INVALID_TASK);
        }

        tu.setComment(comment);
        tu.setEvadate(new java.sql.Date(System.currentTimeMillis()));
        tu.setStatus(TaskUserEntity.EVALUATE);
        taskUserDAO.update(tu);

        /* 检查是否所有的人员都已经提交任务，如果提交则将任务的状态变为已完成*/
        checkTaskEvaluate(tu.getTask().getId());

        return new BaseResult();
    }

    boolean checkTaskEvaluate(Integer tid) {
        TaskEntity task = taskDAO.load(tid);
        if (task.getStatus() != TaskEntity.TASK_FINISHED)
            return false;

        List<TaskUserEntity> l = taskUserDAO.findByProperty("task.id", tid);

        for (TaskUserEntity tu : l) {
            if (tu.getStatus() != TaskUserEntity.EVALUATE)
                return false;
        }

        task.setStatus(TaskEntity.TASK_EVALUATE);
        taskDAO.update(task);
        return true;
    }

    public TaskUserEntity loadTaskUser(Integer id) {
        return taskUserDAO.load(id);
    }

    public ResultModel<List<TaskEntity>> getTasksByType(Integer type, Integer offset, Integer length) {
        return new ResultModel<>(taskDAO.findByPropertyByOffsetWithOrder("type.id", type, offset, length, "id", BaseDAO.DESC));
    }
}
