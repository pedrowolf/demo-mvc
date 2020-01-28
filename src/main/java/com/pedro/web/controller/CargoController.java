package com.pedro.web.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pedro.domain.Cargo;
import com.pedro.domain.Departamento;
import com.pedro.service.CargoService;
import com.pedro.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "cargo/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.loadAll());
		return "cargo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "cargo/cadastro";
		}
		
		if (cargo.getId() == null) {
			attr.addFlashAttribute("success", "Cargo inserido!");
			cargoService.salvar(cargo);
		} else {
			attr.addFlashAttribute("success", "Cargo editado!");
			cargoService.salvar(cargo);
		}
		return "redirect:/cargos/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.loadById(id));
		return "cargo/cadastro";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		if (!cargoService.hasFuncionarios(id)) {
			cargoService.remover(id);
			attr.addFlashAttribute("success", "Cargo Removido!");
		} else {
			attr.addFlashAttribute("fail", "Cargo não pode ser removido, existe funcionários cadastrados!");
		}
		return "redirect:/cargos/listar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> getDepartamentos(){
		return departamentoService.loadAll();
	}
}
