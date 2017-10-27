package com.katyusha.aron.library.http.interceptor;

import com.katyusha.aron.library.constant.Constant;
import com.katyusha.aron.library.constant.Params;
import com.katyusha.aron.library.utils.LocaleUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by aron on 2017/10/11.
 */

public class HttpParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        //add common header
        builder.addHeader("Accept-Language", LocaleUtil.getLanguage());

        String method = request.method();
        if (method.equals("GET")) {
            request = addGetParams(request);
        } else if (method.equals("POST")){
            request = addPostParams(request);
        }
        return chain.proceed(request);
    }

    private Request addPostParams(Request request) {
        return null;
    }

    private Request addGetParams(Request request) {
        HttpUrl httpUrl = request.url()
                .newBuilder()
                .addQueryParameter(Params.VERSION, LocaleUtil.getVersionName())
                .addQueryParameter(Params.SYSTEM_VERSION, LocaleUtil.getSystemVersion())
                .addQueryParameter(Params.SOURCE, Constant.ANDROID_SOURCE)
                .addQueryParameter(Params.PHONE_MODEL, LocaleUtil.getPhoneModel())
                .addQueryParameter(Params.DEVICE_ID, LocaleUtil.getDeviceID())
                .build();
        request = request.newBuilder().url(httpUrl).build();
        return request;
    }
}
