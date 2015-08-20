package tr.org.lkd.lyk2015.camp.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.common.DateBaseModel;
import tr.org.lkd.lyk2015.camp.dal.CourseDao;
import tr.org.lkd.lyk2015.camp.dal.InstructorDao;
import tr.org.lkd.lyk2015.camp.model.AbstractUser;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Instructor;

/*
*cengizhan - Aug 16, 2015
*/
@Transactional
@Service
public class InstructorService extends GenericService<Instructor> {

	@Autowired
	protected InstructorDao instructorDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	protected CourseDao courseDao;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private void setBaseAttribute(AbstractUser instructor) {

		Calendar cal = DateBaseModel.getCurrentTimeAsCalendar();
		instructor.setCreateDate(cal);
		instructor.setUpdateDate(cal);
		instructor.setDeleted(false);
	}

	public List<Instructor> getInstructors() {

		return this.instructorDao.getInstructures();
	}

	public void create(Instructor instructor, List<Long> courseIds) {

		if (instructor == null) {
			throw new RuntimeException("Instructor cannot create");
		}
		this.setBaseAttribute(instructor);
		List<Course> courses = this.courseDao.getByIds(courseIds);
		Set<Course> setCourses = new HashSet<>();
		setCourses.addAll(courses);

		// instructor.getCourses().addAll(courses);

		instructor.setCourses(setCourses);
		instructor.setPassword(this.passwordEncoder.encode(instructor.getPassword()));

		this.instructorDao.create(instructor);

	}

	public Instructor getInstructorWithCourses(Long id) {

		// Instructore instructure = getById(id)
		// Hibernate.initialize(instructor.getCourses())
		return this.instructorDao.getInstructorWithCourses(id);

	}

}
