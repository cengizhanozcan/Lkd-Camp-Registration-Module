package tr.org.lkd.lyk2015.camp.dal;

import java.util.List;

import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.AbstractUser;
import tr.org.lkd.lyk2015.camp.model.Admin;

/*
*cengizhan - Aug 16, 2015
*/


public interface AdminDao {

	Long create(final AbstractUser admin);
	AbstractUser getById(final Long Id); 
	AbstractUser update(final AbstractUser admin);
	List<AbstractUser> getAdmins();
	
}
