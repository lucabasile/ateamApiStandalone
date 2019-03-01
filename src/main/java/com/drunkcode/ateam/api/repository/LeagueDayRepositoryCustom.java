package com.drunkcode.ateam.api.repository;

import java.util.List;

import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueSeason;


public interface LeagueDayRepositoryCustom {
	public LeagueDay findByDayIndex(int dayIndex,LeagueSeason season);
	//public List<LeagueDay> findByDayIndex(List<Integer> dayIndexes,LeagueSeason season);
}
