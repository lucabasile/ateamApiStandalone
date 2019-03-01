package com.drunkcode.ateam.api.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueMatch;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.model.Performance;
import com.drunkcode.ateam.api.model.PerformanceID;
import com.drunkcode.ateam.api.model.Player;
import com.drunkcode.ateam.api.model.Team;
import com.drunkcode.ateam.api.repository.LeagueDayRepository;
import com.drunkcode.ateam.api.repository.LeagueMatchRepository;
import com.drunkcode.ateam.api.repository.LeagueSeasonRepository;
import com.drunkcode.ateam.api.repository.PerformanceRepository;
import com.drunkcode.ateam.api.repository.PlayerRepository;
import com.drunkcode.ateam.api.repository.TeamRepository;


@RestController
public class UploadController {

	private static final String VOTI_GENTILMENTE_OFFERTI_DA_WWW_FANTAGAZZETTA_COM = "E' DA CONSIDERARSI AD USO PERSONALE ESCLUSIVO DEGLI ISCRITTI DI FANTAGAZZETTA.COM";
	private static final String ACTUAL_TEAM = "actualTeam";
	private static final String ROLE = "role";
	private static final String NAME = "name";
	private static final String PLAYER_ID = "playerId";
	
	private static final String EVALUATION="evaluation";
	private static final String GOALS_SCORED="goalsScored";
	private static final String GOALS_ALLOWED="goalsAllowed";
	private static final String PENALTIES_SCORED="penaltiesScored";
	private static final String PENALTIES_MISSED="penaltiesMissed";
	private static final String PENALTIES_SAVED="penaltiesSaved";
	private static final String AUTOGOALS="autoGoals";
	private static final String YELLOW_CARDS="yellowCards";
	private static final String RED_CARDS="redCards";
	private static final String ASSISTS = "assists";
	private static final String ASSISTS_FROM_KICK="assistsFromKick";
	private static final String GOAL_OF_VICTORY="goalOfVictory";
	private static final String GOAL_OF_TIE="goalOfTie";
	
	
//	final String BASE_FILE_PATH="/Users/lucabasile/Downloads/temporaryFiles";
	final String BASE_FILE_PATH="C:/test_tmp/temporaryFiles";
	
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	TeamRepository teamRepository;
	@Autowired
	LeagueSeasonRepository seasonRepository;
	@Autowired
	LeagueDayRepository dayRepository;
	@Autowired
	LeagueMatchRepository matchRepository;
	@Autowired
	PerformanceRepository performanceRepository;
	
	
    @RequestMapping(value="/singleUpload")
    public String singleUpload(){
    	return "singleUpload";
    }
    @RequestMapping(value="/singleSave", method=RequestMethod.POST )
    public @ResponseBody String singleSave(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc,@RequestParam("startingRow") int startingRow,@RequestParam("fieldsString") String fieldsString ){
    	System.out.println("File Description:"+desc);
    	String fileName = file.getOriginalFilename();
    	
    	if (!file.isEmpty()&&fileName.endsWith(".xlsx")) {
            try {
            	//playerId;name;role;birthDate;actualTeam
            	final List<String> fields=Arrays.asList(fieldsString.split(";"));
            	Map<String,String> namesMap=new HashMap<String, String>(){{
            		put(PLAYER_ID,fields.get(0));
            		put(NAME,fields.get(1));
            		put(ROLE,fields.get(2));
            		put(ACTUAL_TEAM,fields.get(3));
            	}};
            	
                
                XSSFSheet sheet = getSheet(file);
                
                Map<String,Integer> columnMap= new HashMap<String, Integer>();
                Iterator<Row> rowIterator = sheet.rowIterator();
                int rowIndex = 0;
                while(rowIndex<startingRow){
                	rowIndex++;
                	rowIterator.next();
                }
                while (rowIterator.hasNext()){
                	Row actualRow = rowIterator.next();
                	Iterator<Cell> cellIterator = actualRow.cellIterator();
                	if(rowIndex==startingRow){
                		while(cellIterator.hasNext()){
                			Cell cell = cellIterator.next();
                			String actualValue=cell.getStringCellValue();
                    		if(actualValue.equalsIgnoreCase(namesMap.get(PLAYER_ID)))
                    			columnMap.put(PLAYER_ID,cell.getColumnIndex() );
                    		else if(actualValue.equalsIgnoreCase(namesMap.get(NAME)))
                    			columnMap.put(NAME,cell.getColumnIndex() );
                    		else if(actualValue.equalsIgnoreCase(namesMap.get(ACTUAL_TEAM)))
                    			columnMap.put(ACTUAL_TEAM,cell.getColumnIndex() );
                    		else if(actualValue.equalsIgnoreCase(namesMap.get(ROLE)))
                    			columnMap.put(ROLE,cell.getColumnIndex() );
                    	}
                		
                	}else{
                		createPlayer(columnMap, actualRow);
                			
                	}
                	rowIndex++;
                }
                
                
                return "You have successfully uploaded " + fileName;
            } catch (Exception e) {
                return "You failed to upload " + fileName + ": " + e.getMessage();
            }
        } else {
            return "Unable to upload. File is empty.";
        }
    }
	private Player createPlayer(Map<String, Integer> columnMap, Row actualRow) {
		Player player = new Player();
		player.setPlayerId((long)actualRow.getCell(columnMap.get(PLAYER_ID)).getNumericCellValue());
		player.setName(actualRow.getCell(columnMap.get(NAME)).getStringCellValue());
		player.setRole(actualRow.getCell(columnMap.get(ROLE)).getStringCellValue());
		String actualTeamName = actualRow.getCell(columnMap.get(ACTUAL_TEAM)).getStringCellValue();
		Team actualTeam=teamRepository.findTeamByName(actualTeamName);
		if (actualTeam==null)
			{
				actualTeam=new Team();
				actualTeam.setName(actualTeamName);
				teamRepository.save(actualTeam);
			}
		player.setTeam(teamRepository.findTeamByName(actualTeamName));
		playerRepository.save(player);
		return player;
	}
	
	private Player createPlayer(Map<String, Integer> columnMap, Row actualRow,String teamName) {
		Player player = new Player();
		player.setPlayerId((long)actualRow.getCell(columnMap.get(PLAYER_ID)).getNumericCellValue());
		player.setName(actualRow.getCell(columnMap.get(NAME)).getStringCellValue());
		player.setRole(actualRow.getCell(columnMap.get(ROLE)).getStringCellValue());
		Team actualTeam=teamRepository.findTeamByName(teamName);
		if (actualTeam==null)
			{
				actualTeam=new Team();
				actualTeam.setName(teamName);
				teamRepository.save(actualTeam);
			}
		player.setTeam(teamRepository.findTeamByName(teamName));
		playerRepository.save(player);
		return player;
	}
	
	private XSSFSheet getSheet(MultipartFile file)
			throws IOException, FileNotFoundException {
		String endFilePath=null;
		String fileName=file.getOriginalFilename();
		endFilePath= BASE_FILE_PATH+ fileName;
		byte[] bytes = file.getBytes();
		BufferedOutputStream buffStream = 
		        new BufferedOutputStream(new FileOutputStream(new File(endFilePath)));
		buffStream.write(bytes);
		buffStream.close();
		
		XSSFWorkbook workBook= new XSSFWorkbook(new FileInputStream(new File(endFilePath)));
		XSSFSheet sheet = workBook.getSheetAt(0);
		return sheet;
	}
    @RequestMapping(value="/multipleUpload")
    public String multiUpload(){
    	return "multipleUpload";
    }
    @RequestMapping(value="/multipleSave", method=RequestMethod.POST )
    public @ResponseBody String multipleSave(@RequestParam("file") MultipartFile[] files){
    	String fileName = null;
    	String msg = "";
    	if (files != null && files.length >0) {
    		for(int i =0 ;i< files.length; i++){
	            try {
	                fileName = files[i].getOriginalFilename();
	                byte[] bytes = files[i].getBytes();
	                BufferedOutputStream buffStream = 
	                        new BufferedOutputStream(new FileOutputStream(new File(BASE_FILE_PATH+"/uploaded" + fileName)));
	                buffStream.write(bytes);
	                buffStream.close();
	                msg += "You have successfully uploaded " + fileName +"<br/>";
	            } catch (Exception e) {
	                return "You failed to upload " + fileName + ": " + e.getMessage() +"<br/>";
	            }
    		}
    		return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }
    
    @RequestMapping(value="/uploadVoti")
    public String uploadVoti(){
    	return "uploadPerformances";
    }
    
    @RequestMapping(value="/createSeason")
    public String createSeason(){
    	return "createSeason";
    }
    
    @RequestMapping(value="/schedule", method=RequestMethod.POST )
    public @ResponseBody String schedule(@RequestParam("file") MultipartFile file, @RequestParam("startingYear") int startingYear){
    	String fileName = file.getOriginalFilename();
    	String endFilePath=null;
    	LeagueSeason season =seasonRepository.findByStartingYear((long) startingYear);
    	if (season==null){
    		season= new LeagueSeason();
    		season.setStartingYear(startingYear);
    		seasonRepository.save(season);
    	}
    	
    		
    	if (!file.isEmpty()) {
    		try {
    			XSSFSheet sheet = getSheet(file);
    			Iterator<Row> rowIterator = sheet.rowIterator();
    			int dayIndex=0;
                while(rowIterator.hasNext()){
                	Row row = rowIterator.next();
                	LeagueDay day;
                	if(row.getCell(0).getCellType()==CellType.STRING&&row.getCell(0).getStringCellValue().toLowerCase().startsWith("giornata")){
                		//empty cell is the marker for a new league day
                		dayIndex=Integer.valueOf(row.getCell(0).getStringCellValue().split(" ")[1]);
                		day=dayRepository.findByDayIndex(dayIndex, season);
                		if(day==null){
                			day = new LeagueDay();
                    		day.setSeason(season);
                    		day.setDayIndex(dayIndex);
                    		day.setCompleted(false);
                    		dayRepository.save(day);
                		}
                		
                	}else{
                		//you are on an actual match
                		
                		//fetch the day
                		day=dayRepository.findByDayIndex(dayIndex, season);
                		LeagueMatch match = new LeagueMatch();
                		Calendar matchDate=Calendar.getInstance();
                		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                		System.out.println("day :"+day.getDayIndex()+" ,day date :"+row.getCell(0).getStringCellValue());
                		matchDate.setTime(dateFormat.parse(row.getCell(0).getStringCellValue()));
                		match.setDate(matchDate);
                		match.setSeason(season);
                		String[] teams = row.getCell(1).getStringCellValue().split("-");
                		Team homeTeam = teamRepository.findTeamByName(teams[0].trim());
                		if(homeTeam==null) {
                			homeTeam= new Team();
                			homeTeam.setName(teams[0].trim());
                			teamRepository.save(homeTeam);
                		}
                		match.setHomeTeam(homeTeam);
                		Team awayTeam = teamRepository.findTeamByName(teams[1].trim());
                		if(awayTeam==null) {
                			awayTeam= new Team();
                			awayTeam.setName(teams[1].trim());
                			teamRepository.save(awayTeam);
                		}
                		match.setAwayTeam(awayTeam);
                		String[] matchGoals=row.getCell(2).getStringCellValue().split("-");
            			match.setHomeGoals(Integer.valueOf(matchGoals.length==0?"0":matchGoals[0]));
            			match.setAwayGoals(Integer.valueOf(matchGoals.length==0?"0":matchGoals[1]));
            			match.setDay(day);
                		if(Calendar.getInstance().after(matchDate)){
                			match.setCompleted(true);
                		}else{
                			match.setCompleted(false);
                		}
                		matchRepository.save(match);
                	}
                		
                	
                }
                Calendar[] dates =matchRepository.getStartinAndEndingMatchesDatesBySeason(season);
                season.setStartingDate(dates[0]);
                season.setEndingDate(dates[1]);
                seasonRepository.save(season);
                return "Schedule created";
    		} catch (Exception e) {
    			return "You failed to upload " + fileName + ": " + e.getMessage() +"<br/>";
    		}
    	}else{
    		return "Unable to upload. File is empty.";
    	}
    	
    	
    }
    
    @RequestMapping(value="/dayPerformancesUpload", method=RequestMethod.POST )
    public @ResponseBody String uploadPerformances(@RequestParam("file") MultipartFile file, @RequestParam("day") int dayNumber,@RequestParam("seasonStartingYear") int seasonStartingYear,@RequestParam("startingRow") int startingRow,@RequestParam("fieldsString") String fieldsString ) throws FileNotFoundException, IOException{
    	
    	String fileName = file.getOriginalFilename();
    	//Boolean fieldsNamesFound=false;
    	
    	if (!file.isEmpty()&&fileName.endsWith(".xlsx")) {
            try {
            	
            	final List<String> fields=Arrays.asList(fieldsString.split(";"));
            	Map<String,String> namesMap=new HashMap<String, String>(){{
            		put(PLAYER_ID,fields.get(0));
            		put(NAME,fields.get(2));
            		put(ROLE,fields.get(1));
            		put(EVALUATION,fields.get(3));
            		put(GOALS_SCORED,fields.get(4));
            		put(GOALS_ALLOWED,fields.get(5));
            		put(PENALTIES_SAVED,fields.get(6));
            		put(PENALTIES_MISSED,fields.get(7));
            		put(PENALTIES_SCORED,fields.get(8));
            		put(AUTOGOALS,fields.get(9));
            		put(YELLOW_CARDS,fields.get(10));
            		put(RED_CARDS,fields.get(11));
            		put(ASSISTS,fields.get(12));
            		put(ASSISTS_FROM_KICK,fields.get(13));
            		put(GOAL_OF_VICTORY,fields.get(14));
            		put(GOAL_OF_TIE,fields.get(15));
            	}};
            	Optional<LeagueSeason> season = seasonRepository.findById(Long.valueOf(seasonStartingYear));
            	LeagueDay day =dayRepository.findByDayIndex(dayNumber, season.get());
                
                XSSFSheet sheet = getSheet(file);
                
                Map<String,Integer> columnMap= new HashMap<String, Integer>();
                Iterator<Row> rowIterator = sheet.rowIterator();
                int rowIndex = 0;
                while(rowIndex<startingRow){
                	rowIndex++;
                	rowIterator.next();
                }
                String teamName="";
                List<Team> teams=teamRepository.findAll();
        		List<String> teamNames =new ArrayList<String>();
        		for(Team team : teams){
        			teamNames.add(team.getName().toLowerCase());
        		}
                while (rowIterator.hasNext()){
                	Row actualRow = rowIterator.next();
                	Iterator<Cell> cellIterator = actualRow.cellIterator();
                	Cell firstCell = actualRow.getCell(0);
                	if(firstCell.getCellType()==CellType.NUMERIC){
                		
                		//data row we must fetch a performance and persist it
                		
                		Performance performance = new Performance();
                		Optional<Player> playerRez =playerRepository.findById((long)actualRow.getCell(columnMap.get(PLAYER_ID)).getNumericCellValue());
                		Player player= new Player();
                		if(!playerRez.isPresent()){
                		
                			player=createPlayer(columnMap, actualRow,teamName);
                		}
                		else {
                			player=playerRez.get();
                		}
                		PerformanceID performanceID= new PerformanceID();
                		performanceID.setPlayer(player);
                		performance.setAssists((int)actualRow.getCell(columnMap.get(ASSISTS)).getNumericCellValue());
                		performance.setAssistsFromKick((int)actualRow.getCell(columnMap.get(ASSISTS_FROM_KICK)).getNumericCellValue());
                		performance.setAutoGoals((int)actualRow.getCell(columnMap.get(AUTOGOALS)).getNumericCellValue());
                		performanceID.setDay(day);
                		if(actualRow.getCell(columnMap.get(EVALUATION)).getCellType()==CellType.NUMERIC){
                			double oneDecimalPlacesEvaluation = Math.floor(actualRow.getCell(columnMap.get(EVALUATION)).getNumericCellValue()*10)/10;
                			performance.setEvaluation(oneDecimalPlacesEvaluation);
                		}
                		 
                		
                		
                		performance.setGoalOfTie((int)actualRow.getCell(columnMap.get(GOAL_OF_TIE)).getNumericCellValue()>0);
                		performance.setGoalOfVictory((int)actualRow.getCell(columnMap.get(GOAL_OF_VICTORY)).getNumericCellValue()>0);
                		performance.setGoalsAllowed((int)actualRow.getCell(columnMap.get(GOALS_ALLOWED)).getNumericCellValue());
                		performance.setGoalsScored((int)actualRow.getCell(columnMap.get(GOALS_SCORED)).getNumericCellValue());
                		
//                		String teamName =actualRow.getCell(columnMap.get(namesMap.get(""))).getStringCellValue();
                		LeagueMatch match = matchRepository.findByDayAndSeasonAndHomeTeam(day, season.get(), teamRepository.findTeamByName(teamName));//findMatch(day, season.get(), teamRepository.findTeamByName(teamName));
                		if(match==null) {
                			match=matchRepository.findByDayAndSeasonAndAwayTeam(day, season.get(), teamRepository.findTeamByName(teamName));
                		}
                		List<Player> matchPlayers = match.getPlayers();
                		if(matchPlayers==null) {
                			matchPlayers=new ArrayList<>();
                		}
                		if(!matchPlayers.contains(player)) {
                			matchPlayers.add(player);
                			match.setPlayers(matchPlayers);
                			matchRepository.save(match);
                		}
                		performance.setHomeMatch(match.getHomeTeam().getName().equalsIgnoreCase(teamName));
                		performanceID.setMatch(match);
                		performance.setPenaltiesMissed((int)actualRow.getCell(columnMap.get(PENALTIES_MISSED)).getNumericCellValue());
                		performance.setPenaltiesSaved((int)actualRow.getCell(columnMap.get(PENALTIES_SAVED)).getNumericCellValue());
                		performance.setPenaltiesScored((int)actualRow.getCell(columnMap.get(PENALTIES_SCORED)).getNumericCellValue());
                		performance.setRedCards((int)actualRow.getCell(columnMap.get(RED_CARDS)).getNumericCellValue());
                		performanceID.setSeason(season.get());
                		performance.setYellowCards((int)actualRow.getCell(columnMap.get(YELLOW_CARDS)).getNumericCellValue());
                		performance.setPerformanceID(performanceID);
                		performanceRepository.save(performance);
                	}else{
                		
                		String firstString=firstCell.getStringCellValue().replace(VOTI_GENTILMENTE_OFFERTI_DA_WWW_FANTAGAZZETTA_COM, "");
                		if(!teamNames.contains(firstString.toLowerCase())){
                			//this is an headers row if needed we get the fieldsNames
                			while(cellIterator.hasNext()){
                				Cell cell=cellIterator.next();
                				String actualValue=cell.getStringCellValue();
                        		if(actualValue.equalsIgnoreCase(namesMap.get(PLAYER_ID)))
                        			columnMap.put(PLAYER_ID,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(ROLE)))
                        			columnMap.put(ROLE,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(NAME)))
                        			columnMap.put(NAME,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(EVALUATION)))
                        			columnMap.put(EVALUATION,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(GOALS_SCORED)))
                        			columnMap.put(GOALS_SCORED,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(GOALS_ALLOWED)))
                        			columnMap.put(GOALS_ALLOWED,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(PENALTIES_SAVED)))
                        			columnMap.put(PENALTIES_SAVED,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(PENALTIES_MISSED)))
                        			columnMap.put(PENALTIES_MISSED,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(PENALTIES_SCORED)))
                        			columnMap.put(PENALTIES_SCORED,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(AUTOGOALS)))
                        			columnMap.put(AUTOGOALS,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(YELLOW_CARDS)))
                        			columnMap.put(YELLOW_CARDS,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(RED_CARDS)))
                        			columnMap.put(RED_CARDS,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(ASSISTS)))
                        			columnMap.put(ASSISTS,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(ASSISTS_FROM_KICK)))
                        			columnMap.put(ASSISTS_FROM_KICK,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(GOAL_OF_VICTORY)))
                        			columnMap.put(GOAL_OF_VICTORY,cell.getColumnIndex() );
                        		else if(actualValue.equalsIgnoreCase(namesMap.get(GOAL_OF_TIE)))
                        			columnMap.put(GOAL_OF_TIE,cell.getColumnIndex() );
                        		
                        		
                			}
                			
                		}else{
                			teamName=firstString;
                		}
                		
                	}
                	rowIndex++;
                }
                
                
                return "You have successfully uploaded " + fileName;
            } catch (Exception e) {
                System.out.println("EXCEPTION : " + e.getCause().getMessage());
                throw e;
            }
        } else {
            return "Unable to upload. File is empty.";
        }
    }
}
