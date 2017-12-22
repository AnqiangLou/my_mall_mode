package com.android.xwtech.mallmode.http;

import com.android.xwtech.mallmode.model.HttpResult;
import com.android.xwtech.mallmode.model.News;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author XW-Laq
 * @date 2017/12/15
 */

public interface ApiService {
    @GET("news")
    Observable<HttpResult<List<News>>> getNews(@Query("type_id") int id, @Query("page_size") int page_size, @Query("page") int page);

}
