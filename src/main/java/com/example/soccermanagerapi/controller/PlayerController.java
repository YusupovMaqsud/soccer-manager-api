package com.example.soccermanagerapi.controller;

import com.example.soccermanagerapi.entity.Player;
import com.example.soccermanagerapi.payload.ApiResponse;
import com.example.soccermanagerapi.payload.PlayerDto;
import com.example.soccermanagerapi.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PreAuthorize(value = "hasAuthority('ADD_PLAYER')")
    @PostMapping
    public HttpEntity<?> addPlayer(@RequestBody PlayerDto playerDto) {
        ApiResponse apiResponse = playerService.addPlayer(playerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_PLAYER')")
    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        Player player = playerService.getPlayer(id);
        return player;
    }

    @PreAuthorize(value = "hasAuthority('VIEW_PLAYER')")
    @GetMapping
    public List<Player> getPlayers() {
        List<Player> players = playerService.getPlayers();
        return players;
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PLAYER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editPlayer(@RequestBody PlayerDto playerDto, Long id) {
        ApiResponse apiResponse = playerService.editPlayer(playerDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_PLAYER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePlayer(@PathVariable Long id) {
        ApiResponse apiResponse = playerService.deletePlayer(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
