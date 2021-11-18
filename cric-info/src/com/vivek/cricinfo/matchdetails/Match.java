package com.vivek.cricinfo.matchdetails;

import java.util.Date;
import java.util.List;

import com.vivek.cricinfo.enums.MatchResult;
import com.vivek.cricinfo.stats.MatchStat;
import com.vivek.cricinfo.teamdetails.Playing11;
import com.vivek.cricinfo.users.Commentator;
import com.vivek.cricinfo.users.Referee;
import com.vivek.cricinfo.users.Umpire;

public abstract class Match {
	private int number;
	private Date startTime;
	private MatchResult result;
	private Playing11[] teams;
	private List<Inning> innings;
	private List<Umpire> umpires;
	private Referee referee;
	private List<Commentator> commentators;
	private List<MatchStat> matchStats;

	public boolean assignStadium(Stadium stadium) {}

	public boolean assignReferee(Referee referee) {}
}
