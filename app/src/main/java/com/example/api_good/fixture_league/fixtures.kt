package com.example.api_good.fixture_league

class fixtures(fixture_id:Int,league_id:Int,event_date:String,event_timestamp:Int,firstHalfStart:Int,secondHalfStart:Int,round:String,statusShort : String,elapsed:Int,homeTeam:homeTeam,awayTeam: awayTeam,goalsHomeTeam:String,goalsAwayTeam:String,score: score){
    var fixture_id=fixture_id
    var league_id=league_id
    var event_date=event_date
    var event_timestamp=event_timestamp
    var round=round
    var statusShort=statusShort
    var homeTeam = homeTeam
    var awayTeam=awayTeam
    var goalsHomeTeam=goalsHomeTeam
    var goalsAwayTeam=goalsAwayTeam
    var score=score
    var firstHalfStart=firstHalfStart
    var secondHalfStart=secondHalfStart
    var elapsed=elapsed
}