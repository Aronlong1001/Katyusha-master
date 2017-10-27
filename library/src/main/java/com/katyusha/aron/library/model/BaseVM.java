package com.katyusha.aron.library.model;

import android.app.Dialog;
import android.content.Context;

import com.katyusha.aron.library.widget.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by aron on 2017/10/10.
 */

public class BaseVM {

    public Context context;
    private CompositeSubscription mCompositeSubscription;

    private Dialog loadingDialog;

    public BaseVM(Context context) {
        this.context = context;
    }

    /**
     * @return
     */
    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }


    /**
     * @param s
     */
    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
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
