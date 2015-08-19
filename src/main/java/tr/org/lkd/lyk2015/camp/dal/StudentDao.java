package tr.org.lkd.lyk2015.camp.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Student;

/*
*cengizhan - Aug 19, 2015
*/

@Repository
public class StudentDao extends GenericDao<Student> {

	@Autowired
	SessionFactory sessionFactory;

	public Student checkStudent(Long tckn) {
		final Session session = this.sessionFactory.getCurrentSession();
		Student student = (Student) session.createCriteria(Student.class, "student")
				.add(Restrictions.eq("student.tckn", tckn)).uniqueResult();

		return student;
	}

}
