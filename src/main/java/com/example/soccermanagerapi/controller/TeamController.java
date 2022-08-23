package com.example.soccermanagerapi.controller;

import com.example.soccermanagerapi.entity.Team;
import com.example.soccermanagerapi.payload.ApiResponse;
import com.example.soccermanagerapi.payload.TeamDto;
import com.example.soccermanagerapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PreAuthorize(value = "hasAuthority('ADD_TEAM')")
    @PostMapping
    public HttpEntity<?> addTeam(@RequestBody TeamDto teamDto){
        ApiResponse apiResponse = teamService.addTeam(teamDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAuthority('ROLE_MY_TEAM')")
    @GetMapping("/{id}")
    public Team getTeam(@PathVariable Long id){
        Team team = teamService.getTeam(id);
        return team;
    }


    @PreAuthorize(value = "hasAuthority('VIEW_TEAM')")
    @GetMapping
    public List<Team> getTeams(){
        List<Team> teams = teamService.getTeams();
        return teams;
    }



    @PreAuthorize(value = "hasAuthority('EDIT_TEAM')")
    @PutMapping("/{id}")
    public HttpEntity<?> editTeam(@RequestBody TeamDto teamDto,Long id){
        ApiResponse apiResponse = teamService.editTeam(teamDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_TEAM')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTeam(@PathVariable Long id){
        ApiResponse apiResponse = teamService.deleteTeam(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
