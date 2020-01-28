package com.pedro.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pedro.domain.Departamento;
import com.pedro.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.loadAll());
		return "departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "departamento/cadastro";
		}
		service.salvar(departamento);
		attr.addFlashAttribute("success", "Departamento Inserido!");
		return "redirect:/departamentos/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", service.loadById(id));
		return "departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "departamento/cadastro";
		}
		service.salvar(departamento);
		attr.addFlashAttribute("success", "Departamento Editado!");
		return "redirect:/departamentos/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		if (!service.hasCargos(id)) {
			service.remover(id);
			attr.addFlashAttribute("success", "Departamento Removido!");
		} else {
			attr.addFlashAttribute("fail", "Departamento não Removido, possui cargos vinculados!");
		}
		return "redirect:/departamentos/listar";
	}
}
