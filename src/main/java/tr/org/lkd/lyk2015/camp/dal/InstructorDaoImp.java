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

import tr.org.lkd.lyk2015.camp.model.AbstractUser;
import tr.org.lkd.lyk2015.camp.model.Instructor;

/*
*cengizhan - Aug 16, 2015
*/

@Repository
public class InstructorDaoImp implements InstructorDao {

	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Long create(AbstractUser instructure) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(instructure);
	}

	@Override
	public AbstractUser getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractUser update(AbstractUser instructure) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AbstractUser> getInstructures() {
		final Session session = sessionFactory.getCurrentSession();
		List<AbstractUser> instructors = new ArrayList<>();
		
		instructors = session.createCriteria(Instructor.class, "instructor")
				.add(Restrictions.eq("instructor.deleted", false))
				.list();
		
		return instructors;
	}

}
