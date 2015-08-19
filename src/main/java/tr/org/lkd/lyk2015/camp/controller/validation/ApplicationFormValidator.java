package tr.org.lkd.lyk2015.camp.controller.validation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import tr.org.lkd.lyk2015.camp.model.Application.WorkStatus;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.model.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.service.BlackListService;
import tr.org.lkd.lyk2015.camp.service.ExaminationService;
import tr.org.lkd.lyk2015.camp.service.MailService;
import tr.org.lkd.lyk2015.camp.service.TcknValidationService;

/*
*cengizhan - Aug 18, 2015
*/

@Component
public class ApplicationFormValidator implements Validator {

	@Autowired
	TcknValidationService tcknValidationService;

	@Autowired
	BlackListService blackListService;

	@Autowired
	ExaminationService examinationService;

	@Autowired
	MailService mailService;

	@Override
	public boolean supports(Class<?> clazz) {

		// Validation esnasında çağırılcak.Valide olan classın tipini göndercek.
		return clazz.equals(ApplicationFormDto.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ApplicationFormDto application = (ApplicationFormDto) target;

		if (application.getApplication().getWorkStatus() == WorkStatus.NOT_WORKING
				&& application.getApplication().getOfficer()) {

			errors.rejectValue("workStatus", "error.notWorkingOfficer", "Hep Çalışmayıp hem memursun vay bee...");
		}

		//// nulları sil
		application.getPreferredCourseIds().removeAll(Collections.singleton(null));
		if (application.getPreferredCourseIds().size() == 0) {
			errors.rejectValue("preferredCourseIds", "error.preferredCourseNoSelection",
					"En az bir kurs seçmelisiniz...");
		}

		// Aynı olan kursları seçtirme
		int listSize = application.getPreferredCourseIds().size();
		Set<Long> set = new HashSet<>(application.getPreferredCourseIds());
		int setSize = set.size();

		// Aynı kursu secmis olanlar
		if (listSize != setSize) {

			errors.rejectValue("preferredCourseIds", "error.preferredCourseSame", "Aynı kursu iki kez seçemezsiniz...");
		}

		// TCkn validation from web service
		Student student = application.getStudent();

		this.tcknValidation(errors, student);

		this.isExaminationFail(errors, student);

		this.isBlackList(errors, student);

		this.activationMailValid(errors, student);

	}

	private void activationMailValid(Errors errors, Student student) {
		boolean isMailSuccess = this.mailService.sendActivationMail(student.getEmail(), "subject", "content");
		if (!isMailSuccess) {
			errors.rejectValue("student.email", "error.mail", "Aktivasyon Mail'i Gönderilemedi...");
		}
	}

	private void tcknValidation(Errors errors, Student student) {
		boolean tcknValid = this.tcknValidationService.validate(student.getName(), student.getSurname(),
				student.getBirthDate(), student.getTckn());

		if (!tcknValid) {
			errors.rejectValue("student.tckn", "error.tcknValid", "Tc Kimlik No uyuşmadı...");

		}
	}

	private void isExaminationFail(Errors errors, Student student) {
		boolean examinationResult = this.examinationService.examinationIsSuccess(student.getName(),
				student.getSurname(), student.getTckn(), student.getEmail());

		if (!examinationResult) {
			errors.rejectValue("student.email", "error.examinationFail", "Sınav Sonucu Başarısız...");
		}
	}

	private void isBlackList(Errors errors, Student student) {
		boolean blackListResult = this.blackListService.inBlackList(student.getTckn(), student.getEmail());

		if (!blackListResult) {
			errors.rejectValue("student.email", "error.blackList",
					"Kara Listede Bulunduğunuz İçin Başvurunuz Reddedildi...");
		}
	}

}
