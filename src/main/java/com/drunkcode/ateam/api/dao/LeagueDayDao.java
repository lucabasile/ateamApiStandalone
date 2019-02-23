package com.drunkcode.ateam.api.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.dao.impl.IOperations;
import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueSeason;

@Component
public interface LeagueDayDao extends IOperations<LeagueDay>{
	public LeagueDay findByDayIndex(int dayIndex,LeagueSeason season);
	public List<LeagueDay> findByDayIndex(List<Integer> dayIndexes,LeagueSeason season);
}
