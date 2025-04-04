package com.example.demo.service;

import com.example.demo.controllers.dto.*;
import com.example.demo.service.dto.CreatePlayerService;
import com.example.demo.service.dto.FilterPlayerService;
import com.example.demo.service.dto.GetPlayersCountService;
import com.example.demo.service.dto.UpdatePlayerService;

import java.util.List;

public interface PlayerService {

    CreatePlayerResponse createPlayer(CreatePlayerService createPlayerService);

    UpdatePlayerResponse updatePlayer(long id, UpdatePlayerService updatePlayerService);

    void deletePlayer(long id);

    int getPlayersCount(GetPlayersCountService getPlayersCountService);

    GetPlayerResponse getPlayer(long id);

    List<FilterPlayerResponse> getListByFilter (FilterPlayerService filterPlayerService);
}
