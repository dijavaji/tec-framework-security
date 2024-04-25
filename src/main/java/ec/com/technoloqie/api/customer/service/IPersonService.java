package ec.com.technoloqie.api.customer.service;

import ec.com.technoloqie.api.customer.dto.PersonDto;

public interface IPersonService {
	
	PersonDto createPerson(PersonDto person);
	PersonDto getPersonId(Integer code);
	PersonDto updatePerson(PersonDto person, int id);
	void deletePerson(Integer code);
}
