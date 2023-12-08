package com.smart.smartapp.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/11/30
 */
public class JsonToJavaObjectExample {
    public static void main(String[] args) throws Exception {
        String jsonString = "{"
                + "\"@StartTime\": \"14:05:00\","
                + "\"@EndTime\": \"14:10:00\","
                + "\"@NumberServed\": \"3\","
                + "\"@SecondsOccupied\": \"40\","
                + "\"@TotalSecondsOccupied\": \"45\","
                + "\"@Status\": \"0\""
                + "}";

        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 将 JSON 对象转换为 Java 对象
        ServingInfo servingInfo = objectMapper.readValue(jsonString, ServingInfo.class);

        // 访问 Java 对象的属性
        System.out.println("Start Time: " + servingInfo.getStartTime());
        System.out.println("End Time: " + servingInfo.getEndTime());
        System.out.println("Number Served: " + servingInfo.getNumberServed());
        System.out.println("Seconds Occupied: " + servingInfo.getSecondsOccupied());
        System.out.println("Total Seconds Occupied: " + servingInfo.getTotalSecondsOccupied());
        System.out.println("Status: " + servingInfo.getStatus());
    }
}

class ServingInfo {
    @JsonProperty("@StartTime")
    private String startTime;

    @JsonProperty("@EndTime")
    private String endTime;

    @JsonProperty("@NumberServed")
    private String numberServed;

    @JsonProperty("@SecondsOccupied")
    private String secondsOccupied;

    @JsonProperty("@TotalSecondsOccupied")
    private String totalSecondsOccupied;

    @JsonProperty("@Status")
    private String status;

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getNumberServed() {
        return numberServed;
    }

    public String getSecondsOccupied() {
        return secondsOccupied;
    }

    public String getTotalSecondsOccupied() {
        return totalSecondsOccupied;
    }

    public String getStatus() {
        return status;
    }
}
