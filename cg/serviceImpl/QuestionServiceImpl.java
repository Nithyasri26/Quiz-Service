package com.cg.serviceImpl;

import com.cg.dto.QuestionDto;
import com.cg.entities.Question;
import com.cg.entities.QuizOption;
import com.cg.exception.QuestionNotFoundException;
import com.cg.repository.QuestionRepository;
import com.cg.repository.QuizOptionRepository;
import com.cg.service.QuestionService;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Arrays;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private QuizOptionRepository quizOptionRepository;

    
    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public QuestionDto getQuestionById(String questionId) {
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new QuestionNotFoundException("Question not found with ID: " + questionId));
        List<QuizOption> options = quizOptionRepository.findByQuestionId(questionId);
        return new QuestionDto(
            question.getQuestionId(),
            question.getQuestionText(),
            question.getQuizId(),
            question.getCorrectAnswer(),
          
            options
        );
    }

   
    @Override
    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll().stream().map(question-> {
        	List<QuizOption> options=quizOptionRepository.findByQuestionId(question.getQuestionId());
        	return new QuestionDto(
        			question.getQuestionId(),
        			question.getQuestionText(),
        			question.getQuizId(),
        			question.getCorrectAnswer(),
        			
        			options
                  );
        }).collect(Collectors.toList());
    }
    
  
    @Override
    public Question updateQuestion(String questionId, Question questionDetails) {
        Optional<Question> question = questionRepository.findById(questionId);
        
        if (question.isPresent()) {
            Question existingQuestion = question.get();
            existingQuestion.setQuestionText(questionDetails.getQuestionText());
            existingQuestion.setCorrectAnswer(questionDetails.getCorrectAnswer());
            return questionRepository.save(existingQuestion);
        }
        throw new QuestionNotFoundException("Question not found with ID: " + questionId);
    }

 
    @Override
    public void deleteQuestion(String questionId) {
        if (!questionRepository.existsById(questionId)) {
            throw new QuestionNotFoundException("Question not found with ID: " + questionId);
        }
        questionRepository.deleteById(questionId);
    }


    @Override
    public List<Question> searchQuestionsByKeyword(String keyword) {
        return questionRepository.findByQuestionTextContainingIgnoreCase(keyword);
    }

    
    @Override
    public List<QuestionDto> searchQuestionsByQuizId(String quizId){
    	List<Question> questions= questionRepository.findByQuizId(quizId);
    	
    	return questions.stream().map(question-> {
    		List<QuizOption> options=quizOptionRepository.findByQuestionId(question.getQuestionId());
    		
    		return new QuestionDto(
    				question.getQuestionId(),
    				question.getQuestionText(),
    				question.getQuizId(),
    				question.getCorrectAnswer(),
    				
    				options);
    		}).collect(Collectors.toList());
    				
    	}
     
}
