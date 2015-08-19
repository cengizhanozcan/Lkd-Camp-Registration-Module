package tr.org.lkd.lyk2015.camp.dal;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Application;

/*
*cengizhan - Aug 18, 2015
*/

@Repository
public class ApplicationDao extends GenericDao<Application> {

	@Autowired
	protected SessionFactory sessionFactory;

	public Application getValidator(String confirmationCode) {

		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("validationId", confirmationCode));
		return (Application) criteria.uniqueResult();

	}

}
