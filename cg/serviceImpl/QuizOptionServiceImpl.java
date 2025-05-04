package com.cg.serviceImpl;

import com.cg.entities.QuizOption;
import com.cg.exception.QuizNotFoundException;
import com.cg.repository.QuizOptionRepository;
import com.cg.service.QuizOptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizOptionServiceImpl implements QuizOptionService {

    @Autowired
    private QuizOptionRepository quizOptionRepository;
    

    @Override
    public QuizOption addQuizOption(QuizOption quizOption) {
        return quizOptionRepository.save(quizOption);
    }

    @Override
    public List<QuizOption> getAllQuizOptions() {
        return quizOptionRepository.findAll();
    }

    @Override
    public List<QuizOption> getOptionsByQuestionId(String questionId) {
        return quizOptionRepository.findByQuestionId(questionId);
    }

    @Override
    public QuizOption updateQuizOption(String optionId, QuizOption updatedOption) {
        Optional<QuizOption> existingOption = quizOptionRepository.findById(optionId);
        if (existingOption.isPresent()) {
            QuizOption quizOptions = existingOption.get();
            quizOptions.setOption1(updatedOption.getOption1());
            quizOptions.setOption2(updatedOption.getOption2());
            quizOptions.setOption3(updatedOption.getOption3());
            quizOptions.setOption4(updatedOption.getOption4());
            quizOptions.setQuestionId(updatedOption.getQuestionId());
            return quizOptionRepository.save(quizOptions);
        }
        throw new RuntimeException("QuizOption with ID " + optionId + " not found");
    }
    @Override
    public void deleteQuizOption(String optionId) {
        boolean exists = quizOptionRepository.existsById(optionId);
        if (!exists) {
            throw new QuizNotFoundException("Option not found with ID: " + optionId);
        }
        quizOptionRepository.deleteById(optionId);
    }

  
}

