package com.example.api_good.restfulApis;

import android.util.Log;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private Call<JsonObject> call;

    private RetrofitClient() {
        this.retrofit = new Retrofit.Builder()
                // .client(new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build()) // time out
                .baseUrl(selectBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        // on creation
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    private String selectBaseUrl() {

        String BASE_URL = "https://api-football-v1.p.rapidapi.com/v2/";

        //String BASE_URL="https://api-football-v1.p.rapidapi.com/v2/";

        return BASE_URL;
    }

    public void get_matches(int leagueID,HandleResponse handleResponse){
        call=getInstance().getAPI().get_matches(leagueID);
        call.enqueue(createWebserviceCallback(handleResponse));
    }

    public void getTeamByID(String teamID , HandleResponse HandleResponse) {
        call = getInstance().getAPI().getTeamByID(teamID);
        call.enqueue(createWebserviceCallback(HandleResponse));
    }

    public void getAllPlayer( HandleResponse HandleResponse) {
        call = getInstance().getAPI().getAllPlayers();
        call.enqueue(createWebserviceCallback(HandleResponse));
    }

    public void getAllTeams( HandleResponse HandleResponse) {
        call = getInstance().getAPI().getAllteams();
        call.enqueue(createWebserviceCallback(HandleResponse));
    }
    public void getLive_events(int league_id,HandleResponse HandleResponse) {
        call = getInstance().getAPI().getLive_events(league_id);
        call.enqueue(createWebserviceCallback(HandleResponse));
    }

    public void getleagues(int leagueID,HandleResponse HandleResponse) {
        call = getInstance().getAPI().getLeagues(leagueID);
        call.enqueue(createWebserviceCallback(HandleResponse));
    }

    public void getfixture_league(int leagueID,String date,HandleResponse HandleResponse) {
        call = getInstance().getAPI().getfixture_league(leagueID,date);
        call.enqueue(createWebserviceCallback(HandleResponse));
    }
    public void get_Order(int leagueID,HandleResponse HandleResponse) {
        call = getInstance().getAPI().get_Order(leagueID);
        call.enqueue(createWebserviceCallback(HandleResponse));
    }


    public void get_Events(int fixture_id,HandleResponse HandleResponse) {
        call = getInstance().getAPI().get_Events(fixture_id);
        call.enqueue(createWebserviceCallback(HandleResponse));
    }


    private APIs getAPI() {
        return retrofit.create(APIs.class);
    }

    private Callback<JsonObject> createWebserviceCallback(final HandleResponse HandleResponse) {
        return new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) { // code == 200


                        // dynamic with each call


                        Log.d("body", response.body().toString());
                        HandleResponse.ResponseOK(response.body());


                } else { // code != 200

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) { // connection errors
                Log.d("onFailure: ", t.getMessage() + "");
                // dynamic with each call
                // HandleResponse.handleConnectionErrors(context.getString(R.string.no_connection));
                HandleResponse.ResponseError(t.getMessage());

            }
        };
    }

    public void cancelCall() {
        if (call != null) {
            if (!call.isCanceled()) {
                call.cancel();
            }
        }
    }
}
