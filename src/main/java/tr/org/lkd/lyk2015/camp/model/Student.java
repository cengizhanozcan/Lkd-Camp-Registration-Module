package tr.org.lkd.lyk2015.camp.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/*
*cengizhan - Aug 16, 2015
*/

@Entity
public class Student extends AbstractUser {

	public enum Sex {
		MALE, FEMALE
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@OneToMany(mappedBy = "owner")
	private Set<Application> applicationForms;

	public Sex getSex() {
		return this.sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Set<Application> getApplicationForms() {
		return this.applicationForms;
	}

	public void setApplicationForms(Set<Application> applicationForms) {
		this.applicationForms = applicationForms;
	}

}
