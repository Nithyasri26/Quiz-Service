package com.cg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id
    @NotBlank(message = "Quiz ID cannot be empty")
    private String quizId;

    @NotBlank(message = "Quiz title cannot be empty")
    @Size(min = 3, max = 100, message = "Quiz title must be between 3 and 100 characters")
    private String title;

    @Min(value = 0, message = "Marks must be a non-negative number")
    @Max(value = 100, message = "Marks cannot exceed 100")
    private int marks;

    @NotBlank(message = "Course Id cannot be empty")
    private String courseId;
}
