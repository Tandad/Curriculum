package com.ss.webutil.wechat.pay;

import com.ss.webutil.wechat.Config;

/**
 * Created by LiYm on 2015/9/2.
 */
public class CloseReq extends BaseRequest {

    String out_trade_no;

    public CloseReq(Config config) {
        super(config);
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
