package com.pedro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedro.domain.Departamento;
import com.pedro.repository.DepartamentoRepository;

@Transactional
@Service
public class DepartamentoService implements MyServiceInterface<Departamento, Long> {

	@Autowired
	private DepartamentoRepository repo;

	@Override
	public void salvar(Departamento e) {
		// TODO Auto-generated method stub
		if(e.getId()!=null) {
			e.setVersion(loadById(e.getId()).getVersion());
		}
		repo.save(e);
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Departamento loadById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Departamento> loadAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return repo.count();
	}

	public boolean hasCargos(Long id) {
		if(loadById(id).getCargos().isEmpty()) {
			return false;
		}
		return true;
	}

}
