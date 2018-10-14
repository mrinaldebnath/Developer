// default package
// Generated Oct 7, 2018 2:16:31 PM by Hibernate Tools 5.3.0.Beta2
package Models;
import java.util.HashSet;
import java.util.Set;

/**
 * Developers generated by hbm2java
 */
public class Developers implements java.io.Serializable {

	private String email;
	private Set programminglanguageses = new HashSet(0);
	private Set languageses = new HashSet(0);

	public Developers() {
	}

	public Developers(String email) {
		this.email = email;
	}

	public Developers(String email, Set programminglanguageses, Set languageses) {
		this.email = email;
		this.programminglanguageses = programminglanguageses;
		this.languageses = languageses;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set getProgramminglanguageses() {
		return this.programminglanguageses;
	}

	public void setProgramminglanguageses(Set programminglanguageses) {
		this.programminglanguageses = programminglanguageses;
	}

	public Set getLanguageses() {
		return this.languageses;
	}

	public void setLanguageses(Set languageses) {
		this.languageses = languageses;
	}

}