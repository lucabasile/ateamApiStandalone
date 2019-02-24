package com.drunkcode.ateam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.model.LeagueSeason;

@Component
public interface LeagueSeasonRepository extends JpaRepository<LeagueSeason, Long>,LeagueSeasonRepositoryCustom{
	
}
