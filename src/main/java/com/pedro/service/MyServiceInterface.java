package com.pedro.service;

import java.io.Serializable;
import java.util.List;

public interface MyServiceInterface<Entidade, ID extends Serializable> {

	void salvar(Entidade e);
		
	void remover(ID id);
	
	Entidade loadById(ID id);
	
	List<Entidade> loadAll();
	
	Long count();
}
