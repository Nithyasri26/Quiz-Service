package com.cg.microservicecalls;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.dto.CourseDto;

@FeignClient(name="COURSE-SERVICE")
public interface CourseClient {
	
	 @GetMapping("/course/searchcoursebyId/{courseId}")  
	    CourseDto getCourseById(@PathVariable("courseId") String courseId);
	 
	 
	    @GetMapping("/course/getAllCourses")
	   List<CourseDto>  getAllCourses();

}
