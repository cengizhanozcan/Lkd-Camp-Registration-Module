package tr.org.lkd.lyk2015.camp.service;

import java.util.Calendar;
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

	private static final String URL_BASE = "http://localhost:8080/camp/applicationForm/validate/";

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private StudentService studentService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ApplicationDao applicationDao;

	private String generateUUID() {

		UUID uuid = UUID.randomUUID();
		return uuid.toString();

	}

	public String createConfirmationUrl(String url) {

		return URL_BASE + url;
	}

	private List<Course> getCourseByIds(ApplicationFormDto applicationFormDto, List<Long> preferredCourseIds) {

		Application application = applicationFormDto.getApplication();
		return this.courseDao.getByIds(preferredCourseIds);
	}

	public void createApplication(ApplicationFormDto applicationFormDto) {

		List<Course> courses = this.getCourseByIds(applicationFormDto, applicationFormDto.getPreferredCourseIds());
		applicationFormDto.getApplication().getPreferredCourses().clear();
		applicationFormDto.getApplication().getPreferredCourses().addAll(courses);

		int year = Calendar.getInstance().get(Calendar.YEAR);
		applicationFormDto.getApplication().setYear(year);
		// -----------------------------------------------------------------------------
		System.out.println("ApplicationSErver ---- getCourseByıdd ---------------");
		for (Course course : applicationFormDto.getApplication().getPreferredCourses()) {
			System.out.println("course :" + course.getName() + "Id" + course.getId());
		}
		// -----------------------------------------------------------------------------

		Application application = applicationFormDto.getApplication();
		Student student = this.studentService.checkStudentExist(applicationFormDto.getStudent());

		String url = this.generateUUID();

		application.setValidationId(url);
		application.setValidated(false);
		// Yeni eklenen
		application.setOwner(student);
		// -----------

		this.emailService.sendActivationEmail(student.getEmail(), "Activation", this.createConfirmationUrl(url));
		// Set<Application> applications = student.getApplicationForms();
		// applications.add(application);
		// student.setApplicationForms(applications);
		// applicationFormDto.setStudent(student);

		Long success = this.applicationDao.create(application);
	}

	public String validation(String confirmationCode) {

		System.out.println(confirmationCode);
		Application application = this.applicationDao.getValidator(confirmationCode);

		if (application == null) {
			return "yok";
		} else {
			if (application.getValidated()) {
				return "mevcut";
			}
			application.setValidated(true);
			this.applicationDao.update(application);
		}
		return "basarili";
	}

}
