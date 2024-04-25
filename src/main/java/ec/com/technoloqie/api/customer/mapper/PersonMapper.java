package ec.com.technoloqie.api.customer.mapper;

import ec.com.technoloqie.api.customer.dto.PersonDto;
import ec.com.technoloqie.api.customer.entity.Person;

public class PersonMapper {
	
	public static Person mapToPerson(PersonDto personDto) {
		return new Person(
				personDto.getId(),
				personDto.getFirstName(), 
				personDto.getLastName(), 
				personDto.getGender(),
				personDto.getAge(),
				personDto.getIdn(),
				personDto.getAddress(),
				personDto.getPhone(),
				personDto.getEmail(),
				personDto.getCreatedBy(),
				personDto.getCreatedDate(),
				personDto.getModifiedBy(),
				personDto.getModifiedDate(),
				personDto.getStatus()
				);
	}
	
	public static PersonDto mapToPersonDto(Person person) {
		return new PersonDto(
				person.getId(),
				person.getFirstName(), 
				person.getLastName(), 
				person.getGender(),
				person.getAge(),
				person.getIdn(), 
				person.getAddress(),
				person.getPhone(),
				person.getEmail(),
				person.getCreatedBy(),
				person.getCreatedDate(),
				person.getModifiedBy(),
				person.getModifiedDate(),
				person.getStatus()
				);
	}
}
