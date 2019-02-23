package com.drunkcode.ateam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.model.Player;
@Component
public interface PlayerRepository extends JpaRepository<Player, Long>,PlayerRepositoryCustom {
	
}
