package dev.bozho.birthdater.service.impl;

import dev.bozho.birthdater.domain.Friend;
import dev.bozho.birthdater.repository.FriendRepository;
import dev.bozho.birthdater.service.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;


    @Override
    public List<Friend> getFriendsOfUserByUserId(long userId) {
        return null;
    }
}
