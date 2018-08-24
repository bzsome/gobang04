package com.chao.service;

import com.chao.utils.Constants;
import okhttp3.*;

public class OkHttpTool {
    public static void getUser(String token, Callback callback) {
        final Request.Builder builder = new Request.Builder().url(Constants.OAUTH_PATH+"/token/check");
        builder.addHeader(Constants.AUTHORIZATION, token);  //将请求头以键值对形式添加，可添加多个请求头
        final Request request = builder.post(new FormBody.Builder().build()).build();
        final OkHttpClient client = new OkHttpClient.Builder().build();

        client.newCall(request).enqueue(callback);
    }
}
