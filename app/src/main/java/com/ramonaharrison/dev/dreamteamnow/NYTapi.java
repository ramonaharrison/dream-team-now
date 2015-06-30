package com.ramonaharrison.dev.dreamteamnow;

import com.ramonaharrison.dev.dreamteamnow.model.NYTModel;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by kadeemmaragh on 6/27/15.
 */
public interface NYTapi {

    @GET("/24?api-key=1fb09ee32a69fd6c40f98e7e38f6b0a4:6:72391617")      //here is the other url part.best way is to start using /
    public void getFeed(Callback<NYTModel> response);

}
