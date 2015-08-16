package tr.org.lkd.lyk2015.camp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // Bunun icinde ki kolonlar sub classlar icinde gecerli olacak.
public abstract class AbstractUser extends AbstractBaseModel{
	
	private String name;
	private String surname;
	private Long tckn;
	private String password;
	private Integer birthDate;
	private String email;
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getTckn() {
		return tckn;
	}

	public void setTckn(Long tckn) {
		this.tckn = tckn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Integer birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
