package tr.org.lkd.lyk2015.camp.service;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.common.DateBaseModel;
import tr.org.lkd.lyk2015.camp.dal.CourseDao;
import tr.org.lkd.lyk2015.camp.model.AbstractUser;
import tr.org.lkd.lyk2015.camp.model.Course;

/*
*cengizhan - Aug 16, 2015
*/

@Transactional
@Service
public class CourseService extends GenericService<Course> {

	@Autowired
	private CourseDao courseDao;

	public Long create(Course course) {

		if (course == null) {
			throw new RuntimeException("Course cannot create");
		}
		setBaseAttribute(course);
		
		return super.create(course);
	}

	
	private void setBaseAttribute(Course course) {

		Calendar cal = DateBaseModel.getCurrentTimeAsCalendar();
		course.setCreateDate(cal);
		course.setUpdateDate(cal);
		course.setDeleted(false);
	}


	@Override
	public List<Course> getAll() {
		
		return courseDao.getAll();
	}
	
	

}
