package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
*cengizhan - Aug 19, 2015
*/

@Service
@Transactional
public class MockMailService implements EmailService {

	@Override
	public Boolean sendActivationEmail(String sender, String subject, String content) {

		SendEmail.send(sender, subject, content);
		System.out.println(sender + " " + subject + " " + content);

		return null;
	}

}
