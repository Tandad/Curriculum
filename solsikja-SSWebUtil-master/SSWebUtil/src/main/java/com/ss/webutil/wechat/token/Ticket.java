package com.ss.webutil.wechat.token;

/**
 * Created by liymm on 2015-03-03.
 */
public class Ticket {

    int errcode;
    String errmsg;
    String ticket;
    int expires_in;

    public Ticket() {
    }

    public Ticket(int errcode, String errmsg, String ticket, int expires_in) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.ticket = ticket;
        this.expires_in = expires_in;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
