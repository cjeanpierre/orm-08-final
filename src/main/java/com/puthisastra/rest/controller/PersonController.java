package com.puthisastra.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puthisastra.rest.domain.Person;
import com.puthisastra.rest.repository.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping
	public List<Person> getAll() {
		return personRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Person getById(@PathVariable(value = "id") Long personId) {
		Person person = personRepository.getOne(personId);
		return person;
	}
	
	@PostMapping
	public Person create(@RequestBody Person person) {
		return personRepository.save(person);
	}
	
	@PutMapping("/{id}")
	public Person update(@PathVariable(value = "id") Long personId, @RequestBody Person person) {
		Person personInDb = personRepository.getOne(personId);
		personInDb.setFirstname(person.getFirstname());
		personInDb.setLastname(person.getLastname());
		personInDb.setAge(person.getAge());
		return personRepository.save(personInDb);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id") Long personId) {
		personRepository.deleteById(personId);
	}
	
}
