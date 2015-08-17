package tr.org.lkd.lyk2015.camp.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;


/*
*cengizhan - Aug 16, 2015
*/

@Entity
public class Instructor extends AbstractUser{

	@ManyToMany
	private Set<Course> courses = new HashSet<>();

	
	public Set<Course> getCourses() {
		return courses;
	}


	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	 
	
}
