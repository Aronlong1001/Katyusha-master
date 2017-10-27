package com.katyusha.aron.demo.viewmodel;

import android.content.Context;

import com.katyusha.aron.demo.event.HomeEvent;
import com.katyusha.aron.demo.event.HomeResponse;
import com.katyusha.aron.library.constant.Urls;
import com.katyusha.aron.library.http.subscriber.CommonSubscriber;
import com.katyusha.aron.library.http.FailureType;
import com.katyusha.aron.library.http.HttpParams;
import com.katyusha.aron.library.model.BaseResponse;
import com.katyusha.aron.library.model.BaseVM;
import com.katyusha.aron.library.http.request.BLRequest;
import com.katyusha.aron.library.utils.BhCacheUtils;

import rx.Subscription;

/**
 * Created by aron on 2017/6/13.
 */

public class HomeVM extends BaseVM{

    public static final String BH_CATCH_KEY = "1000";
    public final static int Cache_Second = 600;

    public HomeVM(Context context) {
        super(context);
    }

    public void requestHomeData() {
        // 取缓存600秒的数据
//        HomeResponse response = BhCacheUtils.getObject(context, BH_CATCH_KEY, Cache_Second);
//        if (response != null){
//            HomeEvent homeEvent = new HomeEvent(true, response.getErrorInfo());
//            homeEvent.setResponse(response);
//            postEvent(homeEvent);
//            return;
//        }

        CommonSubscriber<BaseResponse> mSubscriber = new CommonSubscriber<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                HomeResponse resp = (HomeResponse) response;//类型转换
                HomeEvent homeEvent = new HomeEvent(true, resp.getErrorInfo());
                homeEvent.setResponse(resp);
                postEvent(homeEvent);
                BhCacheUtils.saveObject(context, BH_CATCH_KEY, resp);
            }

            @Override
            public void onFailure(int type, BaseResponse response) {
                if (type == FailureType.ABNORMAL) {
                    HomeEvent homeEvent = new HomeEvent(false, response.getErrorInfo());
                    postEvent(homeEvent);
                } else {
                    HomeEvent homeEvent = new HomeEvent(false, null);
                    postEvent(homeEvent);
                }
            }
        };

        HttpParams params = new HttpParams();
        params.put("channel", "benlai");
        params.put("source", "3");
        params.put("limit", "5");
        params.put("categorySysNo", "2");
        params.put("offset", "0");
        params.put("localcity", "120");
        Subscription subscription = BLRequest.getInstance().createApi(HomeResponse.class)
                .get(Urls.HOME, params.urlParamsMap)
                .subscribe(mSubscriber);

        // 自定义接口
//        HomeApiService service = BLRequest.getInstance().create(HomeApiService.class);
//        Subscription subscription = observe(service.getHomeData(params.urlParamsMap)).subscribe(mSubscriber);
        addSubscription(subscription);
    }

}
