package com.ss.webutil.wechat;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ss.webutil.net.HttpSSL;
import com.ss.webutil.util.Algorithm;
import com.ss.webutil.wechat.menu.Menu;
import com.ss.webutil.wechat.menu.MenuHelper;
import com.ss.webutil.wechat.token.AccessToken;
import com.ss.webutil.wechat.token.Signature;
import com.ss.webutil.wechat.token.Ticket;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

/**
 * Created by liymm on 2015-03-03.
 */
public class InitListener implements ServletContextListener {

    Timer accessTokenTimer;
    ObjectMapper objectMapper;
    Config config;
    Menu menu;

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        WebApplicationContext rwp = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext());
        objectMapper = rwp.getBean(ObjectMapper.class);
        config = rwp.getBean(Config.class);
        menu = rwp.getBean(Menu.class);

        config.setDevelop(!searchInLocalIPList(config.getServerIP()));

        if (config.isDevelop())
            return;

        int period = getAccessToken();
        getJsAPITicket();

        if (menu != null) {
            MenuHelper.createMenu(at.getAccess_token(), objectMapper, menu);
        }

        accessTokenTimer = new Timer();
        accessTokenTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getAccessToken();
                getJsAPITicket();
            }

        }, period*1000, period*1000);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        accessTokenTimer.cancel();
    }


    public static AccessToken at;
    static Ticket ticket;

    int getAccessToken() {
        String url = String
                .format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
                        config.getAppID(), config.getAppSecret());

        String resp = HttpSSL.get(url);

        try {
            at = objectMapper.readValue(resp, AccessToken.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("AT: " + at.getAccess_token());

        return at.getExpires_in();
    }

    void getJsAPITicket() {
        String url = String
                .format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi",
                        at.getAccess_token());
        String resp = HttpSSL.get(url);

        try {
            ticket = objectMapper.readValue(resp, Ticket.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Ticket: " + ticket.getTicket());
    }

    public static Signature getSignature(String url) {
        if (at == null)
            return null;

        if (ticket == null)
            return null;

        Signature s = new Signature();
        s.setTimestamp(System.currentTimeMillis() / 1000);
        s.setNoncestr("random string");

        String src = String.format("jsapi_ticket=%s&noncestr=%s&timestamp=%d&url=%s",
                ticket.getTicket(), s.getNoncestr(), s.getTimestamp(), url);

        s.setSignature(Algorithm.SHA1(src));
//
//      System.out.println(ticket.getTicket());
//      System.out.println(s.getNoncestr());
//      System.out.println(s.getTimestamp());

        return s;
    }

    public static Signature getSignature(HttpServletRequest request) {

        StringBuffer sb = request.getRequestURL();

        String q = request.getQueryString();
        if (q != null && !"".equals(q))
            sb.append("?").append(q);

//      System.out.println(sb);

        return getSignature(sb.toString());
    }

    boolean searchInLocalIPList(String ip) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        if (inetAddress.getHostAddress().equals(ip))
                            return true;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return false;
    }
}
