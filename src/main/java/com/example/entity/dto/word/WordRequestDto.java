package com.example.entity.dto.word;

import com.example.entity.word.Category;
import com.example.entity.word.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordRequestDto {
    private String wordName;
    private String videoUrl;
    private Category category;
    private Subject subject;
}
