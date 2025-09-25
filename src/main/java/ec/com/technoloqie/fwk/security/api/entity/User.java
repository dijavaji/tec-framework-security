package ec.com.technoloqie.fwk.security.api.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="SFSTUSER")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERID",nullable=false, unique=true)
	private Integer id;

	@Column(name = "USERNAME", nullable = false, unique=true)
	private String username; 

	@Column(name = "EMAIL", nullable = false)
	private String email;// email

	@Column(name = "PASS", nullable = false)
	private String pass;// pass

	@NotEmpty(message ="no puede estar vacio")
	@Column(name="CREATEDBY",nullable=false)
	private String createdBy;
	
	@Column(name="CREATEDDATE",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name="MODIFIEDBY")
	private String modifiedBy;
	
	@Column(name="MODIFIEDDATE")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	@Column(name="STATUS")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "USERTYPEID", nullable = false)
	private UserType userType;
	
	//@OneToOne(cascade = CascadeType.ALL)
	@OneToOne(mappedBy = "user")
    //@JoinColumn(name = "PERSONID", nullable=false)
	private Person person;
	
	@PrePersist 
	public void prePersist() {
		createdDate = new Date();
		status = Boolean.TRUE;
	}
	
}
