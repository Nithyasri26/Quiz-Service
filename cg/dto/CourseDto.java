package com.cg.dto;

import com.cg.dto.CourseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
	
	  private String courseId;
	    private String courseName;
	    private String description;
	    private float price;
	    private int duration;
	    private String category;
	    private float rating;
	    private String difficulty;

}
