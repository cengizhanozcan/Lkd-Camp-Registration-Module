package tr.org.lkd.lyk2015.camp.service;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class AdminService extends GenericService<Admin> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Long create(Admin admin) {

		if (admin == null) {
			throw new RuntimeException("Admin cannot create");
		}

		this.setBaseAttribute(admin);
		admin.setPassword(this.passwordEncoder.encode(admin.getPassword()));

		return super.create(admin);
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

	public List<AbstractUser> getAdmins() {

		return this.adminDao.getAdmins();
	}

	@Override
	public Admin update(Admin admin) {

		this.setBaseAttribute(admin);
		return super.update(admin);

	}

}
