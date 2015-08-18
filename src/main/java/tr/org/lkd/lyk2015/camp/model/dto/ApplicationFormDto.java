package tr.org.lkd.lyk2015.camp.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.model.Student;

/*
*cengizhan - Aug 17, 2015
*/
public class ApplicationFormDto {

	@Valid
	private Application application = new Application();

	@Valid
	private Student student = new Student();

	@Size(min = 1, max = 3)
	private List<Long> preferredCourseIds = new ArrayList<>();

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application applicationForm) {
		this.application = applicationForm;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Long> getPreferredCourseIds() {
		return preferredCourseIds;
	}

	public void setPreferredCourseIds(List<Long> preferredCourseIds) {
		this.preferredCourseIds = preferredCourseIds;
	}
 

}