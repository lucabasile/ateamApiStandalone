package com.drunkcode.ateam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drunkcode.ateam.api.model.LeagueDay;

public interface LeagueDayRepository extends JpaRepository<LeagueDay, Long>,LeagueDayRepositoryCustom{
	
}
