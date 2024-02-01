package com.example.entity.friend.serviceImpl;

import com.example.entity.friend.dto.FriendDto;
import com.example.entity.user.domain.User;
import com.example.entity.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
@Rollback(value = false)

class FriendRelationshipServiceImplTest {

    @Autowired
    FriendRelationshipServiceImpl friendRelationshipService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;


    @Test
    public void serachUsersTest() {
        User userA = User.builder()
                .email("a")
                .nickname("윤철")
                .level(1)

                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();

        User userB = User.builder()
                .email("b")
                .nickname("윤철갓")
                .level(5)

                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User userC = User.builder()
                .email("c")
                .nickname("김현준")
                .level(10)

                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        userRepository.save(userA);
        userRepository.save(userB);
        userRepository.save(userC);

        em.flush();

        List<FriendDto.Response> searchUsers = friendRelationshipService.searchUsers("윤");

        for(FriendDto.Response res : searchUsers) {
            System.out.println(" 닉네임 : " + res.getNickname());
            System.out.println(" 레벨 : " + res.getLevel());
        }

    }


    @Test
    public void requestFriendTest() {


        FriendDto.Request req = FriendDto.Request.builder().fromUserNickname("윤철").ToUserNickname("김현준").build();
        friendRelationshipService.requestFriend(req);

    }

    @Test
    public void getRequestListTest() {

        List<FriendDto.Response> requestList = friendRelationshipService.getRequestList(3);

        for(FriendDto.Response res : requestList) {
            System.out.println(res.getNickname());
        }

    }

    @Test
    public void acceptFriendTest() {
        FriendDto.Request req = FriendDto.Request.builder().fromUserNickname("김현준").ToUserNickname("윤철").build();
        friendRelationshipService.acceptFriend(req);

    }
    @Test
    public void getFriendListTest() {
        List<FriendDto.Response> yunfriendList = friendRelationshipService.getFriendList(1);
        for(FriendDto.Response res : yunfriendList) {
            System.out.println("윤철의 친구 : " + res.getNickname());
        }

        List<FriendDto.Response> kimfriendList = friendRelationshipService.getFriendList(3);
        for(FriendDto.Response res : kimfriendList) {
            System.out.println("현준의 친구 : " + res.getNickname());
        }


    }

    @Test
    public void removeFriendTest() {
        FriendDto.Request req = FriendDto.Request.builder().fromUserNickname("김현준").ToUserNickname("윤철").build();
        friendRelationshipService.removeFriend(req);


    }


}