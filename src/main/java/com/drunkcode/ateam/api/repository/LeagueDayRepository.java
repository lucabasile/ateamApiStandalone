package com.drunkcode.ateam.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueSeason;

public interface LeagueDayRepository extends JpaRepository<LeagueDay, Long>,LeagueDayRepositoryCustom{
	public List<LeagueDay> findBySeasonAndDayIndexIn(LeagueSeason season,List<Integer> dayIndexes);
}
