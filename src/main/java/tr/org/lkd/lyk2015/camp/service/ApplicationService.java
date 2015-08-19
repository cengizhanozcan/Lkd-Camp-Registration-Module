package tr.org.lkd.lyk2015.camp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.dal.ApplicationDao;
import tr.org.lkd.lyk2015.camp.dal.CourseDao;
import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.model.dto.ApplicationFormDto;

/*
*cengizhan - Aug 18, 2015
*/

@Transactional
@Service
public class ApplicationService extends GenericService<Application> {

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ApplicationDao applicationDao;

	private String generateUUID() {

		UUID uuid = UUID.randomUUID();
		return uuid.toString();

	}

	public String createConfirmationUrl() {

		String url = "http://localhost:8080/camp/applicationForm/validate/" + this.generateUUID();
		return url;
	}

	private List<Course> getCourseByIds(ApplicationFormDto applicationFormDto, List<Long> preferredCourseIds) {

		Application application = applicationFormDto.getApplication();
		return this.courseDao.getByIds(preferredCourseIds);
	}

	public void createApplication(ApplicationFormDto applicationFormDto) {

		List<Course> courses = this.getCourseByIds(applicationFormDto, applicationFormDto.getPreferredCourseIds());

		applicationFormDto.getApplication().getPreferredCourses().addAll(courses);

		// -----------------------------------------------------------------------------
		System.out.println("ApplicationSErver ---- getCourseByıdd ---------------");
		for (Course course : applicationFormDto.getApplication().getPreferredCourses()) {
			System.out.println("course :" + course.getName() + "Id" + course.getId());
		}
		// -----------------------------------------------------------------------------

		Student student = this.studentService.checkStudentExist(applicationFormDto.getStudent());
		applicationFormDto.setStudent(student);

		Long success = this.applicationDao.create(applicationFormDto.getApplication());
	}

}
