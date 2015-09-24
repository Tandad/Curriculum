package com.ss.task.action;

import com.ss.task.dao.EvaluationReportDAO;
import com.ss.task.dto.NewsInfo;
import com.ss.task.model.EvaluationReportEntity;
import com.ss.task.model.LeaderEntity;
import com.ss.task.model.TaskEntity;
import com.ss.task.model.UserEntity;
import com.ss.task.service.EvaluationService;
import com.ss.task.service.NewsService;
import com.ss.task.service.TaskService;
import com.ss.task.service.UserService;
import com.ss.webutil.struct.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by liymm on 2015-03-23.
 */
@Controller
@RequestMapping("/")
public class RootAction {

    public static final String DEFAULT_ACTION = "/";

    static final int LENGTH = 5;

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;
    @Autowired
    EvaluationService evaluationService;
    @Autowired
    TaskService taskService;

    @RequestMapping("")
    public String defaultPage(HttpSession session, ModelMap model) {
        UserEntity user = (UserEntity)session.getAttribute("user");
        if (user == null)
            return "login";

        return "redirect:/dashboard";
    }

    @RequestMapping("dashboard")
    public String dashboardPage(ModelMap model) {
        List<NewsInfo> newsList = newsService.getNewsList(0, LENGTH).getData();
        model.addAttribute("news", newsList);

        List<EvaluationReportEntity> evaluationList = evaluationService.getReportList(0, LENGTH).getData();
        model.addAttribute("evaluation", evaluationList);

        List<TaskEntity> progressList = taskService.getTasksByType(1, 0, LENGTH).getData();
        model.addAttribute("progress", progressList);

        List<TaskEntity> qualityList = taskService.getTasksByType(2, 0, LENGTH).getData();
        model.addAttribute("quality", qualityList);

        List<TaskEntity> safetyList = taskService.getTasksByType(3, 0, LENGTH).getData();
        model.addAttribute("safety", safetyList);

        List<TaskEntity> costList = taskService.getTasksByType(4, 0, LENGTH).getData();
        model.addAttribute("cost", costList);

        //Long t = newsService.getCountOfNews().getData();
        //model.addAttribute("page", page);
        //model.addAttribute("total", (t+LENGTH-1)/LENGTH);

        return "dashboard";
    }

    @RequestMapping("login")
    public String login(String name, String password, HttpSession session) {

        ResultModel<UserEntity> r = userService.login(name, password);
        if (r.getStatus() != 0)
            return "login";

        UserEntity user = r.getData();

        session.setAttribute("user", user);

        List<LeaderEntity> leaders = userService.getLeaderByUser(user.getId()).getData();

        session.setAttribute("isLeader", leaders.size() != 0);

        session.setAttribute("tasknum", taskService.getCountOfAllPubTasksByMember(user.getId()).getData());

        return "redirect:/";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("isLeader");
        return "redirect:/";
    }
}
