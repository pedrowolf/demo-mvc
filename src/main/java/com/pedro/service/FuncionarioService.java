package com.pedro.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedro.domain.Funcionario;
import com.pedro.repository.FuncionarioRepository;

@Transactional
@Service
public class FuncionarioService implements MyServiceInterface<Funcionario, Long> {

	@Autowired
	private FuncionarioRepository repo;

	@Override
	public void salvar(Funcionario e) {
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
	public Funcionario loadById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Funcionario> loadAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return repo.count();
	}

	public List<Funcionario> loadByName(String nome) {
		return repo.findAllByNome(nome);
	}

	public List<Funcionario> loadByCargoId(Long id) {
		// TODO Auto-generated method stub
		return repo.findAllByCargoId(id);
	}

	public List<Funcionario> loadByDatas(LocalDate entrada, LocalDate saida) {
		if(entrada != null && saida != null) {
			return repo.findAllByDataEntradaDataSaida(entrada, saida);
		}else if(entrada != null) {
			return repo.findAllByDataEntrada(entrada);
		}else if(saida != null) {
			return repo.findAllByDataSaida(saida);
		}else {
			return new ArrayList<Funcionario>();
		}
	}

}
