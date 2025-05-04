package com.cg.controller;

import com.cg.entities.QuizOption;
import com.cg.service.QuizOptionService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizOptions")

public class QuizOptionController {

    private static final Logger logger = LoggerFactory.getLogger(QuizOptionController.class);

    @Autowired
    private QuizOptionService quizOptionService;

    @PostMapping("/addQuizOption")
    public ResponseEntity<QuizOption> addQuizOption(@Valid @RequestBody QuizOption quizOption) {
        logger.info("Adding a new QuizOption: {}", quizOption);
        QuizOption savedOption = quizOptionService.addQuizOption(quizOption);
        logger.info("QuizOption added successfully with ID: {}", savedOption.getOptionId());
        return new ResponseEntity<>(savedOption, HttpStatus.CREATED);
    }

    @GetMapping("/viewAllQuizOptions")
    public ResponseEntity<List<QuizOption>> getAllQuizOptions() {
        logger.info("Fetching all quiz options");
        List<QuizOption> options = quizOptionService.getAllQuizOptions();

        if (options.isEmpty()) {
            logger.warn("No quiz options found in the database.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("Retrieved {} quiz options successfully", options.size());
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @GetMapping("/viewOptionsByQuestionId/{questionId}")
    public ResponseEntity<List<QuizOption>> getOptionsByQuestionId(@PathVariable String questionId) {
        logger.info("Fetching quiz options for question ID: {}", questionId);
        List<QuizOption> options = quizOptionService.getOptionsByQuestionId(questionId);

        if (options.isEmpty()) {
            logger.warn("No options found for question ID: {}", questionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("Retrieved {} options for question ID: {}", options.size(), questionId);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @PutMapping("/updateQuizOption/{optionId}")
    public ResponseEntity<QuizOption> updateQuizOption(@Valid @PathVariable String optionId, @RequestBody QuizOption updatedOption) {
        logger.info("Updating QuizOption with ID: {}", optionId);
        QuizOption updated = quizOptionService.updateQuizOption(optionId, updatedOption);
        logger.info("QuizOption updated successfully: {}", updated);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteQuizOption/{optionId}")
    public ResponseEntity<String> deleteQuizOption(@PathVariable String optionId) {
        logger.info("Deleting QuizOption with ID: {}", optionId);
        quizOptionService.deleteQuizOption(optionId);
        logger.info("QuizOption with ID: {} deleted successfully", optionId);
        return new ResponseEntity<>("Quiz Option deleted successfully", HttpStatus.OK);
    }
}

