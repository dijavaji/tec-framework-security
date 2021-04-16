package ec.com.technoloqie.framework.security.ejb.commons.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SFSTUSER")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name="USERID",nullable=false, unique=true)
    private Integer id;
    
    @Column(name="USERNAME",nullable=false)
    private String userName; //atributo nombre
         
    @Column(name="PASS",nullable=false)
    private String pass;
    
    @Column(name="ISENABLE")
    private Integer isEnable;
    
    @OneToMany(mappedBy="user")
    private List<UserProfile> userProfileCol;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	public List<UserProfile> getUserProfileCol() {
		return userProfileCol;
	}

	public void setUserProfileCol(List<UserProfile> userProfileCol) {
		this.userProfileCol = userProfileCol;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
