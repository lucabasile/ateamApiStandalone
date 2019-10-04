package com.drunkcode.ateam.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.model.FantasyTeam;

@Component
@Transactional
public interface FantasyTeamRepository extends JpaRepository<FantasyTeam,Long>{

}
