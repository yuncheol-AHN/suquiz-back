package com.example.entity.bookmark.service;

import com.example.entity.bookmark.dto.BookmarkDTO;
import com.example.entity.user.User;

import java.util.List;

public interface BookmarkService {

    BookmarkDTO.checkResponse findAllByUser(String userEmail);

    void addWordsByUser(String email, String wordName);
    void deleteWordsByUser(String email, String wordName);
}
