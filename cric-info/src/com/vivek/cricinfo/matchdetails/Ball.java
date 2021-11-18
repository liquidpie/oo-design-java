package com.vivek.cricinfo.matchdetails;

import com.vivek.cricinfo.enums.BallType;
import com.vivek.cricinfo.users.Player;

import java.util.List;

public class Ball {
	private Player balledBy;
	private Player playedBy;
	private BallType type;

	private Wicket wicket;
	private List<Run> runs;
	private Commentary commentary;
}
