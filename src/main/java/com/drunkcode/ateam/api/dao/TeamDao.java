package com.drunkcode.ateam.api.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.dao.impl.IOperations;
import com.drunkcode.ateam.api.model.Team;
@Component
@Transactional
public interface TeamDao extends IOperations<Team>{
	public Team findTeamByName(String name);
}
