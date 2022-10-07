package dev.bozho.birthdater.service;

import dev.bozho.birthdater.model.Friend;

import java.util.List;

public interface IFriendService {

    List<Friend> getFriendsOfUserId(long userId) throws Exception;

    Friend createFriend(Friend friend);
}
