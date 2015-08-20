package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
*cengizhan - Aug 18, 2015
*/

@Service
@Transactional
public class MockExamination implements ExaminationService {

	@Override
	public Boolean examinationIsSuccess(String name, String surname, Long tckn, String email) {
		return true;
	}

}
