package com.katyusha.aron.library.http.subscriber;

import android.util.Log;

import com.katyusha.aron.library.http.FailureType;
import com.katyusha.aron.library.model.BaseResponse;
import com.socks.library.KLog;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by aron on 2017/10/10.
 * 创建一个可被 CompositeDisposable 管理的 observer->DisposableObserver
 */

public abstract class CommonSubscriber<T extends BaseResponse> extends DisposableObserver<T> {

    private final String TAG = this.getClass().getSimpleName();
    public static final String ERROR_CODE = "0";

    @Override
    public void onNext(T response) {
        if (response.getError().equals(ERROR_CODE)) {
            onSuccess(response);
        } else {
            onFinalFailure(FailureType.ABNORMAL, response);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e != null) {
            Log.e(TAG, e.toString());
            onFinalFailure(FailureType.NETWORK, null);
        }
    }

    @Override
    public void onComplete() {

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
