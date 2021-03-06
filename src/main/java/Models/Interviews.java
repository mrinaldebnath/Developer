// default package
// Generated Oct 7, 2018 2:16:31 PM by Hibernate Tools 5.3.0.Beta2
package Models;
/**
 * Interviews generated by hbm2java
 */
public class Interviews implements java.io.Serializable {

	private Integer id;
	private Integer score;
	private String comment;

	public Interviews() {
	}
	
	public Interviews(Integer id, Integer score, String comment) {
		this.id= id;
		this.score = score;
		this.comment = comment;
	}
	
	public Interviews(Integer score, String comment) {
		this.score = score;
		this.comment = comment;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
