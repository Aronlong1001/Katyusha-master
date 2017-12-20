package com.katyusha.aron.demo.viewmodel;

import android.content.Context;

import com.katyusha.aron.demo.event.LoginEvent;
import com.katyusha.aron.demo.event.MessageResponse;
import com.katyusha.aron.library.constant.Constant;
import com.katyusha.aron.library.constant.Urls;
import com.katyusha.aron.library.http.subscriber.CommonSubscriber;
import com.katyusha.aron.library.http.FailureType;
import com.katyusha.aron.library.http.HttpParams;
import com.katyusha.aron.library.http.request.BLRequest;
import com.katyusha.aron.library.model.BaseResponse;
import com.katyusha.aron.library.model.BaseVM;
import com.google.gson.Gson;
import com.socks.library.KLog;


/**
 * Created by aron on 2017/10/12.
 */

public class LoginVM extends BaseVM {

    public LoginVM(Context context) {
        super(context);
    }

    public void requestLogin(String userId, String md5Pwd, String userName, String autoLogin){
        showLoadingDialog();
        CommonSubscriber<BaseResponse> subscriber = new CommonSubscriber<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                dismissLoadingDialog();
                KLog.e("login---->" + response.toString());
                LoginEvent event = new LoginEvent(true, response.getErrorInfo());
                event.setResponse(response);
                postEvent(event);
            }

            @Override
            public void onFailure(int type, BaseResponse response) {
                dismissLoadingDialog();
                if (type == FailureType.ABNORMAL) {
                    LoginEvent event = new LoginEvent(false, response.getErrorInfo());
                    postEvent(event);
                } else {
                    LoginEvent event = new LoginEvent(false, null);
                    postEvent(event);
                }
            }
        };
        HttpParams params = new HttpParams();
        params.put("UserID", userId);
        params.put("MD5PWD", md5Pwd);
        params.put("UserName", userName);
        params.put("AutoLogin", autoLogin);
        BLRequest.getInstance()
                .setBaseUrl(Constant.BLGF_HOST)//如果baseurl不一致先设置baseurl
                .createApi(BaseResponse.class)//必须设置返回数据的实体类
                .get(Urls.LOGIN, params.urlParamsMap)
                .subscribe(subscriber);//通用api接口

//        LoginApiService service = BLRequest.getInstance()
//                .setBaseUrl(Constant.BLGF_HOST)
//                .create(LoginApiService.class);//自定义api接口
//        observe(service.getLogin(params.urlParamsMap)).subscribe(subscriber);//自定义api接口请求
        addSubscription(subscriber);
    }

    public void requestMessage(){
        showLoadingDialog();
        CommonSubscriber<BaseResponse> MsgSubscriber = new CommonSubscriber<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                dismissLoadingDialog();
                MessageResponse resp = (MessageResponse) response;
                Gson gson = new Gson();
                String result = gson.toJson(resp);
                KLog.e("message---->" + result);
            }

            @Override
            public void onFailure(int type, BaseResponse response) {
                dismissLoadingDialog();
            }
        };
        BLRequest.getInstance()
                .setBaseUrl(Constant.BLGF_HOST)//如果baseurl不一致先设置baseurl
                .createApi(MessageResponse.class)//必须设置返回数据的实体类
                .get(Urls.MSG_HOME)
                .subscribe(MsgSubscriber);

//        MsgApiService service = BLRequest.getInstance()
//                .setBaseUrl(Constant.BLGF_HOST)
//                .create(MsgApiService.class);//自定义api接口
//        observe(service.getMessage()).subscribe(MsgSubscriber);//自定义api接口请求
        addSubscription(MsgSubscriber);
    }
}
