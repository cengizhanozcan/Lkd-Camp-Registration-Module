package tr.org.lkd.lyk2015.camp.service;

/*
*cengizhan - Aug 18, 2015
*/

public interface TcknValidationService {

	boolean validate(String name, String surname, Integer birthDate, Long tckn);

}
