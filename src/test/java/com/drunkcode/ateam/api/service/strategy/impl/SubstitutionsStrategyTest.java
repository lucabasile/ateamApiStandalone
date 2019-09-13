package com.drunkcode.ateam.api.service.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drunkcode.ateam.api.service.dto.FantasyFormation;
import com.drunkcode.ateam.api.service.dto.FantasyPlayer;

class SubstitutionsStrategyTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	SubstitutionsStrategy substitutionsStrategy = new SubstitutionsStrategy();

	@DisplayName("Check sustitutions")
	@ParameterizedTest(name = "{index} => playersList={0}, benchList={1},expected={2}")
	@CsvFileSource (resources="/substitutionsTest.csv")
	void test(String playersList,String benchList,String expected) {
		
		FantasyFormation formation = new FantasyFormation();
		List<FantasyPlayer> players= new ArrayList<FantasyPlayer>();
		long id=0;
		for(Object p: Arrays.asList(playersList.split(";"))){
			String[] fields = p.toString().split("%");
		
			Arrays.asList(fields).stream().forEach(ff->{System.out.println(ff);});
			FantasyPlayer player = new FantasyPlayer(id++,fields[0],fields[1],fields[2].equalsIgnoreCase("null")?null:Double.valueOf(fields[2]),null);
			players.add(player);
		}
		
		formation.setPlayers(players);
		
		List<FantasyPlayer> bench= new ArrayList<FantasyPlayer>();
		for(Object p: Arrays.asList(benchList.split(";"))){
			String[] fields = p.toString().split("%");
			FantasyPlayer player = new FantasyPlayer(id++,fields[0],fields[1],fields[2].equalsIgnoreCase("null")?null:Double.valueOf(fields[2]),null);
			bench.add(player);
		}

		formation.setBench(bench);
		
		FantasyFormation finalFormation = substitutionsStrategy.finalizeTeam(formation );
		
		formation.getPlayers().stream().forEach(g->{System.out.println(g.toString());});
		
		int index=0;
		for(Object p: Arrays.asList(expected.split(";"))){
			String[] fields = p.toString().split("%");
		
			
			FantasyPlayer player = finalFormation.getPlayers().get(index++);
			System.out.println(fields[1]+" "+fields[2]+" got :"+player.getName()+" "+player.getEvaluation() );
			assertTrue(player.getName().equalsIgnoreCase(fields[1]));
			if(!fields[2].equalsIgnoreCase("null"))
				assertTrue(player.getEvaluation().equals(Double.valueOf(fields[2])));
			else
				assertNull(player.getEvaluation());
		}
		
	}

}
