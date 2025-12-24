package ec.com.technoloqie.fwk.security.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateDto {
	
	private String name;
	private String email;
	private String currentPassword;
	private String newPassword;
	private String createdBy;
	private PersonDto person;
	private String modifiedBy;
	private Date modifiedDate;
	private Boolean status;

}
