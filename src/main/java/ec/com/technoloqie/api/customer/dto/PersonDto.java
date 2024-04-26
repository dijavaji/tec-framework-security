package ec.com.technoloqie.api.customer.dto;

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
	private String idn;// atributo identificacion
	private String address;// atributo direccion
	private String phone;// telefono
	//private String email;// email
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Boolean status;
	
}
