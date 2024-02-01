package com.example.entity.bookmark.controller;

import com.example.entity.bookmark.dto.BookmarkDTO;
import com.example.entity.bookmark.service.BookmarkService;
import com.example.entity.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping("/words")
    public ResponseEntity<CommonResponse> allWordsByUser(@RequestParam("user") String userEmail) {
        BookmarkDTO.checkResponse allByUser = bookmarkService.findAllByUser(userEmail);
//        BookmarkDTO.checkResponse user2 = bookmarkService.findAllByUser(userEmail);
//
//        Object[] obj = new Object[2];
//        obj[0] = allByUser;
//        obj[1] = user2;
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("유저가 북마크한 모든 단어 조회 성공")
                .data(allByUser)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/words")
    public ResponseEntity<CommonResponse> addWordsByUser(@RequestBody BookmarkDTO.addRequest request) {
        String userEmail = request.getUserEmail();
        String wordName = request.getWordName();
        bookmarkService.addWordsByUser(userEmail, wordName);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("북마크에 단어 추가")
                .data(BookmarkDTO.addResponse.builder().isBookmarked(true).build())
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/words")
    public ResponseEntity<CommonResponse> deleteWordsByUser(@RequestBody BookmarkDTO.deleteRequest request) {
        String userEmail = request.getUserEmail();
        String wordName = request.getWordName();
        bookmarkService.deleteWordsByUser(userEmail, wordName);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("북마크에서 단어 삭제")
                .data(BookmarkDTO.deleteResponse.builder().isBookmarked(false).build())
                .build(), HttpStatus.OK);
    }
}
