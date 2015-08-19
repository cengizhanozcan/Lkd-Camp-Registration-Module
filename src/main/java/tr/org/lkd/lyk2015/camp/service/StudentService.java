package tr.org.lkd.lyk2015.camp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.dal.StudentDao;
import tr.org.lkd.lyk2015.camp.model.Student;

/*
*cengizhan - Aug 19, 2015
*/

@Transactional
@Service
public class StudentService extends GenericService<Student> {

	@Autowired
	private StudentDao studentDao;

	public Student checkStudentExist(Student student) {

		Student checkedStudent = this.studentDao.checkStudent(student.getTckn());

		if (checkedStudent == null) {
			this.studentDao.create(student);
			checkedStudent = this.studentDao.checkStudent(student.getTckn());
		}

		return checkedStudent;
	}
}
