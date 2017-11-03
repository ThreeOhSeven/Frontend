package edu.purdue.a307.betcha.Api;

import edu.purdue.a307.betcha.Models.AcceptBetRequest;
import edu.purdue.a307.betcha.Models.AccountInformation;
import edu.purdue.a307.betcha.Models.AddFriendRequest;
import edu.purdue.a307.betcha.Models.BetComment;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.BetInformationRequest;
import edu.purdue.a307.betcha.Models.BetLikeRequest;
import edu.purdue.a307.betcha.Models.BetUpdateRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.CompleteBetRequest;
import edu.purdue.a307.betcha.Models.CreateBetResponse;
import edu.purdue.a307.betcha.Models.EmailResponse;
import edu.purdue.a307.betcha.Models.FriendItems;
import edu.purdue.a307.betcha.Models.JoinBetRequest;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.ProfileInformation;
import edu.purdue.a307.betcha.Models.Bets;
import edu.purdue.a307.betcha.Models.RejectBetRequest;
import edu.purdue.a307.betcha.Models.SendBetRequest;
import edu.purdue.a307.betcha.Models.TransactionBalance;
import edu.purdue.a307.betcha.Models.UserEmailRequest;
import edu.purdue.a307.betcha.Models.UserID;
import edu.purdue.a307.betcha.Models.UserIDRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kyleohanian on 9/20/17.
 */

public interface BetchaApi {

    // Login
    @POST("/auth/login")
    Call<BetchaResponse> login(@Body LoginRequest betchaLoginRequest);


    // Account Information
    @POST("/account/info")
    Call<AccountInformation> getAccountInfo(@Query("authToken") String authToken);
    @POST("/account/edit")
    Call<BetchaResponse> editAccountInfo(@Body AccountInformation accountInformation, @Query("authToken") String authToken);
    @POST("/account/delete")
    Call<BetchaResponse> deleteAccount(@Body LoginRequest authToken);


    // User Info
    @POST("/users/get/id")
    Call<UserID> getIDByUser(@Body UserEmailRequest request);
    @POST("/users/get/email")
    Call<EmailResponse> getUserByID(@Body UserIDRequest request);



    // Social Feed
    @POST("/publicfeed")
    Call<Bets> getPublicFeed(@Body LoginRequest request);


    // Private Feed
    @POST("/privatefeed")
    Call<Bets> getPrivateFeed(@Body LoginRequest request);

    @POST("/open")
    Call<Bets> getMyOpenBets(@Body LoginRequest authToken);

    @POST("/completed")
    Call<Bets> getMyCompletedBets(@Body LoginRequest authToken);

    @POST("/pending")
    Call<Bets> getMyPendingBets(@Body LoginRequest authToken);


    // Friends
    @POST("/friends/")
    Call<FriendItems> getFriends(@Body LoginRequest authToken);
    @POST("/friends/requests")
    Call<FriendItems> getFriendRequests(@Body LoginRequest authToken);
    @POST("/friends/add/")
    Call<BetchaResponse> addFriend(@Body AddFriendRequest addFriendRequest);
    @POST("/friends/delete/{id}")
    Call<BetchaResponse> deleteFriend(@Path("id") String friendID, @Body LoginRequest authToken);
    @POST("/friends/info/{id}")
    Call<ProfileInformation> getFriendInfo(@Path("id") String friendID, @Query("authToken") String authToken);


    // Bets
    @POST("/bets/create")
    Call<CreateBetResponse> createBet(@Body BetInformationRequest betInformation);
    @POST("/bets/delete/{id}")
    Call<BetchaResponse> deleteBet(@Path("id") String betID, @Query("authToken") String authToken);
    @POST("/bets/update")
    Call<CreateBetResponse> updateBet(@Body BetUpdateRequest betUpdateRequest);
    @POST("/bets/info/{id}")
    Call<BetInformation> getBetInfo(@Path("id") String betID, @Query("authToken") String authToken);

    @POST("/bets/join")
    Call<BetchaResponse> joinBet(@Body JoinBetRequest jbr);

    @POST("/bets/send")
    Call<BetchaResponse> sendBet(@Body SendBetRequest sbr);

    @POST("/bets/accept")
    Call<BetchaResponse> acceptBet(@Body AcceptBetRequest abr);

    @POST("/bets/reject")
    Call<BetchaResponse> rejectBet(@Body RejectBetRequest rbr);

    @POST("/bets/complete")
    Call<BetchaResponse> completeBet(@Body CompleteBetRequest rbr);

    @POST("/bets/profile")
    Call<Bets> getProfileBets(@Body LoginRequest request);


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
    @POST("like/update")
    Call<BetchaResponse> postLike(@Body BetLikeRequest betLikeRequest);

    @POST("/transaction/getPoints")
    Call<TransactionBalance> getBalance(@Body LoginRequest loginRequest);




}
