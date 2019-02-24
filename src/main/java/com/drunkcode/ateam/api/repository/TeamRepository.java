package com.drunkcode.ateam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.model.Team;
@Component
@Transactional
public interface TeamRepository extends JpaRepository<Team, Long>,TeamRepositoryCustom{
	
}
