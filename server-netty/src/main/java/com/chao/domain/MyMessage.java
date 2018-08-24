package com.chao.domain;

import java.io.Serializable;

/**
 * 聊天信息
 */
    public class MyMessage implements Serializable {
    private static final long serialVersionUID = 4228051882802183587L;
    private String send_user;//发信者
    private String receive_user;//接收者
    private String msg_text;//消息内容

    public MyMessage() {
    }

    public MyMessage(String send_user, String receive_user, String msg_text) {
        this.send_user = send_user;
        this.receive_user = receive_user;
        this.msg_text = msg_text;
    }

    public String getSend_user() {
        return send_user;
    }

    public void setSend_user(String send_user) {
        this.send_user = send_user;
    }

    public String getReceive_user() {
        return receive_user;
    }

    public void setReceive_user(String receive_user) {
        this.receive_user = receive_user;
    }

    public String getMsg_text() {
        return msg_text;
    }

    public void setMsg_text(String msg_text) {
        this.msg_text = msg_text;
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "send_user='" + send_user + '\'' +
                ", receive_user='" + receive_user + '\'' +
                ", msg_text='" + msg_text + '\'' +
                '}';
    }
}
