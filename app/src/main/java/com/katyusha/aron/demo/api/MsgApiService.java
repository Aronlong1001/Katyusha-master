package com.katyusha.aron.demo.api;

import com.katyusha.aron.demo.event.MessageResponse;
import com.katyusha.aron.library.constant.Urls;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by aron on 2017/10/12.
 */

public interface MsgApiService {

    @GET(Urls.MSG_HOME)
    Observable<MessageResponse> getMessage();
}
