package com.yass.okhttp;

import com.yass.okhttp.https.HttpsUtils;
import com.yass.okhttp.listener.DisposeDataHandle;
import com.yass.okhttp.response.CommonFileCallback;
import com.yass.okhttp.response.CommonJsonCallback;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author yass
 * @function 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */
public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {

        // 1 创建OkHttpClient.Builder构建者对象
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        // 2 通过构建者进行参数配置
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        //okHttpClientBuilder.cookieJar(new SimpleCookieJar());
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true);
        /**
         * trust all the https point
         */
        okHttpClientBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());

        // 3 通过OkHttpClient.Builder生成OkHttpClient对象
        mOkHttpClient = okHttpClientBuilder.build();
    }

    /**
     * 指定cilent信任指定证书
     *
     * @param certificates
     */
    public static void setCertificates(InputStream... certificates) {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
    }

    /**
     * 指定client信任所有证书
     */
    public static void setCertificates() {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory());
    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     * @param handle
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }
}