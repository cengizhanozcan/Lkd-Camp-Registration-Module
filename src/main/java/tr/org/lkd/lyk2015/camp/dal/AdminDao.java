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
import tr.org.lkd.lyk2015.camp.model.Admin;

/*
*cengizhan - Aug 16, 2015
*/

@Repository
public class AdminDao extends GenericDao<Admin>{

	@Autowired
	protected SessionFactory sessionFactory;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public List<AbstractUser> getAdmins() {
		
		final Session session = sessionFactory.getCurrentSession();
		List<AbstractUser> admins = new ArrayList<>();
		admins = session.createCriteria(Admin.class, "admin").add(Restrictions.eq("admin.deleted", false)).list();
		return admins;
	}
}
