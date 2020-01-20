package com.pedro.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pedro.domain.Cargo;
import com.pedro.domain.Funcionario;
import com.pedro.domain.UF;
import com.pedro.service.CargoService;
import com.pedro.service.FuncionarioService;
import com.pedro.web.validator.FuncionarioValidator;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private CargoService cargoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new FuncionarioValidator());
	}

	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "funcionario/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.loadAll());
		model.addAttribute("cargos", cargoService.loadAll());
		return "funcionario/lista";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.loadById(id));
		return "funcionario/cadastro";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result,RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "funcionario/cadastro";
		}
		if (funcionario.getId() != null) {
			// editar
			Funcionario tmp = funcionarioService.loadById(funcionario.getId());
			tmp.reload(funcionario);
			funcionarioService.salvar(tmp);
			attr.addFlashAttribute("success", "Funcionário editado com sucesso!");
		} else {
			// salvar
			funcionarioService.salvar(funcionario);
			attr.addFlashAttribute("success", "Funcionário inserido com sucesso!");
		}
		return "redirect:/funcionarios/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		funcionarioService.remover(id);
		attr.addFlashAttribute("success", "Funcionário removido com sucesso!");
		return "redirect:/funcionarios/listar";
	}

	@GetMapping("/buscar/nome")
	public String getByNome(@RequestParam String nome, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.loadByName(nome));
		model.addAttribute("cargos", cargoService.loadAll());
		return "funcionario/lista";
	}

	@GetMapping("/buscar/cargo")
	public String getByCargo(@RequestParam Long id, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.loadByCargoId(id));
		model.addAttribute("cargos", cargoService.loadAll());
		return "funcionario/lista";
	}

	@GetMapping("/buscar/data")
	public String getByDates(@RequestParam("entrada") @DateTimeFormat(iso = ISO.DATE) LocalDate entrada,
			@RequestParam("saida") @DateTimeFormat(iso = ISO.DATE) LocalDate saida, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.loadByDatas(entrada, saida));
		model.addAttribute("cargos", cargoService.loadAll());
		return "funcionario/lista";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> getCargos(){
		return cargoService.loadAll();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUfs(){
		return UF.values();
	}
}
