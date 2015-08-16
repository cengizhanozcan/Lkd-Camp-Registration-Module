package tr.org.lkd.lyk2015.camp.dal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Instructor;

/*
*cengizhan - Aug 16, 2015
*/


@Repository
public class CourseDao extends GenericDao<Course>{
	
	
	@Autowired
	protected SessionFactory sessionFactory;

	public List<Course> getCourses() {

		final Session session = sessionFactory.getCurrentSession();
		List<Course> courses = new ArrayList<>();
		courses = session.createCriteria(Course.class, "course")
				.add(Restrictions.eq("course.deleted", false)).list();
		
		return courses;
	
	}

}
