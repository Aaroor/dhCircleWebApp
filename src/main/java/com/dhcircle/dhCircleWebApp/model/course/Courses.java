package com.dhcircle.dhCircleWebApp.model.course;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.dhcircle.dhCircleWebApp.model.user.AuditModel;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @NoArgsConstructor
@ToString
public class Courses extends AuditModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String courseName;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "courses")
	Set<Resources> resourses;
}
