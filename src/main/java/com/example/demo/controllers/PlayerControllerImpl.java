package com.example.demo.controllers;

import com.example.demo.controllers.dto.*;
import com.example.demo.mapper.PlayerMapper;
import com.example.demo.service.PlayerService;
import com.example.demo.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerControllerImpl implements PlayerController{
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    public GetCreatedPlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
        //TODO создать маппер и вызывать его dto = Mapper.map(createPlayerRequest);
        CreatePlayerDto createPlayerDto = playerMapper.toCreatePlayerDto(createPlayerRequest);
        return playerMapper.toGetCreatedPlayer(playerService.createPlayer(createPlayerDto));
    }

    public UpdatePlayerResponse updatePlayer(long id, UpdatePlayerRequest updatePlayerRequest) {
        UpdatePlayerDto updatePlayerDto = playerMapper.toUpdatePlayerDto(updatePlayerRequest);
        return playerMapper.toUpdatePlayerResponse(playerService.updatePlayer(id, updatePlayerDto));
    }

    public void deletePlayer(long id) {
        playerService.deletePlayer(id);
    }

    public GetCreatedPlayerResponse getPlayer(long id) {
        return playerMapper.toGetCreatedPlayer(playerService.getPlayer(id));
    }

    public int getPlayersCount(GetPlayersCountRequest getPlayersCountRequest){
        GetPlayersCountDto getPlayersCountDto = playerMapper.toGetPlayersCountDto(getPlayersCountRequest);
        return playerService.getPlayersCount(getPlayersCountDto);
    }

    public List<FilterPlayerResponse> getListByFilter(FilterPlayerRequest filterPlayerRequest) {
        FilterPlayerDto filterPlayerDto = playerMapper.toFilterPlayerDto(filterPlayerRequest);
        List<GetFilteredListDto> filteredList = playerService.getListByFilter(filterPlayerDto);
        return playerMapper.toFilterPlayerResponse(filteredList);
    }
}

