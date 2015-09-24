package com.ss.webutil.wechat.pay;

/**
 * Created by LiYm on 2015/6/8.
 */
public class PrepayResp extends BaseResponse {
    String trade_type;
    String prepay_id;
    String code_url;

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
}
