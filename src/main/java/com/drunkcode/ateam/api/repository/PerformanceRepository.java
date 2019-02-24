package com.drunkcode.ateam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.model.Performance;
import com.drunkcode.ateam.api.model.PerformanceID;
@Component
public interface PerformanceRepository extends JpaRepository<Performance, PerformanceID>,PerformanceRepositoryCustom{
	
	

}
