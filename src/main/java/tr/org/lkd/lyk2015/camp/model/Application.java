package tr.org.lkd.lyk2015.camp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/*
*cengizhan - Aug 16, 2015
*/

/*
 * This entity represents an application form which is submitted by a student
 * 
 * Same Studdent may summit different forms in different year
 */

@Entity
public class Application  extends AbstractBaseModel{

	public enum WorkStatus {
		WORKING, STUDENT, NOT_WORKING
	}

	@Max(2005)
	@Min(1940)
	private Integer year;

	@Enumerated(EnumType.STRING)
	private WorkStatus workStatus;

	private Boolean officer = false;

	private String corporation;

	private String workDetails;

	private Integer englishLevel = new Integer(0);

	private String githubLink;

	@OneToMany
	private Set<Course> preferredCourses = new HashSet<>();

	@ManyToOne
	private Student owner;

	private Boolean needAccomodation;

	public WorkStatus getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(WorkStatus workStatus) {
		this.workStatus = workStatus;
	}

	public boolean isOfficer() {
		return officer;
	}

	public void setOfficer(boolean officer) {
		this.officer = officer;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getWorkDetails() {
		return workDetails;
	}

	public void setWorkDetails(String workDetails) {
		this.workDetails = workDetails;
	}

	public int getEnglishLevel() {
		return englishLevel;
	}

	public void setEnglishLevel(int englishLevel) {
		this.englishLevel = englishLevel;
	}

	public String getGithubLink() {
		return githubLink;
	}

	public void setGithubLink(String githubLink) {
		this.githubLink = githubLink;
	}

	public Set<Course> getPreferredCourses() {
		return preferredCourses;
	}

	public void setPreferredCourses(Set<Course> preferredCourses) {
		this.preferredCourses = preferredCourses;
	}

	public void setNeedAccomodation(Boolean needAccomodation) {
		this.needAccomodation = needAccomodation;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Boolean getOfficer() {
		return officer;
	}

	public void setOfficer(Boolean officer) {
		this.officer = officer;
	}

	public Student getOwner() {
		return owner;
	}

	public void setOwner(Student owner) {
		this.owner = owner;
	}

	public void setEnglishLevel(Integer englishLevel) {
		this.englishLevel = englishLevel;
	}

	public Boolean getNeedAccomodation() {
		return needAccomodation;
	}

}
