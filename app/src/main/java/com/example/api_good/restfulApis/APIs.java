package com.example.api_good.restfulApis;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface APIs {

  @Headers("X-RapidAPI-Key:76b22493f6mshc3de5f01b641d3ap19c5f9jsn96c70a2de77b")
  @GET("teams/team/{teamID}/")
  Call<JsonObject> getTeamByID(@Path("teamID") String teamID);

  @Headers("X-RapidAPI-Key:76b22493f6mshc3de5f01b641d3ap19c5f9jsn96c70a2de77b")
  @GET("players/")
  Call<JsonObject> getAllPlayers();

  @Headers("X-RapidAPI-Key:76b22493f6mshc3de5f01b641d3ap19c5f9jsn96c70a2de77b")
  @GET("teams/league/2/")
  Call<JsonObject> getAllteams();

  @Headers("X-RapidAPI-Key:76b22493f6mshc3de5f01b641d3ap19c5f9jsn96c70a2de77b")
  @GET("fixtures/live/{leagueID}/")
  Call<JsonObject> getLive_events(@Path("leagueID") int leagueID);

  @Headers("X-RapidAPI-Key:76b22493f6mshc3de5f01b641d3ap19c5f9jsn96c70a2de77b")
  @GET("leagues/league/{leagueID}/")
  Call<JsonObject> getLeagues(@Path("leagueID") int leagueID);

  @Headers("X-RapidAPI-Key:76b22493f6mshc3de5f01b641d3ap19c5f9jsn96c70a2de77b")
  @GET("fixtures/league/{leagueID}/{date}/")
  Call<JsonObject> getfixture_league(@Path("leagueID") int leagueID,@Path("date") String date);

  @Headers("X-RapidAPI-Key:76b22493f6mshc3de5f01b641d3ap19c5f9jsn96c70a2de77b")
  @GET("leagueTable/{leagueID}/")
  Call<JsonObject> get_Order(@Path("leagueID") int leagueID);

  @Headers("X-RapidAPI-Key:76b22493f6mshc3de5f01b641d3ap19c5f9jsn96c70a2de77b")
  @GET("fixtures/league/{leagueID}/")
  Call<JsonObject> get_matches(@Path("leagueID") int leagueID);

  @Headers("X-RapidAPI-Key:76b22493f6mshc3de5f01b641d3ap19c5f9jsn96c70a2de77b")
  @GET("events/{fixture_id}/")
  Call<JsonObject> get_Events(@Path("fixture_id") int leagueID);

}