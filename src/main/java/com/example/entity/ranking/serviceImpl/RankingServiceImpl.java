package com.example.entity.ranking.serviceImpl;

import com.example.entity.global.service.EntityAndDtoConversionService;
import com.example.entity.ranking.dto.RankingDto;
import com.example.entity.ranking.service.RankingService;
import com.example.entity.user.domain.User;
import com.example.entity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final UserRepository userRepository;
    private final EntityAndDtoConversionService entityAndDtoConversionService;

    @Override
    public RankingDto.Response getRanking(long userId) {
        List<User> orderedUserList = userRepository.findAllOrderByExp();
        List<RankingDto.RankDto> ranking = new ArrayList<>();
        int myRank = 0;
        for(int i=0; i<orderedUserList.size(); i++) {
            if(i<10) {
                ranking.add(entityAndDtoConversionService.userEntityToRankDto(orderedUserList.get(i)));
            }
            if(orderedUserList.get(i).getId() == userId) {
                myRank = i+1;
                if(i>=10) break;
            }
        }

        return RankingDto.Response.builder()
                .myRank(myRank)
                .ranking(ranking)
                .build();

    }
}
