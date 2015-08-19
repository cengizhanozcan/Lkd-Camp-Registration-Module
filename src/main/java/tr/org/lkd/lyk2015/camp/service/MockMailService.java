package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
*cengizhan - Aug 19, 2015
*/

@Service
@Transactional
public class MockMailService implements MailService {

	@Override
	public Boolean sendActivationEmail(String sender, String subject, String content) {

		if (!sender.equals("a@a.com"))
			return false;

		return true;
	}

}
