package tr.org.lkd.lyk2015.camp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.dal.UserDao;
import tr.org.lkd.lyk2015.camp.model.AbstractUser;

/*
*cengizhan - Aug 20, 2015
*/

@Service
@Transactional
public class UserDetailServiceImp implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AbstractUser user = this.userDao.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User Yok ya la");
		}

		return user;
	}

}
