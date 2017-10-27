package com.katyusha.aron.library.http.request;


import android.content.Context;
import android.text.TextUtils;

import com.katyusha.aron.library.BaseApplication;
import com.katyusha.aron.library.constant.Constant;
import com.katyusha.aron.library.http.cookie.CookieManger;
import com.katyusha.aron.library.http.service.ApiService;
import com.katyusha.aron.library.http.transformer.FlatTransformer;
import com.katyusha.aron.library.http.transformer.SchedulersTransformer;
import com.katyusha.aron.library.model.BaseResponse;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.socks.library.KLog;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action0;

/**
 * Created by aron on 2017/6/13.
 */

public class BLRequest {

    public static final int CONNECT_TIME = 15;
    public static final int READ_TIME = 15;
    public static final int WRITE_TIME = 15;
    private static volatile OkHttpClient sOkHttpClient;
    private static BLRequest mRetrofitManager;
    private Context context;
    private ApiService apiService;
    private String baseUrl;
    private Class clz;

    public BLRequest() {
        this.context = BaseApplication.getAppContext();
    }

    public static BLRequest getInstance() {
        if (mRetrofitManager == null) {
            mRetrofitManager = new BLRequest();
        }
        return mRetrofitManager;
    }

    private Retrofit createApiClient() {
        if (TextUtils.isEmpty(baseUrl)) {
            baseUrl = Constant.BL_HOST;
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient()).build();
        return retrofit;
    }

    /**
     * 供外部调用创建api服务
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> clz) {
        return createApiClient().create(clz);
    }

    /**
     * 创建通用api服务
     *
     * @return
     */
    public <T> BLRequest createApi(Class<T> clz) {
        apiService = createApiClient().create(ApiService.class);
        this.clz = clz;
        return this;
    }

    /**
     * 不是默认baseurl时,设置baseurl
     * @param baseUrl
     */
    public BLRequest setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * 创建OKHttpClient
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (BLRequest.class) {
                Cache cache = new Cache(new File(context.getCacheDir(), "HttpCache"),
                        1024 * 1024 * 100);
                if (sOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.connectTimeout(CONNECT_TIME, TimeUnit.SECONDS);
                    builder.readTimeout(READ_TIME, TimeUnit.SECONDS);
                    builder.writeTimeout(WRITE_TIME, TimeUnit.SECONDS);
                    builder.cookieJar(new CookieManger(context));
//                    builder.cache(cache);
//                    builder.addNetworkInterceptor(new HttpCacheInterceptor(context));
//                    builder.addInterceptor(new HttpParamsInterceptor());
                    builder.addInterceptor(new StethoInterceptor());
//                    if (BuildConfig.LOG) {
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                    } else {
//                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
//                    }
                    builder.addInterceptor(loggingInterceptor);
                    sOkHttpClient = builder.build();
                }
            }
        }
        return sOkHttpClient;
    }

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            KLog.i(String.format("request message %s", message));
        }
    });

    /**
     * 带参数的GET请求
     * @param url
     * @param params
     * @return
     */
    public Observable<BaseResponse> get(String url, Map<String, String> params) {
        return apiService.get(url, params).compose(new FlatTransformer(clz))
                .compose(new SchedulersTransformer<BaseResponse>())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        baseUrl = null;
                        clz = null;
                    }
                });
    }

    /**
     * 不带参数的GET请求
     * @param url
     * @return
     */
    public Observable<BaseResponse> get(String url) {
        return apiService.get(url).compose(new FlatTransformer(clz))
                .compose(new SchedulersTransformer<BaseResponse>())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        baseUrl = null;
                        clz = null;
                    }
                });
    }

    /**
     * 格式为json的Post请求
     * @param url
     * @param body
     * @return
     */
    public Observable<BaseResponse> postJson(String url, RequestBody body){
        return apiService.postJson(url, body)
                .compose(new FlatTransformer(clz))
                .compose(new SchedulersTransformer<BaseResponse>())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        baseUrl = null;
                        clz = null;
                    }
                });
    }

    /**
     * 格式为map的Post请求
     * @param url
     * @param maps
     * @return
     */
    public Observable<BaseResponse> post(String url, Map<String, String> maps){
        return apiService.post(url, maps)
                .compose(new FlatTransformer(clz))
                .compose(new SchedulersTransformer<BaseResponse>())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        baseUrl = null;
                        clz = null;
                    }
                });
    }
}
