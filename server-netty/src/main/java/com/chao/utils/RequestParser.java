package com.chao.utils;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.*;

import java.io.IOException;
import java.util.*;

/**
 * HTTP请求参数解析器, 支持GET, POST
 * Created by whf on 12/23/15.
 */
public class RequestParser {
    private FullHttpRequest fullReq;

    /**
     * 构造一个解析器
     *
     * @param req
     */
    public RequestParser(FullHttpRequest req) {
        this.fullReq = req;
    }


    /**
     * 解析请求参数
     *
     * @return 包含所有请求参数的键值对, 如果没有参数, 则返回空Map
     * @throws IOException
     */
    public Map<String, String> getRequestParams() {

        Map<String, String> requestParams = new HashMap<>();
        // 处理get请求
        if (fullReq.getMethod() == HttpMethod.GET) {
            QueryStringDecoder decoder = new QueryStringDecoder(fullReq.getUri());
            Map<String, List<String>> parame = decoder.parameters();
            Iterator<Map.Entry<String, List<String>>> iterator = parame.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> next = iterator.next();
                requestParams.put(next.getKey(), next.getValue().get(0));
            }
        }
        // 处理POST请求
        if (fullReq.getMethod() == HttpMethod.POST) {
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(
                    new DefaultHttpDataFactory(false), fullReq);
            List<InterfaceHttpData> postData = decoder.getBodyHttpDatas(); //
            for (InterfaceHttpData data : postData) {
                if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                    MemoryAttribute attribute = (MemoryAttribute) data;
                    requestParams.put(attribute.getName(), attribute.getValue());
                }
            }
        }
        return requestParams;
    }
}