package tr.org.lkd.lyk2015.camp.dal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Instructor;

/*
*cengizhan - Aug 16, 2015
*/

@Repository
public class InstructorDao extends GenericDao<Instructor> {

	@Autowired
	protected SessionFactory sessionFactory;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	
	public List<Instructor> getInstructures() {
		final Session session = sessionFactory.getCurrentSession();
		List<Instructor> instructors = new ArrayList<>();
		instructors = session.createCriteria(Instructor.class, "instructor")
				.add(Restrictions.eq("instructor.deleted", false)).list();
		return instructors;
	}


	public Instructor getByIdWithCourses(Long id) {

		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Instructor.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("*", FetchMode.JOIN);
		
		return (Instructor) criteria.uniqueResult();
		
	}
}
