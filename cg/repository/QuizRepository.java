package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.dto.QuestionDto;
import com.cg.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, String> {
	


}
