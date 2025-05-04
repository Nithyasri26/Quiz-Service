package com.cg.service;

import java.util.List;

import com.cg.dto.QuestionDto;
import com.cg.entities.Question;

public interface QuestionService {
	
	  Question addQuestion(Question question);
	  QuestionDto getQuestionById(String questionId);
	  List<QuestionDto> getAllQuestions();
	  List<QuestionDto> searchQuestionsByQuizId(String quizId);
	  Question updateQuestion(String questionId, Question questionDetails);
	  void deleteQuestion(String questionId);
	  List<Question> searchQuestionsByKeyword(String keyword);

}
