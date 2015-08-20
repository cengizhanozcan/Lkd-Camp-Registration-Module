package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
*cengizhan - Aug 18, 2015
*/

@Service
@Transactional
public class MockBlackList implements BlackListService {

	@Override
	public Boolean inBlackList(Long tckn, String email) {

		return true;
	}

}
