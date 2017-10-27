package com.katyusha.aron.library.http.transformer;

import com.katyusha.aron.library.model.BaseResponse;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by aron on 2017/10/14.
 */

public class FlatTransformer implements Observable.Transformer<ResponseBody, BaseResponse> {

    private Class clz;

    public FlatTransformer(Class clz) {
        this.clz = clz;
    }

    @Override
    public Observable<BaseResponse> call(Observable<ResponseBody> observable) {
        return observable.flatMap(new Func1<ResponseBody, Observable<BaseResponse>>() {

            @Override
            public Observable<BaseResponse> call(ResponseBody responseBody) {
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
}
