// default package
// Generated Oct 7, 2018 2:16:31 PM by Hibernate Tools 5.3.0.Beta2
package Models;
/**
 * Programminglanguages generated by hbm2java
 */
public class Programminglanguages implements java.io.Serializable {

	private Integer id;
	private Developers developers;
	private String name;

	public Programminglanguages() {
	}

	public Programminglanguages(Developers developers, String name) {
		this.developers = developers;
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Developers getDevelopers() {
		return this.developers;
	}

	public void setDevelopers(Developers developers) {
		this.developers = developers;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
