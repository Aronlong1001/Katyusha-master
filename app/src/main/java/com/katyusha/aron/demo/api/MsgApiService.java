package com.katyusha.aron.demo.api;

import com.katyusha.aron.demo.event.MessageResponse;
import com.katyusha.aron.library.constant.Urls;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by aron on 2017/10/12.
 */

public interface MsgApiService {

    @GET(Urls.MSG_HOME)
    Observable<MessageResponse> getMessage();
}
