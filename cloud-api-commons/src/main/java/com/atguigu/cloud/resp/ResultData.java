package com.atguigu.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Author chentian
 * @Date 2024/4/25
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {

    private String code;
    private String messge;
    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessge(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(String code, String message) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMessge(message);
        resultData.setData(null);
        return resultData;
    }
}
