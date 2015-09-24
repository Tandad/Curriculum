package com.ss.webutil.baidu.lbs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.webutil.net.Http;

import java.io.IOException;

/**
 * Created by LiYm on 2015/8/4.
 */
public class LBSHelper {

    /**
     * 新建LBS数据
     * @param objectMapper
     * @param lbsBase
     * @return
     */
    public static LBSResponse save(ObjectMapper objectMapper, LBSBase lbsBase) {
        String response = Http.post("http://api.map.baidu.com/geodata/v3/poi/create", lbsBase.toForm());
        try {
            return objectMapper.readValue(response, LBSResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 更新LBS数据
     * @param objectMapper
     * @param lbsBase
     * @return
     */
    public static LBSResponse update(ObjectMapper objectMapper, LBSBase lbsBase) {
        String response = Http.post("http://api.map.baidu.com/geodata/v3/poi/update", lbsBase.toForm());

        try {
            return objectMapper.readValue(response, LBSResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
