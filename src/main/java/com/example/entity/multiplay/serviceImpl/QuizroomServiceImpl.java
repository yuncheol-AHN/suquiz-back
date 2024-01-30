package com.example.entity.multiplay.serviceImpl;

import com.example.entity.multiplay.domain.Quizroom;
import com.example.entity.user.domain.User;
import com.example.entity.multiplay.service.QuizroomService;
import com.example.entity.multiplay.repository.QuizroomRepository;
import com.example.entity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizroomServiceImpl implements QuizroomService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 8;
    private final QuizroomRepository quizroomRepository;
    private final UserRepository userRepository;

    @Override
    public int makeQuizroom(Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            String inviteCode = generateRandomCode();


            if(user.getQuizroom() == null) {

                Quizroom quizroom = Quizroom.builder().inviteCode(inviteCode).build();
                quizroomRepository.save(quizroom);
                user.changeQuizroom(quizroom);
                quizroom.addUser(user);
                return 1;
            }

        }

        return 0;

    }

    @Override
    public boolean checkIsRoomJoinable(String inviteCode) {
        Optional<Quizroom> optionalQuizroom = quizroomRepository.findByInviteCode(inviteCode);
        if(optionalQuizroom.isPresent()){
            Quizroom quizroom = optionalQuizroom.get();
            if(quizroom.getUserList().size()<4)
                return true;
        };
        return false;
    }

    private static String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
}
