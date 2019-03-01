package com.drunkcode.ateam.api.repository;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.model.LeagueSeason;

@Component
public interface LeagueSeasonRepository extends JpaRepository<LeagueSeason, Long>{
	public LeagueSeason findByStartingDateAfterAndEndingDateBefore(Calendar startingDate,Calendar endingDate);
	public LeagueSeason findByStartingYear(Long startingYear);
}
