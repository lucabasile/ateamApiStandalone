package com.drunkcode.ateam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drunkcode.ateam.api.model.LeagueMatch;

public interface LeagueMatchRepository extends JpaRepository<LeagueMatch, Long>,LeagueMatchRepositoryCustom{

}
