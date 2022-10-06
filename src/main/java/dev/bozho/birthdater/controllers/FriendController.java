package dev.bozho.birthdater.controllers;

import dev.bozho.birthdater.domain.AppUser;
import dev.bozho.birthdater.domain.Friend;
import dev.bozho.birthdater.domain.enums.FriendType;
import dev.bozho.birthdater.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class FriendController {

    @Autowired
    public FriendRepository friendRepository;

    @PostMapping("/friend/new")
    public ResponseEntity<Friend> createFriend(@RequestBody Friend friend) {
        try {
            Friend _friend = friendRepository
                    .save(new Friend(friend.getFirstName(), friend.getLastName(), friend.getFriendType(), friend.getBirthdate(), friend.getUserId()));
            return new ResponseEntity<>(_friend, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
