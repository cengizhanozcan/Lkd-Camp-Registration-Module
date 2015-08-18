package tr.org.lkd.lyk2015.camp.dal;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
*cengizhan - Aug 18, 2015
*/

@Repository
public class ApplicationDao {
	
	@Autowired
	protected SessionFactory sessionFactory;
}
