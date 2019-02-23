package com.drunkcode.ateam.api.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.dao.impl.IOperations;
import com.drunkcode.ateam.api.model.Performance;
@Component
public interface PerformanceDao extends IOperations<Performance>{
	
	public List<Performance> getPerformancesByParameters(int startingDay,int endingDay,List<Long> ids);

}
