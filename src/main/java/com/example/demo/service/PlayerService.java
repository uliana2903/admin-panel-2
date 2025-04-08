package com.example.demo.service;

import com.example.demo.controllers.dto.*;
import com.example.demo.service.dto.*;

import java.util.List;

public interface PlayerService {

    GetCreatedPlayerDto createPlayer(CreatePlayerDto createPlayerDto);

    GetUpdatedPlayerDto updatePlayer(long id, UpdatePlayerDto updatePlayerDto);

    void deletePlayer(long id);

    int getPlayersCount(GetPlayersCountDto getPlayersCountDto);

    GetCreatedPlayerDto getPlayer(long id);

    List<GetFilteredListDto> getListByFilter (FilterPlayerDto filterPlayerDto);
}
