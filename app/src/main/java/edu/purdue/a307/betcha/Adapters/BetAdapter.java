package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.Activities.ActionBarActivity;
import edu.purdue.a307.betcha.Activities.BetActivity;
import edu.purdue.a307.betcha.Activities.BetchaActivity;
import edu.purdue.a307.betcha.Activities.EditBetActivity;
import edu.purdue.a307.betcha.Activities.JoinBetActivity;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Enums.AdapterType;
import edu.purdue.a307.betcha.Enums.BetAdapterType;
import edu.purdue.a307.betcha.Enums.JoinBetType;
import edu.purdue.a307.betcha.Fragments.BetInvitesFragment;
import edu.purdue.a307.betcha.Helpers.BDialog;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Listeners.AlertDialogListener;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetDeleteRequest;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.CompleteBetRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kyleohanian on 10/3/17.
 */

public class BetAdapter extends RecyclerView.Adapter<BetAdapter.MyViewHolder> {

    private List<Bet> items;
    private Activity activity;
    private String selfToken;
    public BetAdapterType type;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv)
        CardView cardView;
        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.textAmount)
        TextView textAmount;
        @BindView(R.id.textSpotsLeft)
        TextView textSpotsLeft;
        @BindView(R.id.buttonMenu)
        ImageButton buttonMenu;
        CircleImageView icon;
        @BindView(R.id.likeButton)
        ImageButton likeButton;
        @BindView(R.id.commentButton)
        ImageView commentButton;


        boolean isLiked;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            icon = view.findViewById(R.id.iconImage);
            isLiked = false;

        }
    }


    public BetAdapter(Activity activity, List<Bet> items, String selfToken, BetAdapterType type) {
        this.activity = activity;
        this.items = items;
        this.selfToken = selfToken;
        this.type = type;
    }

    public void setItems(List<Bet> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public BetAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bet_information, parent, false);
        return new BetAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BetAdapter.MyViewHolder holder, final int position) {
        final Bet info = items.get(position);
        holder.textTitle.setText(info.getTitle());
        holder.textAmount.setText("$"+info.getAmount());
        int spotsLeft = Integer.parseInt(info.getMaxUsers()) - info.users.size();
        holder.textSpotsLeft.setText("Spots Left: " + String.valueOf(spotsLeft));
        // TODO: needs to be actual spots left
//        holder.textSpotsLeft.setText("Spots Left: " + info.maxUsers);
        if(info.getCreatorId() == Integer.parseInt(SharedPrefsHelper.getUserID(activity))) {
            holder.buttonMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Creating the instance of PopupMenu
                    PopupMenu popup = new PopupMenu(activity, holder.buttonMenu);
                    popup.inflate(R.menu.item_bet_menu);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_item_edit:
                                    Intent myIntent = new Intent(activity, EditBetActivity.class);
                                    String json = new Gson().toJson(info);
                                    myIntent.putExtra("jsonObj", json);
                                    activity.startActivity(myIntent);
                                    break;
                                case R.id.menu_item_complete:
                                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                    builder.setMessage("Which side won?");
                                    builder.setTitle("Completing Bet");
                                    builder.setPositiveButton(info.getSideA(), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(final DialogInterface dialogInterface, int i) {
                                            BDialog.confirmBet(activity, info.getSideA(), new AlertDialogListener() {
                                                @Override
                                                public void onPositive() {
                                                    String authToken = SharedPrefsHelper.getSelfToken(activity);
                                                    ApiHelper.getInstance(activity).completeBet(new CompleteBetRequest(authToken,
                                                            String.valueOf(info.getId()), "0")).enqueue(new Callback<BetchaResponse>() {
                                                        @Override
                                                        public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                                            if (response.code() != 200) {
                                                                BToast.makeError(activity, activity.getString(R.string.bet_completion_error));
                                                                return;
                                                            }
                                                            BToast.makeSuccess(activity, activity.getString(R.string.bet_completion_success));
                                                            dialogInterface.dismiss();
                                                            if(activity instanceof ActionBarActivity) {
                                                                ((ActionBarActivity)activity).setStuffUp();
                                                                Log.d("ACTIONBARACTIVITY", "Action Bar Activity Set Up");
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                                            BToast.makeServerError(activity);
                                                            dialogInterface.dismiss();
                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onNegative() {}
                                            });
                                        }
                                    });
                                    builder.setNegativeButton(info.getSideB(), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(final DialogInterface dialogInterface, int i) {
                                            BDialog.confirmBet(activity, info.getSideB(), new AlertDialogListener() {
                                                @Override
                                                public void onPositive() {
                                                    String authToken = SharedPrefsHelper.getSelfToken(activity);
                                                    ApiHelper.getInstance(activity).completeBet(new CompleteBetRequest(authToken,
                                                            String.valueOf(info.getId()), "1")).enqueue(new Callback<BetchaResponse>() {
                                                        @Override
                                                        public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                                            if (response.code() != 200) {
                                                                BToast.makeError(activity, activity.getString(R.string.bet_completion_error));
                                                                return;
                                                            }
                                                            BToast.makeSuccess(activity, activity.getString(R.string.bet_completion_success));
                                                            dialogInterface.dismiss();
                                                            if(activity instanceof ActionBarActivity) {
                                                                ((ActionBarActivity)activity).setStuffUp();
                                                                Log.d("ACTIONBARACTIVITY", "Action Bar Activity Set Up");
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                                            BToast.makeServerError(activity);
                                                            dialogInterface.dismiss();
                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onNegative() {}
                                            });
                                        }
                                    });
                                    builder.show();
                                    break;

                                case R.id.menu_item_delete:
                                    AlertDialog.Builder builderNew = new AlertDialog.Builder(activity);
                                    builderNew.setMessage("Are you sure you want to delete the bet?");
                                    builderNew.setTitle("Delete Bet");
                                    builderNew.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(final DialogInterface dialogInterface, int i) {
                                            BDialog.deleteBet(activity, new AlertDialogListener() {
                                                @Override
                                                public void onPositive() {
                                                    String authToken = SharedPrefsHelper.getSelfToken(activity);
                                                    ApiHelper.getInstance(activity).deleteBet(new BetDeleteRequest(authToken,
                                                            info.getId())).enqueue(new Callback<BetchaResponse>() {
                                                        @Override
                                                        public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                                            if (response.code() != 200) {
                                                                BToast.makeError(activity, activity.getString(R.string.delete_bet_error));
                                                                return;
                                                            }
                                                            BToast.makeSuccess(activity, activity.getString(R.string.delete_bet_success));
                                                            dialogInterface.dismiss();
                                                            if(activity instanceof ActionBarActivity) {
                                                                ((ActionBarActivity)activity).setStuffUp();
                                                                Log.d("ACTIONBARACTIVITY", "Action Bar Activity Set Up");
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                                            BToast.makeServerError(activity);
                                                            dialogInterface.dismiss();
                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onNegative() {}
                                            });
                                        }
                                    });
                                    builderNew.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(final DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    builderNew.show();
                                    break;

                            }
                            return false;
                        }
                    });

                    popup.show();
                }
            });
        }
        else {
            holder.buttonMenu.setVisibility(View.INVISIBLE);
        }
        IconGenerator.setImageWithPredefinedNums(activity,holder.icon, Integer.parseInt(info.getColor()), Integer.parseInt(info.getIcon()));
        holder.likeButton.setVisibility(View.INVISIBLE);
        holder.commentButton.setVisibility(View.INVISIBLE);

        if(type == BetAdapterType.USERPROFILE) {
            if(items.get(position).isComplete()) {
                holder.cardView.setCardBackgroundColor(Color.LTGRAY);
                holder.buttonMenu.setBackgroundColor(Color.LTGRAY);
            }
        }


        if(type == BetAdapterType.PENDING) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(activity, JoinBetActivity.class);
                    // 1 = Accept
                    myIntent.putExtra("type", 1);
                    Gson gson = new Gson();
                    myIntent.putExtra("Obj",gson.toJson(info));
                    myIntent.putExtra("selfToken",selfToken);
                    activity.startActivity(myIntent);
                }
            });
        } else {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(activity, BetActivity.class);
                    Gson gson = new Gson();
                    myIntent.putExtra("Object",gson.toJson(info));
                    myIntent.putExtra("selfToken",selfToken);
                    activity.startActivity(myIntent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
