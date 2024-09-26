package ec.com.technoloqie.fwk.security.api.service;

import ec.com.technoloqie.fwk.security.api.dto.PersonDto;

public interface IPersonService {
	
	PersonDto createPerson(PersonDto person);
	PersonDto getPersonId(Integer code);
	PersonDto updatePerson(PersonDto person, int id);
	void deletePerson(Integer code);
}
