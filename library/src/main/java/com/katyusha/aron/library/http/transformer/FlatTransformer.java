package com.katyusha.aron.library.http.transformer;

import com.katyusha.aron.library.model.BaseResponse;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Created by aron on 2017/10/14.
 */

public class FlatTransformer implements ObservableTransformer<ResponseBody, BaseResponse> {

    private Class clz;

    public FlatTransformer(Class clz) {
        this.clz = clz;
    }

    /**
     * 检查是否传入clz
     * @return
     */
    private boolean checkClzNull() {
        if (clz == null) {
            return true;
        }
        return false;
    }

    @Override
    public ObservableSource<BaseResponse> apply(Observable<ResponseBody> upstream) {
        return upstream.flatMap(new Function<ResponseBody, ObservableSource<BaseResponse>>() {
            @Override
            public ObservableSource<BaseResponse> apply(ResponseBody responseBody) throws Exception {
                BaseResponse response = null;
                try {
                    String str = new String(responseBody.bytes());
                    if (!checkClzNull()) {
                        response = (BaseResponse) new Gson().fromJson(str, clz);
                    }else {
                        throw new Exception("Unset response entity class");
                    }
//                    if (!(response instanceof BaseResponse)) {
//                        throw new Exception("The response entity type error, it didn't extends BaseResponse");
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Observable.just(response);
            }
        });
    }

//    @Override
//    public Observable<BaseResponse> apply(Observable<ResponseBody> upstream) {
//        return upstream.flatMap(new Function<ResponseBody, Observable<BaseResponse>>() {
//            @Override
//            public Observable<BaseResponse> apply(ResponseBody responseBody) throws Exception {
//                BaseResponse response = null;
//                try {
//                    String str = new String(responseBody.bytes());
//                    if (!checkClzNull()) {
//                        response = (BaseResponse) new Gson().fromJson(str, clz);
//                    }else {
//                        throw new Exception("Unset response entity class");
//                    }
////                    if (!(response instanceof BaseResponse)) {
////                        throw new Exception("The response entity type error, it didn't extends BaseResponse");
////                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return Observable.just(response);
//            }
//        });
//    }
}
