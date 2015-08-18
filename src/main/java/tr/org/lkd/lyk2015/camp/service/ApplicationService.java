package tr.org.lkd.lyk2015.camp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.dal.ApplicationDao;
import tr.org.lkd.lyk2015.camp.model.Application;

/*
*cengizhan - Aug 18, 2015
*/


@Transactional
@Service
public class ApplicationService extends GenericService<Application>{

	@Autowired
	private ApplicationDao applicationDao;
	
}
