package com.example.soccermanagerapi.service;

import com.example.soccermanagerapi.entity.Player;
import com.example.soccermanagerapi.entity.Team;
import com.example.soccermanagerapi.entity.Transfer;
import com.example.soccermanagerapi.entity.enums.PlayerPositionEnum;
import com.example.soccermanagerapi.payload.ApiResponse;
import com.example.soccermanagerapi.payload.PlayerDto;
import com.example.soccermanagerapi.repository.PlayerRepository;
import com.example.soccermanagerapi.repository.TeamRepository;
import com.example.soccermanagerapi.repository.TransferRepository;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;


    public ApiResponse addPlayer(PlayerDto playerDto) {
        boolean exists = playerRepository.existsByFirstNameAndLastName(playerDto.getFirstName(), playerDto.getLastName());
        if (exists) {
            return new ApiResponse("Bunday Player mavjud", false);
        }

        List<Player> playerList = new ArrayList<>();
        PlayerPositionEnum[] values = PlayerPositionEnum.values();
        for (PlayerPositionEnum value : values) {
            if (value.equals("GOALKEEPER")) {
                playerList.add(new Player(
                        playerDto.getFirstName(),
                        playerDto.getLastName(),
                        playerDto.getCountry()
                ));
            }

            if (value.equals("DEFENDER")) {
                playerList.add(new Player(
                        playerDto.getFirstName(),
                        playerDto.getLastName(),
                        playerDto.getCountry()
                ));
            }


            if (value.equals("MIDFIELDER")) {
                playerList.add(new Player(
                        playerDto.getFirstName(),
                        playerDto.getLastName(),
                        playerDto.getCountry()
                ));
            }

            if (value.equals("FORWARD")) {
                playerList.add(new Player(
                        playerDto.getFirstName(),
                        playerDto.getLastName(),
                        playerDto.getCountry()
                ));
            }
        }

        playerRepository.saveAll(playerList);
        return new ApiResponse("Player added", true);
    }





    public Player getPlayer(Long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        return optionalPlayer.get();
    }




    public List<Player> getPlayers() {
        List<Player> players = playerRepository.findAll();
        return players;
    }





    public ApiResponse editPlayer(PlayerDto playerDto, Long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (!optionalPlayer.isPresent()) {
            return new ApiResponse("Bunday players mavjud emas", false);
        }


        Player player = optionalPlayer.get();

        List<Player> playerList = new ArrayList<>();
        PlayerPositionEnum[] values = PlayerPositionEnum.values();
        for (PlayerPositionEnum value : values) {
            if (value.equals("GOALKEEPER")) {
                player.setFirstName(playerDto.getFirstName());
                player.setLastName(playerDto.getLastName());
                player.setCountry(playerDto.getCountry());
                playerList.add(player);

            }

            if (value.equals("DEFENDER")) {
                player.setFirstName(playerDto.getFirstName());
                player.setLastName(playerDto.getLastName());
                player.setCountry(playerDto.getCountry());
                playerList.add(player);
            }


            if (value.equals("MIDFIELDER")) {
                player.setFirstName(playerDto.getFirstName());
                player.setLastName(playerDto.getLastName());
                player.setCountry(playerDto.getCountry());
                playerList.add(player);
            }

            if (value.equals("FORWARD")) {
                player.setFirstName(playerDto.getFirstName());
                player.setLastName(playerDto.getLastName());
                player.setCountry(playerDto.getCountry());
                playerList.add(player);
            }
        }

        playerRepository.saveAll(playerList);
        return new ApiResponse("Player edited", true);
    }




    public ApiResponse deletePlayer(Long id) {
        try {
            playerRepository.deleteById(id);
            return new ApiResponse("Player deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Player not deleted", false);
        }
    }
}
