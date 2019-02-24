package com.drunkcode.ateam.api.repository;

import java.util.List;

import com.drunkcode.ateam.api.model.Performance;

public interface PerformanceRepositoryCustom {
	public List<Performance> getPerformancesByParameters(int startingDay,int endingDay,List<Long> ids);
}
