package dev.bozho.birthdater.service;

import dev.bozho.birthdater.domain.Friend;

import java.util.List;

public interface FriendService {

    List<Friend> getFriendsOfUserByUserId(long userId);
}
