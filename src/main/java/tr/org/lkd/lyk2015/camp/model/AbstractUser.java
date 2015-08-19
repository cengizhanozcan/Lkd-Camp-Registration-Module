package tr.org.lkd.lyk2015.camp.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/*
*cengizhan - Aug 16, 2015
*/

@MappedSuperclass // Bunun icinde ki kolonlar sub classlar icinde gecerli
					// olacak.
public abstract class AbstractUser extends AbstractBaseModel {

	private String name;
	private String surname;

	@Column(unique = true)
	private Long tckn;
	private String password;
	private Integer birthDate;
	private String email;
	private String phone;

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getTckn() {
		return this.tckn;
	}

	public void setTckn(Long tckn) {
		this.tckn = tckn;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Integer birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
