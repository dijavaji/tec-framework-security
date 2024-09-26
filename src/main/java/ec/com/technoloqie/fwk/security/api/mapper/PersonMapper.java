package ec.com.technoloqie.fwk.security.api.mapper;

import ec.com.technoloqie.fwk.security.api.dto.PersonDto;
import ec.com.technoloqie.fwk.security.api.entity.Person;

public class PersonMapper {
	
	public static Person mapToPerson(PersonDto personDto) {
		Person person = new Person();
		person.setId(personDto.getId());
		person.setFirstName(personDto.getFirstName()); 
		person.setLastName(personDto.getLastName()); 
		person.setGender(personDto.getGender()); 
		person.setBirthDate(personDto.getBirthDate()); 
		person.setIdn(personDto.getIdn()); 
		person.setAddress(personDto.getAddress());
		person.setPhone(personDto.getPhone());
		person.setCreatedBy(personDto.getCreatedBy());
		person.setCreatedDate(personDto.getCreatedDate()); 
		person.setModifiedBy(personDto.getModifiedBy()); 
		person.setModifiedDate(personDto.getModifiedDate()); 
		person.setStatus(personDto.getStatus());
		return person;
	}
	
	public static PersonDto mapToPersonDto(Person person) {
		return new PersonDto(
				person.getId(),
				person.getFirstName(), 
				person.getLastName(), 
				person.getGender(),
				person.getBirthDate(),
				person.getIdn(), 
				person.getAddress(),
				person.getPhone(),
				person.getCreatedBy(),
				person.getCreatedDate(),
				person.getModifiedBy(),
				person.getModifiedDate(),
				person.getStatus()
				);
	}
}
