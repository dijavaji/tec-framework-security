package ec.com.technoloqie.fwk.security.api.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
	
	private Integer id;
	private String firstName; //atributo nombre
	private String lastName; //atributo apellido
	private String gender;
	//private Integer age;
	private Date birthDate;
	private String idn;
	private String address;
	private String phone;
	//private String email;// email
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Boolean status;
	
	public enum Gender{
		M, F //, JOIN
	}
	
}
