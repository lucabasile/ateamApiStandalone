package com.drunkcode.ateam.api.repository;

import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.dao.impl.IOperations;
import com.drunkcode.ateam.api.model.LeagueSeason;

@Component
public interface LeagueSeasonDao extends IOperations<LeagueSeason>{
	public LeagueSeason getCurrentSeason();
}
