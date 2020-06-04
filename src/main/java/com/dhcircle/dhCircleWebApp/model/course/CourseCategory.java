package com.dhcircle.dhCircleWebApp.model.course;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Getter @Setter @NoArgsConstructor
@ToString
public class CourseCategory extends AuditModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String topicName;
	private boolean isEnabled;
	@ToString.Exclude
	@OneToMany(mappedBy = "courseTopic",fetch = FetchType.LAZY)
	private Set<Courses> courses;
}
