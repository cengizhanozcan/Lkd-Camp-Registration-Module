package tr.org.lkd.lyk2015.camp.service;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.common.DateBaseModel;
import tr.org.lkd.lyk2015.camp.dal.AdminDao;
import tr.org.lkd.lyk2015.camp.model.AbstractUser;
import tr.org.lkd.lyk2015.camp.model.Admin;

/*
*cengizhan - Aug 16, 2015
*/

@Transactional
@Service
public class AdminService {

	@Autowired
	protected AdminDao adminDao;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	
	public Long create(AbstractUser admin){
		
		if(admin == null){
			throw new RuntimeException("Admin cannot create");
		}
		
		setBaseAttribute(admin);
		
		return adminDao.create(admin);
	}

	/*
	 * Temel attribütler set ediliyor.
	 */
	private void setBaseAttribute(AbstractUser admin) {
		
		Calendar cal = DateBaseModel.getCurrentTimeAsCalendar();
		admin.setCreateDate(cal);
		admin.setUpdateDate(cal);
		admin.setDeleted(false);
	}

	public List<AbstractUser> getAdmins(){
		
		return adminDao.getAdmins();
	} 
	
}
