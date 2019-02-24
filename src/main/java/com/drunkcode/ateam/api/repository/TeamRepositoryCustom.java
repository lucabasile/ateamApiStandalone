package com.drunkcode.ateam.api.repository;

import com.drunkcode.ateam.api.model.Team;

public interface TeamRepositoryCustom {
	public Team findTeamByName(String name);
}
