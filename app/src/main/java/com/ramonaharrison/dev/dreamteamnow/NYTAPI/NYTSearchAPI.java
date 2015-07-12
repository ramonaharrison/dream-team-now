package com.ramonaharrison.dev.dreamteamnow.NYTAPI;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by kadeemmaragh on 7/2/15.
 */
public interface NYTSearchAPI {

//TODO: Need to fix retrofit query fields, right now the search is hardcoded for demo purposes.
//    Example 'GET'
//    @GET("/articlesearch.json?q={searchterm}&facet_field=day_of_week&begin_date={begindate}&end_date={enddate}&api-key=9e8ea4b3395b35879808c8bc47017370:12:72391617")

@GET("/articlesearch.json?q=romney&facet_field=day_of_week&begin_date=20120101&end_date=20120101&api-key=9e8ea4b3395b35879808c8bc47017370:12:72391617")
    void getFeed(Callback<NYTSearchModel> response);


}

