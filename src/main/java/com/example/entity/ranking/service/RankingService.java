package com.example.entity.ranking.service;

import com.example.entity.ranking.dto.RankingDto;

public interface RankingService {
    RankingDto.Response getRanking(long userId);
}
