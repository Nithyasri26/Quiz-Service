package com.cg.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Question;
import com.cg.entities.QuizOption;

import java.util.List;

@Repository
public interface QuizOptionRepository extends JpaRepository<QuizOption, String> {
    
	List<QuizOption> findByQuestionId(String questionId);
  
}
