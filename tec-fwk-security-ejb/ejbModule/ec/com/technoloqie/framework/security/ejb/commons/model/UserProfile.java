package ec.com.technoloqie.framework.security.ejb.commons.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SFSTUSERPROFILE")
public class UserProfile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name="USRPROID",nullable=false, unique=true)
    private Integer id;
	
    @ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="USERID")
    private User user;
    
    @ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="PROFILEID")
    private Profile profile;
    
    @Column(name="DATE")
    private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
