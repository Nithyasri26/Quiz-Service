package com.cg.dto;

import java.util.Map;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizSubmissionDto {

    @NotBlank(message = "Quiz ID cannot be empty")
    private String quizId;

    @NotBlank(message = "User ID cannot be empty")
    private String userId;

    @NotEmpty(message = "User responses cannot be empty")
    private Map<String, String> userResponses;
}
