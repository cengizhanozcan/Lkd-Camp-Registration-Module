package tr.org.lkd.lyk2015.camp.service;

/*
*cengizhan - Aug 19, 2015
*/
public interface MailService {

	Boolean sendActivationEmail(String sender, String subject, String content);
}
