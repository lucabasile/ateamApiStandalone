package com.drunkcode.ateam.api.repository;

import java.util.List;

import com.drunkcode.ateam.api.model.Player;

public interface PlayerRepositoryCustom {
	
	public List<Player> findPlayersByRole(String role);
}
