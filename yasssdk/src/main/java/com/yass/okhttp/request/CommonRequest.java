package com.yass.okhttp.request;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author yass
 * @function 接收请求参数,为我们生成Request对象
 */
public class CommonRequest {

    /**
     * 返回post请求时用到的Request对象
     *
     * @param url
     * @param params
     * @return 返回post请求时用到的Request对象
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody mFormBody = mFormBodyBuild.build();

        Request text = new Request.Builder().url(url).post(mFormBody).build();
        return text;
    }

    /**
     * 返回Get请求时用到的Request对象
     *
     * @param url
     * @param params
     * @return 返回Get请求时用到的Request对象
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();
    }

    /**
     * @param url
     * @param params
     * @return
     */
    public static Request createMonitorRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("&");
        if (params != null && params.hasParams()) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();
    }

    /**
     * 文件上传请求
     *
     * @return
     */
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    public static Request createMultiPostRequest(String url, RequestParams params) {

        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        if (params != null) {

            for (Map.Entry<String, Object> entry : params.fileParams.entrySet()) {
                if (entry.getValue() instanceof File) {
                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                        RequestBody.create(FILE_TYPE, (File) entry.getValue()));
                } else if (entry.getValue() instanceof String) {

                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                        RequestBody.create(null, (String) entry.getValue()));
                }
            }
        }
        return new Request.Builder().url(url).post(requestBody.build()).build();
    }
}