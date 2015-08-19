package tr.org.lkd.lyk2015.camp.service;

/*
*cengizhan - Aug 19, 2015
*/
public interface EmailService {

	Boolean sendActivationEmail(String sender, String subject, String content);
}
