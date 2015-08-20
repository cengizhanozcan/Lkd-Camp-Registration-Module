package tr.org.lkd.lyk2015.camp.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*
*cengizhan - Aug 16, 2015
*/

@Entity
public class Admin extends AbstractUser {

	@NotEmpty
	@Column(unique = true)
	private String lkdNo;

	public String getLkdNo() {
		return this.lkdNo;
	}

	public void setLkdNo(String lkdNo) {
		this.lkdNo = lkdNo;
	}

}
