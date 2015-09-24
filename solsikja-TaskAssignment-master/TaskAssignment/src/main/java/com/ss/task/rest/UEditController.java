package com.ss.task.rest;

import com.baidu.ueditor.ActionEnter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by liymm on 2015-03-25.
 */
@RestController
@RequestMapping("/ueditor/")
public class UEditController {

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("")
    public String ueditor(HttpServletRequest request) {
        String rootPath = servletContext.getRealPath( "/" );

//        String uri = request.getRequestURI();
//        String contextPath = request.getContextPath();
//        String originalPath;
//
//        if(contextPath.length() > 0) {
//            originalPath = rootPath + uri.substring(contextPath.length());
//        } else {
//            originalPath = rootPath + uri;
//        }

//        File file = new File(originalPath);
//        if(!file.isAbsolute()) {
//            file = new File(file.getAbsolutePath());
//        }
//
//        String parentPath = file.getParent();

        return new ActionEnter( request, rootPath ).exec();
    }

}
