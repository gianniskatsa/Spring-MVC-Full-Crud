package org.pcedu.services;

import java.util.List;

import org.pcedu.entities.Trainer;
import org.pcedu.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrainerService {

	@Autowired
	private TrainerRepository repo;
	
	public List<Trainer> listAll() {
		return repo.findAll();
	}
	
	public void save(Trainer trainer) {
		repo.save(trainer);
	}
	
	public Trainer get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
