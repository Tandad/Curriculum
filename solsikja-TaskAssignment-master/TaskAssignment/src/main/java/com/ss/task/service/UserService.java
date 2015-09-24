package com.ss.task.service;

import com.ss.task.dao.DepartmentDAO;
import com.ss.task.dao.LeaderDAO;
import com.ss.task.dao.UserDAO;
import com.ss.task.dto.EvaluateUserInfo;
import com.ss.task.model.DepartmentEntity;
import com.ss.task.model.LeaderEntity;
import com.ss.task.model.UserEntity;
import com.ss.task.util.ErrorInfo;
import com.ss.webutil.struct.BaseResult;
import com.ss.webutil.struct.ResultModel;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liymm on 2015-03-23.
 */
@Service
public class UserService {

    static final String DEFAULT_PWD = "123456";

    @Autowired
    UserDAO userDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    LeaderDAO leaderDAO;

    public ResultModel<UserEntity> login(String name, String password) {
        List<UserEntity> l = userDAO.findByProperties(new String[]{"name", "password"}, new String[]{name, password});
        if (l.size() == 0)
            return new ResultModel<>(ErrorInfo.INVALIE_NAME_OR_PASSWORD);

        return new ResultModel<>(l.get(0));
    }

    public ResultModel<UserEntity> addUser(String name, String realname, Integer dept, String title, Integer admin) {
        UserEntity u = new UserEntity();

        List<UserEntity> l = userDAO.findByProperty("name", name);
        if (l.size() > 0) {
            return new ResultModel<>(ErrorInfo.DUPLICATE_USER_NAME);
        }

        if (dept != null) {
            DepartmentEntity department = departmentDAO.load(dept);
            if (department == null)
                return new ResultModel<>(ErrorInfo.INVALID_DEPT);

            u.setDepartment(department);
        }

        u.setName(name);
        u.setRealname(realname);
        u.setPassword(DEFAULT_PWD);
        u.setTitle(title);
        u.setAdmin(admin);

        userDAO.save(u);

        return new ResultModel(u);
    }

    public ResultModel<UserEntity> setDept(Integer uid, Integer dept) {
        UserEntity user = userDAO.load(uid);
        if (user == null)
            return new ResultModel<>(ErrorInfo.INVALID_USER);

        DepartmentEntity department = departmentDAO.load(dept);
        if (department == null)
            return new ResultModel<>(ErrorInfo.INVALID_DEPT);

        user.setDepartment(department);
        userDAO.update(user);

        return new ResultModel(user);
    }

    public ResultModel<UserEntity> modifyUser(Integer uid, String realname, Integer dept, String title) {
        UserEntity user = userDAO.load(uid);
        if (user == null)
            return new ResultModel<>(ErrorInfo.INVALID_USER);

        if (dept != null) {
            DepartmentEntity department = departmentDAO.load(dept);
            if (department == null)
                return new ResultModel<>(ErrorInfo.INVALID_DEPT);

            user.setDepartment(department);
        } else {
            user.setDepartment(null);
        }

        user.setRealname(realname);
        user.setTitle(title);

        userDAO.update(user);

        return new ResultModel<>(user);
    }

    public BaseResult removeUser(Integer uid) {
        BaseResult res = new BaseResult();

        UserEntity user = userDAO.load(uid);
        if (user == null)
            return new BaseResult(ErrorInfo.INVALID_USER);

        userDAO.delete(user);

        return res.setStatus(0);
    }

    public ResultModel<UserEntity> getUser(Integer uid) {
        UserEntity u = userDAO.load(uid);
        if (u == null)
            return new ResultModel<>(ErrorInfo.INVALID_PARAMETER);

        return new ResultModel<>(u);
    }

    public ResultModel<List<UserEntity>> getUsers(Integer offset, Integer length) {
        return new ResultModel<>(userDAO.getAllListByOffset(offset, length));
    }

    public ResultModel<Long> getCountOfUsers() {
        return new ResultModel<>(userDAO.getTotalCount());
    }

    public ResultModel<List<UserEntity>> getUsersByDept(Integer dept) {
        return new ResultModel<>(userDAO.findByProperty("department.id", dept));
    }

    public ResultModel<List<UserEntity>> getUsersByLeader(Integer uid) {
        return new ResultModel<>(userDAO.getUsersByLeader(uid));
    }

    public ResultModel<DepartmentEntity> addDept(String title) {
        DepartmentEntity dept = new DepartmentEntity();
        dept.setTitle(title);
        dept.setEvaluate(DepartmentEntity.NOTEVALUATE);
        departmentDAO.save(dept);
        return new ResultModel<>(dept);
    }

    public ResultModel<DepartmentEntity> modifyDept(Integer did, String title) {
        DepartmentEntity department = departmentDAO.load(did);
        if (department == null)
            return new ResultModel<>(ErrorInfo.INVALID_DEPT);

        department.setTitle(title);
        departmentDAO.update(department);

        return new ResultModel<>(department);
    }

    public BaseResult removeDept(Integer did) {
        BaseResult res = new BaseResult();

        DepartmentEntity department = departmentDAO.load(did);
        if (department == null)
            return new BaseResult(ErrorInfo.INVALID_DEPT);

        List<UserEntity> l = userDAO.findByProperty("department.id", did);
        if (l.size() > 0)
            return new BaseResult(ErrorInfo.NOT_EMPTY_DEPT);

        departmentDAO.delete(department);

        return res.setStatus(0);
    }

    public ResultModel<List<DepartmentEntity>> getDepts() {
        return new ResultModel(departmentDAO.getAllList());
    }

    public ResultModel<DepartmentEntity> getDept(Integer did) {
        DepartmentEntity d = departmentDAO.load(did);
        if (d == null)
            return new ResultModel<>(ErrorInfo.INVALID_PARAMETER);

        return new ResultModel(d);
    }

    public BaseResult setEvaluateForDept(Integer did, Integer evaluate) {
        DepartmentEntity dept = departmentDAO.load(did);
        if (dept == null)
            return new BaseResult(ErrorInfo.INVALID_DEPT);

        dept.setEvaluate(evaluate);
        departmentDAO.update(dept);
        return new BaseResult();
    }

    public BaseResult setEvaluateUser(Integer did, Integer uid) {
        DepartmentEntity dept = departmentDAO.load(did);
        if (dept == null)
            return new BaseResult(ErrorInfo.INVALID_DEPT);

        if (uid > 0) {
            UserEntity user = userDAO.load(uid);
            if (user == null)
                return new ResultModel<>(ErrorInfo.INVALID_USER);

            dept.setEvauser(user.getId());
            dept.setEvausername(user.getRealname());
        } else {
            dept.setEvauser(null);
            dept.setEvausername(null);
        }

        departmentDAO.update(dept);
        return new BaseResult();
    }

    public ResultModel<LeaderEntity> addLeaderForDept(Integer did, Integer uid, String title) {
        UserEntity user = userDAO.load(uid);
        if (user == null)
            return new ResultModel<>(ErrorInfo.INVALID_USER);

        DepartmentEntity dept = departmentDAO.load(did);
        if (dept == null)
            return new ResultModel<>(ErrorInfo.INVALID_DEPT);

        LeaderEntity leader = new LeaderEntity();
        leader.setLeader(user);
        leader.setDepartment(dept);
        leader.setTitle(title);
        leaderDAO.save(leader);

        return new ResultModel<>(leader);
    }

    public ResultModel<List<LeaderEntity>> getLeaderByDept(Integer did) {
        return new ResultModel<>(leaderDAO.findByProperty("department.id", did));
    }

    public ResultModel<List<LeaderEntity>> getLeaderByUser(Integer uid) {
        return new ResultModel<>(leaderDAO.findByProperty("leader.id", uid));
    }

    public BaseResult removeLeaderForDept(Integer did, Integer uid) {
        List<LeaderEntity> ll = leaderDAO.findByProperties(new String[]{"department.id", "leader.id"}, new Integer[]{did, uid});
        for (LeaderEntity l : ll) {
            leaderDAO.delete(l);
        }
        return new BaseResult();
    }

    public BaseResult changePwd(Integer id, String old, String newp, String again) {

        UserEntity user = userDAO.load(id);
        if (user == null)
            return new BaseResult(ErrorInfo.INVALID_USER);

        if (!user.getPassword().equals(old))
            return new BaseResult(ErrorInfo.INVALIE_OLD_PASSWORD);

        if (!newp.equals(again))
            return new BaseResult(ErrorInfo.DIFF_NEW_PASSWORD);

        user.setPassword(newp);

        userDAO.update(user);

        return new BaseResult();
    }

    public ResultModel<EvaluateUserInfo> getEvaluateUserList(Integer uid) {
        UserEntity user = userDAO.load(uid);
        if (user == null) {
            return new ResultModel<>(ErrorInfo.INVALID_USER);
        }

        EvaluateUserInfo info = new EvaluateUserInfo();

        if (user.getDepartment() != null) {
            if (user.getDepartment().getEvaluate() == DepartmentEntity.NOTEVALUATE) {
                info.setUnderling(userDAO.getEvaUsersByLeader(uid));
                info.setOtherUnderling(userDAO.getEvaUsersWithoutLeader(uid));
            }

            if (user.getDepartment().getEvauser() == user.getId()) {
                List<UserEntity> l = userDAO.getEvaOtherDepartmentColleague(uid);
                info.setColleague(l);
            }
        }



        return new ResultModel<>(info);
    }
}
