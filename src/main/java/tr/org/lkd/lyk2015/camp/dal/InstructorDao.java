package tr.org.lkd.lyk2015.camp.dal;

import java.util.List;

import tr.org.lkd.lyk2015.camp.model.AbstractUser;

/*
*cengizhan - Aug 16, 2015
*/
public interface InstructorDao {
	
	Long create(final AbstractUser instructure);
	AbstractUser getById(final Long id);
	AbstractUser update(final AbstractUser instructure);
	List<AbstractUser> getInstructures();

}
