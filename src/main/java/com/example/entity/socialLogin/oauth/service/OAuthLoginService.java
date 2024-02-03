package com.example.entity.socialLogin.oauth.service;

import com.example.entity.user.domain.OAuthProvider;
import com.example.entity.user.domain.User;
import com.example.entity.user.repository.UserRepository;
import com.example.entity.socialLogin.oauth.oauthApi.params.OAuthLoginParams;
import com.example.entity.socialLogin.oauth.oauthApi.response.OAuthInfoResponse;
import com.example.entity.socialLogin.oauth.tokens.AuthTokens;
import com.example.entity.socialLogin.oauth.oauthApi.response.RequestOAuthInfoService;
import com.example.entity.socialLogin.oauth.tokens.AuthTokensGenerator;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

//    public AuthTokens login(OAuthLoginParams params) {
//        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
//        // param 값을 통한, access, refresh, grantType, 유효시간을 반환한다.
//        Long memberId = findOrCreateMember(oAuthInfoResponse);
//
//        return authTokensGenerator.generate(memberId);
//        // JWT 토큰으로 엑세스 토큰, 리프래쉬 토큰이 만들어져서 리턴된다
//    }

    public LoginResult login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        OAuthProvider oAuthProvider = oAuthInfoResponse.getOAuthProvider();
        String email = oAuthInfoResponse.getEmail();
        Long userId = findOrCreateMember(oAuthInfoResponse);
        AuthTokens authTokens = authTokensGenerator.generate(userId);

        return new LoginResult(oAuthProvider,email,authTokens);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        User user = userRepository.findByEmailAndOAuthProvider(
                oAuthInfoResponse.getEmail(),
                oAuthInfoResponse.getOAuthProvider()
        );

        if (user != null) {
            return user.getId();
        } else {
            return newMember(oAuthInfoResponse);
        }
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        User user = User.builder()
                .email(oAuthInfoResponse.getEmail())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return userRepository.save(user).getId();
    }

    public Boolean findNickname(String email) {
        Optional<User> findUser = userRepository.findByEmail(email);
        String nickname = findUser.get().getNickname();
        if(nickname == null) {
            return false;
        } else
            return true;
    }
    public boolean findAllNickname(String nickname) {
        Optional<User> findNickname = userRepository.findByNickname(nickname);
        // 닉네임을 가진 유저가 있으면 false, 없으면 true
        if (findNickname.isPresent())
            return false;
        else
            return true;

    }

    @Transactional
    public Boolean registerNickname(nicknameRequest request) {
        Optional<User> findUser = userRepository.findByEmail(request.getEmail());

        
        if (findUser.isPresent()) {
            User user = findUser.get();
            System.out.println("user.getEmail() = " + user.getEmail());
            user.changeNickname(request.getNickname());
        }

        return true;
    }

    private User findUser(OAuthInfoResponse oAuthInfoResponse) {
        Optional<User> findUser = userRepository.findByEmail(oAuthInfoResponse.getEmail());

        return findUser.orElse(null);
    }

    @Data
    @AllArgsConstructor
    public static class LoginResult {
        private final OAuthProvider oAuthProvider;
        private final String email;
        private final AuthTokens authTokens;
    }

    @Data
    @AllArgsConstructor
    public static class nicknameRequest {
        private final String email;
        private final String nickname;
    }

}
