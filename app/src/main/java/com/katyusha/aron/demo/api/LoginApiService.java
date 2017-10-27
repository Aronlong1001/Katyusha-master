package com.katyusha.aron.demo.api;

import com.katyusha.aron.library.constant.Urls;
import com.katyusha.aron.library.model.BaseResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by aron on 2017/10/12.
 */

public interface LoginApiService {

    @GET(Urls.LOGIN)
    Observable<BaseResponse> getLogin(@QueryMap Map<String, String> maps);
}
