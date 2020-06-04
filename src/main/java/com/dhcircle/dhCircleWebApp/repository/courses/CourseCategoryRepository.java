package com.dhcircle.dhCircleWebApp.repository.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhcircle.dhCircleWebApp.model.course.CourseCategory;
@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
	CourseCategory findByTopicName(String topicName);
}
