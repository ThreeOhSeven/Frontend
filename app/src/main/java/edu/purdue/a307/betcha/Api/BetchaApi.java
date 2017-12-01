package edu.purdue.a307.betcha.Api;

import edu.purdue.a307.betcha.Models.AccountInformation;
import edu.purdue.a307.betcha.Models.AddFriendRequest;
import edu.purdue.a307.betcha.Models.ApiResponse;
import edu.purdue.a307.betcha.Models.BetComment;
import edu.purdue.a307.betcha.Models.BetCommentsGetRequest;
import edu.purdue.a307.betcha.Models.BetDeleteRequest;
import edu.purdue.a307.betcha.Models.BetCommentAddRequest;
import edu.purdue.a307.betcha.Models.BetComments;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.BetInformationRequest;
import edu.purdue.a307.betcha.Models.BetLikeRequest;
import edu.purdue.a307.betcha.Models.BetUpdateRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.Bets;
import edu.purdue.a307.betcha.Models.CompleteBetRequest;
import edu.purdue.a307.betcha.Models.CreateBetResponse;
import edu.purdue.a307.betcha.Models.EmailResponse;
import edu.purdue.a307.betcha.Models.FeedbackRequest;
import edu.purdue.a307.betcha.Models.FriendItems;
import edu.purdue.a307.betcha.Models.JoinBetRequest;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.NotificationsResponse;
import edu.purdue.a307.betcha.Models.PaymentRequest;
import edu.purdue.a307.betcha.Models.ProfileInformation;
import edu.purdue.a307.betcha.Models.RecordResponse;
import edu.purdue.a307.betcha.Models.RejectBetRequest;
import edu.purdue.a307.betcha.Models.SendBetRequest;
import edu.purdue.a307.betcha.Models.TransactionBalance;
import edu.purdue.a307.betcha.Models.UpdateIdRequest;
import edu.purdue.a307.betcha.Models.User;
import edu.purdue.a307.betcha.Models.UserEmailRequest;
import edu.purdue.a307.betcha.Models.UserID;
import edu.purdue.a307.betcha.Models.UserIDRequest;
import edu.purdue.a307.betcha.Models.UserProfileRequest;
import edu.purdue.a307.betcha.Models.UserProfileResponse;
import edu.purdue.a307.betcha.Models.Users;
import retrofit2.Call;
import retrofit2.http.Body;
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


    // Account Information
    @POST("/account/info")
    Call<AccountInformation> getAccountInfo(@Query("authToken") String authToken);
    @POST("/account/edit")
    Call<BetchaResponse> editAccountInfo(@Body AccountInformation accountInformation, @Query("authToken") String authToken);
    @POST("/account/delete")
    Call<BetchaResponse> deleteAccount(@Body LoginRequest authToken);

    @POST("/info")
    Call<User> getUserInfo(@Body LoginRequest loginRequest);



    // User Info
    @POST("/users/get/id")
    Call<UserID> getIDByUser(@Body UserEmailRequest request);
    @POST("/users/get/email")
    Call<EmailResponse> getUserByID(@Body UserIDRequest request);

    @POST("/users/updateDevice")
    Call<ApiResponse> postDeviceId(@Body UpdateIdRequest request);

    @POST("/users/get/record")
    Call<RecordResponse> getRecord(@Body LoginRequest request);


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
    @POST("/bets/delete")
    Call<BetchaResponse> deleteBet(@Body BetDeleteRequest betDeleteRequest);
    @POST("/bets/update")
    Call<CreateBetResponse> updateBet(@Body BetUpdateRequest betUpdateRequest);
    @POST("/bets/info/{id}")
    Call<BetInformation> getBetInfo(@Path("id") String betID, @Query("authToken") String authToken);

    @POST("/bets/join")
    Call<BetchaResponse> joinBet(@Body JoinBetRequest jbr);

    @POST("/bets/send")
    Call<BetchaResponse> sendBet(@Body SendBetRequest sbr);

    @POST("/bets/accept")
    Call<BetchaResponse> acceptBet(@Body JoinBetRequest abr);

    @POST("/bets/reject")
    Call<BetchaResponse> rejectBet(@Body RejectBetRequest rbr);

    @POST("/bets/complete")
    Call<BetchaResponse> completeBet(@Body CompleteBetRequest rbr);

    @POST("/bets/profile")
    Call<Bets> getProfileBets(@Body LoginRequest request);

    @POST("/bets/userProfile")
    Call<UserProfileResponse> getUserProfileBets(@Body UserProfileRequest request);


    // Reusing RejectBetRequest because it has same schema
    @POST("/bets/friendsNot")
    Call<Users> getFriendsNotInBet(@Body RejectBetRequest request);


    // Comments
    @POST("/comment/add")
    Call<BetchaResponse> addComment(@Body BetCommentAddRequest request);
    @POST("/comment/update")
    Call<BetchaResponse> deleteComment(@Body BetComment betComment);
    @POST("/comment/get")
    Call<BetComments> getComments(@Body BetCommentsGetRequest request);


    @POST("/transaction/payment/charge")
    Call<BetchaResponse> chargeUser(@Body PaymentRequest request);

    @POST("/transaction/payment/payout")
    Call<BetchaResponse> payoutUser(@Body PaymentRequest request);


    // Likes
    @POST("like/update")
    Call<BetchaResponse> postLike(@Body BetLikeRequest betLikeRequest);

    @POST("/transaction/getPoints")
    Call<TransactionBalance> getBalance(@Body LoginRequest loginRequest);


    // Feedback
    @POST("/feedback")
    Call<BetchaResponse> sendFeedback(@Body FeedbackRequest feedbackRequest);

    // Notifications
    @POST("/notifications/get")
    Call<NotificationsResponse> getNotifications(@Body LoginRequest loginRequest);



}
