package com.example.entity.friend.serviceImpl;

import com.example.entity.friend.domain.FriendRelationship;
import com.example.entity.friend.dto.FriendDto;
import com.example.entity.friend.repository.FriendRelationshipRepository;
import com.example.entity.friend.service.FriendRelationshipService;
import com.example.entity.global.service.EntityAndDtoConversionService;
import com.example.entity.user.domain.User;
import com.example.entity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendRelationshipServiceImpl implements FriendRelationshipService {

    private final FriendRelationshipRepository friendRelationshipRepository;
    private final EntityAndDtoConversionService entityAndDtoConversionService;
    private final UserRepository userRepository;
    @Override
    public List<FriendDto.Response> searchUsers(String nickname) {
        List<User> userList = userRepository.findAllByNickname(nickname);
        List<FriendDto.Response> users = new ArrayList<>();
        for(User user : userList) {
            users.add(entityAndDtoConversionService.userEntityToFriendDtoResponse(user));
        }

        return users;
    }

    @Override
    public List<FriendDto.Response> getFriendList(long userId) {
        List<User> friendEntityList = friendRelationshipRepository.findAllFriendsById(userId);
        List<FriendDto.Response> friendList = new ArrayList<>();
        for(User user : friendEntityList) {
            friendList.add(entityAndDtoConversionService.userEntityToFriendDtoResponse(user));
        }

        return friendList;
    }

    @Override
    public void requestFriend(FriendDto.Request req) {
        User fromUser = userRepository.findByNickname(req.getFromUserNickname());
        User toUser = userRepository.findByNickname(req.getToUserNickname());
        FriendRelationship friendRequest = FriendRelationship.builder().fromUser(fromUser).toUser(toUser).build();
        if(!friendRequest.isFriend())
            friendRequest.updateIsFriend();
        FriendRelationship friendAccept = FriendRelationship.builder().fromUser(toUser).toUser(fromUser).build();

        friendRelationshipRepository.save(friendRequest);
        friendRelationshipRepository.save(friendAccept);
    }

    @Override
    public List<FriendDto.Response> getRequestList(long userId) {

        List<User> requestList = friendRelationshipRepository.findAllRequestById(userId);
        List<FriendDto.Response> resList = new ArrayList<>();
        for(User user : requestList) {
            resList.add(entityAndDtoConversionService.userEntityToFriendDtoResponse(user));
        }

        return resList;
    }

    @Override
    public void acceptFriend(FriendDto.Request req) {
        FriendRelationship friendRelationship = friendRelationshipRepository.findByFromUserAndToUser(req.getFromUserNickname(), req.getToUserNickname());
        if(!friendRelationship.isFriend())
            friendRelationship.updateIsFriend();
    }

    @Override
    public void removeFriend(FriendDto.Request req) {
        friendRelationshipRepository.deleteByNicknames(req.getFromUserNickname(), req.getToUserNickname());
        friendRelationshipRepository.deleteByNicknames(req.getToUserNickname(), req.getFromUserNickname());

    }

}
