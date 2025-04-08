package com.example.demo.mapper;

import com.example.demo.controllers.dto.*;
import com.example.demo.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper (componentModel = "spring")
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
    CreatePlayerDto toCreatePlayerDto(CreatePlayerRequest createPlayerRequest);
    GetCreatedPlayerResponse toGetCreatedPlayer(GetCreatedPlayerDto getCreatedPlayerDto);
    UpdatePlayerDto toUpdatePlayerDto(UpdatePlayerRequest updatePlayerRequest);
    UpdatePlayerResponse toUpdatePlayerResponse(GetUpdatedPlayerDto getUpdatedPlayerDto);
    GetPlayersCountDto toGetPlayersCountDto(GetPlayersCountRequest getPlayersCountRequest);
    FilterPlayerDto toFilterPlayerDto(FilterPlayerRequest filterPlayerRequest);
    List<FilterPlayerResponse> toFilterPlayerResponse(List<GetFilteredListDto> getFilteredListDto);
}
