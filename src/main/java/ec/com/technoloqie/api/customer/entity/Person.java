package ec.com.technoloqie.api.customer.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PERSON")
public class Person implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERSONID",nullable=false, unique=true)
	private Integer id;
	
	@NotEmpty(message ="no puede estar vacio")
	@NotNull(message = "no puede ser nulo")
	@Column(name="FIRSTNAME",nullable=false)
	private String firstName; //atributo nombre
	 
	@NotEmpty(message ="no puede estar vacio")
	@Column(name="LASTNAME")
	private String lastName; //atributo apellido
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="AGE")
	private Integer age;
	
	@Column(name="IDN")
	private String idn;// atributo identificacion
	
	@Column(name="ADDRESS")
	private String address;// atributo direccion
	
	@Column(name="PHONE",nullable=false)
	private String phone;// telefono
	
	@Email(message="no es una direccion de correo bien formada")
	@Column(name="EMAIL")
	private String email;// email
	
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
	
	//@OneToOne(mappedBy = "person")
    //private Customer customer;
	
	@PrePersist 
	public void prePersist() {
		createdDate = new Date();
		status = Boolean.TRUE;
	}
	
	private static final long serialVersionUID = 8505762287691021184L;
}