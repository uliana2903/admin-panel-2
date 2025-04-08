package com.example.demo.controllers;

import com.example.demo.controllers.dto.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RequestMapping ("/rest/players")
public interface PlayerController {

    @PostMapping()
    GetCreatedPlayerResponse createPlayer(@Valid @RequestBody CreatePlayerRequest createPlayerRequest);

    @PostMapping("/{id}")
    UpdatePlayerResponse updatePlayer(@PathVariable @Valid @Positive long id, @RequestBody UpdatePlayerRequest updatePlayerRequest);

    @DeleteMapping("/{id}")
    void deletePlayer(@PathVariable @Valid @Positive long id);

    @GetMapping("/{id}")
    GetCreatedPlayerResponse getPlayer(@Valid @Positive @PathVariable long id);

    @GetMapping()
    List<FilterPlayerResponse> getListByFilter(FilterPlayerRequest filterPlayerRequest);

    @GetMapping("/count")
    int getPlayersCount(GetPlayersCountRequest getPlayersCountRequest);
}
