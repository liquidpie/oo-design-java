package com.vivek.cricinfo.stats;

import java.util.Date;

public abstract class Stat {
	
	private Date statCreationDate;
	private Date statLastUpdateDate;
	
	public abstract void saveStat();
}
