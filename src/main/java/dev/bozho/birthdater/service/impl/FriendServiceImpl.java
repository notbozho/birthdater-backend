package dev.bozho.birthdater.service.impl;

import dev.bozho.birthdater.model.Friend;
import dev.bozho.birthdater.repository.FriendRepository;
import dev.bozho.birthdater.service.IFriendService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendServiceImpl implements IFriendService {

    private final UserServiceImpl userService;
    private final FriendRepository friendRepository;

    @Override
    public List<Friend> getFriendsOfUserByUserId(long userId) throws Exception {
        boolean validUser = userService.doesUserExistById(userId);
        if(!validUser) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with id: " + userId + " doesn't exist");

        List<Friend> friends = friendRepository.findAllByUserId(userId).get();
        if(friends.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with id: " + userId + " doesn't have any friends");
        }

        return friends;
    }
    @Override
    public Friend createFriend(Friend friend) {
        Friend _friend = friendRepository
                .save(new Friend(friend.getFirstName(), friend.getLastName(), friend.getFriendType(), friend.getBirthdate(), friend.getUserId()));

        return _friend;
    }
}
