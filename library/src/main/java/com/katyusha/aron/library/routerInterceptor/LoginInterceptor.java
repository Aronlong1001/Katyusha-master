package com.katyusha.aron.library.routerInterceptor;


import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.katyusha.aron.library.constant.Constant;
import com.katyusha.aron.library.constant.PagePath;

/**
 * Created by aron on 2017/7/13.
 */

@Interceptor(priority = 10, name = "登录拦截器")
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, final InterceptorCallback callback) {
        Log.e("Arouter", postcard.getPath());
        if (postcard.getExtra() == Constant.NEED_LOGIN){
            Log.e("Arouter", postcard.getExtra() + "");
            if (Constant.isLogin){
                callback.onContinue(postcard);
            }else {
                ARouter.getInstance().build(PagePath.Login).navigation();
            }
        }else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {

    }
}
