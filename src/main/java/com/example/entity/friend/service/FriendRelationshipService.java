package com.example.entity.friend.service;

import com.example.entity.friend.dto.FriendDto;

import java.util.List;

public interface FriendRelationshipService {
    List<FriendDto.Response> searchUsers(String nickname);

    List<FriendDto.Response> getFriendList(long userId);

    void requestFriend(FriendDto.Request req);

    List<FriendDto.Response> getRequestList(long userId);

    void acceptFriend(FriendDto.Request req);

    void removeFriend(FriendDto.Request req);
}
