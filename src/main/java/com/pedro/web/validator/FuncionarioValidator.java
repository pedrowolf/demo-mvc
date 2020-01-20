package com.pedro.web.validator;
import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pedro.domain.Funcionario;

public class FuncionarioValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Funcionario.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Funcionario f = (Funcionario)object;
		LocalDate entrada = f.getDataEntrada();
		LocalDate saida = f.getDataSaida();
		if(saida != null) {
			if(saida.isBefore(entrada)) {
				errors.rejectValue("dataSaida", null, "A data de saída não deve ser menor que a de entrada");
			}
		}
	}

}
