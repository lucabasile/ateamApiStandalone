package com.drunkcode.ateam.api.dto.validators;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.ConstraintValidatorContext;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.drunkcode.ateam.api.dto.Squad;
import com.drunkcode.ateam.api.dto.SquadMember;
import com.drunkcode.ateam.api.model.FantasyTeam;
import com.drunkcode.ateam.api.repository.FantasyTeamRepository;

//@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SquadValidatorTest {
	
	@InjectMocks
	SquadValidator squadValidator;
	
	@Mock
	FantasyTeamRepository fantasyTeamRepo;
	
	
	
	ConstraintValidatorContext constraintValidatorContext;
	
	@Before
	public void setUp(){
		//MockitoAnnotations.initMocks(this);
		constraintValidatorContext = mock(ConstraintValidatorContext.class);
	}

	@DisplayName("Test for Squad Validator")
	@ParameterizedTest(name = "{index} => membersString={0}, alternativesString={1},teamIdsString={2}")
	@CsvFileSource (resources="/squadValidatorTest.csv")
	void test(String membersString,String alternativesString,String teamIdsString) {
		
		long squadId = (new Random()).nextLong();
		Squad squad = new Squad();
		
		squad.setMembers(extractMembers(membersString));
		squad.setAlternatives(extractMembers(alternativesString));
		squad.setFantasyTeamId(squadId);
		
		FantasyTeam answer=new FantasyTeam();
		when(fantasyTeamRepo.findById(squadId)).thenReturn(Optional.of(answer));
		squadValidator.isValid(squad, constraintValidatorContext);
		fail("Not yet implemented");
	}

	private List<SquadMember> extractMembers(String membersString) {
		List<SquadMember> members= new ArrayList<SquadMember>();
		for (Object memberString : Arrays.asList(membersString.split(";"))) {
			String[] memberValues = memberString.toString().split("%",-1);
			
			String memberRole=memberValues[0];		
			String memberName=memberValues[1];
			Long memberId=Long.valueOf(memberValues[2]);
			SquadMember member = new SquadMember();
			member.setName(memberName);
			member.setRole(memberRole);
			member.setId(memberId);
			
			if(!memberValues[3].isEmpty())
			{
				Integer captainOrder= Integer.valueOf(memberValues[3]);
				member.setCaptainOrder(captainOrder);
			}
			if(!memberValues[4].isEmpty())
			{
				Integer penaltyOrder= Integer.valueOf(memberValues[4]);
				member.setPenaltyOrder(penaltyOrder);
			}
						
			
			
			
			members.add(member);
		}
		return members;
	}

}
