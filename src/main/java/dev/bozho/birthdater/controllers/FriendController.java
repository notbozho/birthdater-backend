package dev.bozho.birthdater.controllers;

import dev.bozho.birthdater.domain.AppUser;
import dev.bozho.birthdater.domain.Friend;
import dev.bozho.birthdater.domain.enums.FriendType;
import dev.bozho.birthdater.repository.FriendRepository;
import dev.bozho.birthdater.service.impl.FriendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class FriendController {

    @Autowired
    public FriendServiceImpl friendService;

    @PostMapping("/friend/new")
    public ResponseEntity<Friend> createFriend(@RequestBody Friend friend) {
        Friend f = friendService.createFriend(friend);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }

}
