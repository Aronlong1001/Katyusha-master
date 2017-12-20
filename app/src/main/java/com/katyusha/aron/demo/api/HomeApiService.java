package com.katyusha.aron.demo.api;

import com.katyusha.aron.demo.event.HomeResponse;
import com.katyusha.aron.library.constant.Urls;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by aron on 2017/6/13.
 */

public interface HomeApiService {

    @GET(Urls.HOME)
    Observable<HomeResponse> getHomeData(@Query("channel") String channel, @Query("source") int source,
                                         @Query("limit") int limit, @Query("categorySysNo") int categorySysNo,
                                         @Query("offset") int offset, @Query("localcity") int localCity);

    @GET(Urls.HOME)
    Observable<HomeResponse> getHomeData(@QueryMap Map<String, String> maps);
}
