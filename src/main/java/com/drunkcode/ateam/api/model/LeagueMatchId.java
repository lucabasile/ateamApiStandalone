package com.drunkcode.ateam.api.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class LeagueMatchId implements Serializable {

    @ManyToOne(targetEntity=Team.class)
    @JoinColumn(name="homeTeam")
    Team	homeTeam;

    @ManyToOne(targetEntity=Team.class)
    @JoinColumn(name="awayTeam")
    Team 	awayTeam;

    @ManyToOne(targetEntity=LeagueDay.class)
    @JoinColumn(name="day")
    LeagueDay 	day;

    @ManyToOne(targetEntity=LeagueSeason.class)
    @JoinColumn(name="year")
    LeagueSeason	year;
}
