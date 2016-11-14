package com.example.user.myapplication;

/**
 * Created by User on 2016-11-14.
 */

public class ResultEntity {
    boolean result;
    int responseTime;

    public ResultEntity(boolean result, int responseTime) {
        this.result = result;
        this.responseTime = responseTime;
    }

    public boolean isResult() {
        return result;
    }

    public int getResponseTime() {
        return responseTime;
    }
}
