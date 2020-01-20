package com.pedro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pedro.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	@Query("select f from Funcionario f where f.nome like ?1")
	List<Funcionario> findAllByNome(String nome);
	
	@Query("select f from Funcionario f where f.cargo.id = ?1")
	List<Funcionario> findAllByCargoId(Long id);
	
	@Query("select f from Funcionario f where f.dataEntrada >= ?1 and f.dataSaida <= ?2 order by f.dataEntrada asc")
	List<Funcionario> findAllByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);
	
	@Query("select f from Funcionario f where f.dataEntrada = ?1 order by f.dataEntrada asc")
	List<Funcionario> findAllByDataEntrada(LocalDate entrada);
	
	@Query("select f from Funcionario f where f.dataSaida = ?1 order by f.dataSaida asc")
	List<Funcionario> findAllByDataSaida(LocalDate saida);
}
