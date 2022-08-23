package com.example.soccermanagerapi.service;

import com.example.soccermanagerapi.entity.Player;
import com.example.soccermanagerapi.entity.Team;
import com.example.soccermanagerapi.entity.User;
import com.example.soccermanagerapi.payload.ApiResponse;
import com.example.soccermanagerapi.payload.TeamDto;
import com.example.soccermanagerapi.repository.PlayerRepository;
import com.example.soccermanagerapi.repository.TeamRepository;
import com.example.soccermanagerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerRepository playerRepository;


    public ApiResponse addTeam(TeamDto teamDto) {
        boolean exists = teamRepository.existsByNameAndCountry(teamDto.getName(), teamDto.getCountry());
        if (exists) {
            return new ApiResponse("Bunday team mavjud", false);
        }

        Optional<Player> optionalPlayer = playerRepository.findById(teamDto.getPlayerId());
        if (!optionalPlayer.isPresent()) {
            return new ApiResponse("Bunday Player mavjud emas", false);
        }

        Team team = new Team(
                teamDto.getName(),
                teamDto.getCountry(),
                Collections.singletonList(optionalPlayer.get())
        );
        teamRepository.save(team);
        return new ApiResponse("Team saqlandi", true);
    }




    public Team getTeam(Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        return optionalTeam.get();
    }






    public List<Team> getTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams;
    }





    public ApiResponse editTeam(TeamDto teamDto, Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (!optionalTeam.isPresent()) {
            return new ApiResponse("Bunday Team mavjud emas", false);
        }

        Optional<Player> optionalPlayer = playerRepository.findById(teamDto.getPlayerId());
        if (!optionalPlayer.isPresent()) {
            return new ApiResponse("Bunday Player mavjud emas", false);
        }

        Team team = optionalTeam.get();
        team.setName(teamDto.getName());
        team.setCountry(teamDto.getCountry());
        team.setPlayers(Collections.singletonList(optionalPlayer.get()));
        teamRepository.save(team);

        return new ApiResponse("Edit qilindi", true);
    }





    public ApiResponse deleteTeam(Long id) {
        try {
            teamRepository.deleteById(id);
            return new ApiResponse("Team Deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Team not Deleted", false);
        }
    }


}
