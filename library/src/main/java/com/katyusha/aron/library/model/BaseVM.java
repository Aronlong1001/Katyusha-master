package com.katyusha.aron.library.model;

import android.app.Dialog;
import android.content.Context;

import com.katyusha.aron.library.widget.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aron on 2017/10/10.
 */

public class BaseVM {

    public Context context;
    private CompositeDisposable mCompositeSubscription;

    private Dialog loadingDialog;

    public BaseVM(Context context) {
        this.context = context;
    }

    /**
     * @return
     */
    public CompositeDisposable getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeDisposable();
        }

        return this.mCompositeSubscription;
    }


    /**
     * @param s
     */
    public void addSubscription(Disposable s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeDisposable();
        }

        this.mCompositeSubscription.add(s);
    }

    /**
     * @param event
     */
    public void postEvent(Object event) {
        EventBus.getDefault().post(event);
    }

    /**
     * @param observable
     * @param <T>
     * @return
     */
    public <T> Observable<T> observe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 显示加载框
     */
    public void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }
        loadingDialog.show();
    }

    /**
     * 取消加载框
     */
    public void dismissLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}
