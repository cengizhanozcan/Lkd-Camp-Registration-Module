package tr.org.lkd.lyk2015.camp.service;

import java.util.Calendar;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.common.DateBaseModel;
import tr.org.lkd.lyk2015.camp.dal.InstructorDao;
import tr.org.lkd.lyk2015.camp.model.AbstractUser;
import tr.org.lkd.lyk2015.camp.model.Instructor;

/*
*cengizhan - Aug 16, 2015
*/
@Transactional
@Service
public class InstructorService extends GenericService<Instructor>{

	@Autowired
	protected InstructorDao instructorDao;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public Long create(Instructor instructor) {

		if (instructor == null) {
			throw new RuntimeException("Instructor cannot create");
		}
		setBaseAttribute(instructor);

		return super.create(instructor);
	}

	private void setBaseAttribute(AbstractUser instructor) {

		Calendar cal = DateBaseModel.getCurrentTimeAsCalendar();
		instructor.setCreateDate(cal);
		instructor.setUpdateDate(cal);
		instructor.setDeleted(false);
	}

	public List<Instructor> getInstructors(){
		
		return instructorDao.getInstructures();
	}
	
}
