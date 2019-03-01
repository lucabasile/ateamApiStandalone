package com.drunkcode.ateam.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.model.Player;
@Component
public interface PlayerRepository extends JpaRepository<Player, Long>,PlayerRepositoryCustom {
	public List<Player> findByPlayerIdIn(List<Long> ids);
}
