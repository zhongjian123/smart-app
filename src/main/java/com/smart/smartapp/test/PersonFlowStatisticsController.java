package com.smart.smartapp.test;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.smart.smartapp.POJO.DO.UserDO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/11/24
 */
@RestController
public class PersonFlowStatisticsController {

    @PostMapping("/report/data")
    public String receiveReportData(@RequestBody Object data) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(data));
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
        return dateFormat.format(date);
//        return HttpStatus.OK.getReasonPhrase();
    }

}
