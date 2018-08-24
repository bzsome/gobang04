package com.chao.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用的Json消息返回类
 *
 * @author chao
 */
public class ResponseMessage implements Serializable {

    // 状态码
    private int code;

    // 提示信息
    private String msg;

    // 用户要返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    public static ResponseMessage success() {
        ResponseMessage result = new ResponseMessage();
        result.setCode(200);
        result.setMsg("处理成功！");
        return result;
    }

    public static ResponseMessage fail() {
        ResponseMessage result = new ResponseMessage();
        result.setCode(400);
        result.setMsg("处理失败！");
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public ResponseMessage add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

}
