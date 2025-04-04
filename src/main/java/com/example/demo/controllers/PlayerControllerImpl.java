package com.example.demo.controllers;

import com.example.demo.controllers.dto.*;
import com.example.demo.service.PlayerService;
import com.example.demo.service.dto.CreatePlayerService;
import com.example.demo.service.dto.FilterPlayerService;
import com.example.demo.service.dto.GetPlayersCountService;
import com.example.demo.service.dto.UpdatePlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerControllerImpl implements PlayerController{
    private final PlayerService playerService;

    public PlayerControllerImpl(PlayerService playerService) {
        this.playerService = playerService;
    }

    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
        CreatePlayerService createPlayerService = new CreatePlayerService(createPlayerRequest.getName(), createPlayerRequest.getTitle(),
                createPlayerRequest.getRace(), createPlayerRequest.getProfession(), createPlayerRequest.getBirthday());
        return playerService.createPlayer(createPlayerService);
    }

    public UpdatePlayerResponse updatePlayer(long id, UpdatePlayerRequest updatePlayerRequest) {
        UpdatePlayerService updatePlayerService = new UpdatePlayerService(updatePlayerRequest.getName(), updatePlayerRequest.getTitle(),
                updatePlayerRequest.getRace(), updatePlayerRequest.getProfession(), updatePlayerRequest.getBirthday(),
                updatePlayerRequest.getBanned(), updatePlayerRequest.getExperience());
        return playerService.updatePlayer(id, updatePlayerService);
    }

    public void deletePlayer(long id) {
        playerService.deletePlayer(id);
    }

    public GetPlayerResponse getPlayer(long id) {
        return playerService.getPlayer(id);
    }

    public int getPlayersCount(GetPlayersCountRequest getPlayersCountRequest){
        GetPlayersCountService getPlayersCountService = new GetPlayersCountService(getPlayersCountRequest.getName(), getPlayersCountRequest.getTitle(),
                getPlayersCountRequest.getRace(), getPlayersCountRequest.getProfession(), getPlayersCountRequest.getAfter(), getPlayersCountRequest.getBefore(),
                getPlayersCountRequest.getBanned(), getPlayersCountRequest.getMinExperience(), getPlayersCountRequest.getMaxExperience(),
                getPlayersCountRequest.getMinLevel(), getPlayersCountRequest.getMaxLevel());
        return playerService.getPlayersCount(getPlayersCountService);
    }

    public List<FilterPlayerResponse> getListByFilter(FilterPlayerRequest filterPlayerRequest) {
        FilterPlayerService filterPlayerService = new FilterPlayerService(filterPlayerRequest.getName(), filterPlayerRequest.getTitle(),
                filterPlayerRequest.getRace(), filterPlayerRequest.getProfession(), filterPlayerRequest.getAfter(), filterPlayerRequest.getBefore(),
                filterPlayerRequest.getBanned(), filterPlayerRequest.getMinExperience(), filterPlayerRequest.getMaxExperience(),
                filterPlayerRequest.getMinLevel(), filterPlayerRequest.getMaxLevel(), filterPlayerRequest.getPlayerOrder(),
                filterPlayerRequest.getPageNumber(), filterPlayerRequest.getPageSize());
        return playerService.getListByFilter(filterPlayerService);
    }
}

