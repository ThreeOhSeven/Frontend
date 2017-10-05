package edu.purdue.a307.betcha.Api;

import java.util.List;

import edu.purdue.a307.betcha.Models.AccountInformation;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetComment;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.BetLike;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.FriendItem;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.PrivateFeedItem;
import edu.purdue.a307.betcha.Models.ProfileInformation;
import edu.purdue.a307.betcha.Models.PublicFeedItem;
import edu.purdue.a307.betcha.Models.PublicFeedResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kyleohanian on 9/20/17.
 */

public interface BetchaApi {

    // Login
    @POST("/auth/login")
    Call<BetchaResponse> login(@Body LoginRequest betchaLoginRequest);


    // Users
    @POST("/account/info")
    Call<AccountInformation> getAccountInfo(@Query("authToken") String authToken);
    @POST("/account/edit")
    Call<BetchaResponse> editAccountInfo(@Body AccountInformation accountInformation, @Query("authToken") String authToken);
    @POST("/account/delete")
    Call<BetchaResponse> deleteAccount(@Query("authToken") String authToken);


    // Social Feed
    // TODO - Refactor to be POST with authToken
    @GET("/publicfeed")
    Call<PublicFeedResponse> getPublicFeed(); //@Query("authToken") String authToken


    // Private Feed
    @POST("/privatefeed")
    Call<List<PrivateFeedItem>> getPrivateFeed(@Query("authToken") String authToken);


    // Friends
    @POST("/friends")
    Call<List<FriendItem>> getFriends(@Query("authToken") String authToken);
    @POST("/friends/add/{id}")
    Call<BetchaResponse> addFriend(@Path("id") String friendID, @Query("authToken") String authToken);
    @POST("/friends/delete/{id}")
    Call<BetchaResponse> deleteFriend(@Path("id") String friendID, @Query("authToken") String authToken);
    @POST("/friends/info/{id}")
    Call<ProfileInformation> getFriendInfo(@Path("id") String friendID, @Query("authToken") String authToken);


    // Bets
    @POST("/bets/create")
    Call<BetchaResponse> createBet(@Body BetInformation betInformation, @Query("authToken") String authToken);
    @POST("/bets/delete/{id}")
    Call<BetchaResponse> deleteBet(@Path("id") String betID, @Query("authToken") String authToken);
    @POST("/bets/update")
    Call<BetchaResponse> updateBet(@Body BetInformation betInformation, @Query("authToken") String authToken);
    @POST("/bets/info/{id}")
    Call<BetInformation> getBetInfo(@Path("id") String betID, @Query("authToken") String authToken);


    // Comments
    @POST("/comments/create")
    Call<BetchaResponse> createComment(@Body BetComment betComment,
                                       @Query("authToken") String authToken);
    @POST("/comments/update")
    Call<BetchaResponse> updateComment(@Body BetComment betComment,
                                       @Query("authToken") String authToken);
    @POST("/comments/delete/{id}")
    Call<BetchaResponse> deleteComment(@Path("id") String betID, @Query("authToken") String authToken);


    // Likes
    @POST("likes/update")
    Call<BetchaResponse> postLike(@Body BetLike betLike, @Query("incrementalValue") String incValue,
                                  @Query("authToken") String authToken);




}
