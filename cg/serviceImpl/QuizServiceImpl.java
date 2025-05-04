package com.cg.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cg.dto.QuestionDto;
import com.cg.dto.QuizSubmissionDto;
import com.cg.entities.Question;
import com.cg.entities.Quiz;
import com.cg.entities.QuizOption;
import com.cg.exception.QuizNotFoundException;
import com.cg.repository.QuestionRepository;
import com.cg.repository.QuizOptionRepository;
import com.cg.repository.QuizRepository;
import com.cg.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;
    
    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuizById(String quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found with ID: " + quizId));
    }
   
    @Override
    public List<Quiz> getAllQuizzesByCourseId(){
    	return quizRepository.findAll();
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

//    @Override
//    public Page<Quiz> getAllQuizzesByPage(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return quizRepository.findAll(pageable);
//    }

    @Override
    public Quiz updateQuiz(String quizId, Quiz quizDetails) {
        Quiz existingQuiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found with ID: " + quizId));

        existingQuiz.setTitle(quizDetails.getTitle());
        existingQuiz.setMarks(quizDetails.getMarks());

        return quizRepository.save(existingQuiz);
    }

    @Override
    public void deleteQuiz(String quizId) {
        if (!quizRepository.existsById(quizId)) {
            throw new QuizNotFoundException("Quiz not found with ID: " + quizId);
        }
        quizRepository.deleteById(quizId);
    }
    
  @Override
    public int evaluateQuiz(QuizSubmissionDto submission) {
        int totalMarks = 0;
        String userId = submission.getUserId();  

        for (Map.Entry<String, String> entry : submission.getUserResponses().entrySet()) {
            String questionId = entry.getKey();
            String studentAnswer = entry.getValue();

            Question question = questionRepository.findById(questionId).orElse(null);

            String correctAns = question.getCorrectAnswer().toLowerCase().replaceAll("\\s+", "");
            String userAns = studentAnswer.toLowerCase().replaceAll("\\s+", "");

            if (correctAns.equals(userAns)) {
                totalMarks++;
           }

        }

        System.out.println("User " + userId + " scored: " + totalMarks); 

        return totalMarks;
    } 



}
