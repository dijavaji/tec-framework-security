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
@Table(name="SFSTPROFILE")
public class Profile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name="PROFILEID",nullable=false, unique=true)
    private Integer id;
    
    @Column(name="NAME",nullable=false)
    private String name; //atributo nombre
    
    @Column(name="DESCRIPTION")
    private String description; //atributo nombre
    
    @OneToMany(mappedBy="profile")
    private List<UserProfile> profileUserCol;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserProfile> getProfileUserCol() {
		return profileUserCol;
	}

	public void setProfileUserCol(List<UserProfile> profileUserCol) {
		this.profileUserCol = profileUserCol;
	}
}
