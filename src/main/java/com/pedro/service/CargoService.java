package com.pedro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedro.domain.Cargo;
import com.pedro.repository.CargoRepository;

@Service
@Transactional
public class CargoService implements MyServiceInterface<Cargo, Long> {

	@Autowired
	private CargoRepository repo;
	
	@Override
	public void salvar(Cargo e) {
		// TODO Auto-generated method stub
		if(e.getId()!=null) {
			e.setVersion(loadById(e.getId()).getVersion());
		}
		repo.saveAndFlush(e);
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Cargo loadById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Cargo> loadAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return repo.count();
	}

	public boolean hasFuncionarios(Long id) {
		if(loadById(id).getFuncionarios().isEmpty()) {
			return false;
		}
		return true;
	}

}
