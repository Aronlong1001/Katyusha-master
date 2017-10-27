package com.katyusha.aron.library.http.subscriber;

import com.katyusha.aron.library.http.FailureType;
import com.katyusha.aron.library.model.BaseResponse;
import com.socks.library.KLog;

import rx.Subscriber;

/**
 * Created by aron on 2017/10/10.
 */

public abstract class CommonSubscriber<T extends BaseResponse> extends Subscriber<T> {


    public static final String ERROR_CODE = "0";

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        KLog.e(e.toString());
        onFinalFailure(FailureType.NETWORK, null);
    }

    @Override
    public void onNext(T response) {
        if (response.getError().equals(ERROR_CODE)) {
            onSuccess(response);
        } else {
            onFinalFailure(FailureType.ABNORMAL, response);
        }
    }


    public abstract void onSuccess(T response);

    public abstract void onFailure(int type, BaseResponse response);

    /**
     * @param type
     * @param response
     */
    private void onFinalFailure(int type, BaseResponse response){
        onFailure(type, response);
    }
}
