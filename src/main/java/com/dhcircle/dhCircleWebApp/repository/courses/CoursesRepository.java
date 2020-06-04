package com.dhcircle.dhCircleWebApp.repository.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhcircle.dhCircleWebApp.model.course.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {

}
