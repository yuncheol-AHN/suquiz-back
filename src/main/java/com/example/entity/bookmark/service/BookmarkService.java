package com.example.entity.bookmark.service;

import com.example.entity.bookmark.dto.BookmarkDTO;

public interface BookmarkService {

    BookmarkDTO.checkResponse findAllByUser(String userEmail);

    void addWordsByUser(String email, String wordName);
    void deleteWordsByUser(String email, String wordName);
}
