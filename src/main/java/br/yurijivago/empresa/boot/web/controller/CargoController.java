package br.yurijivago.empresa.boot.web.controller;

import br.yurijivago.empresa.boot.dao.DepartamentoDao;
import br.yurijivago.empresa.boot.domain.Cargo;
import br.yurijivago.empresa.boot.domain.Departamento;
import br.yurijivago.empresa.boot.domain.ProfilePropertyEditor;
import br.yurijivago.empresa.boot.service.CargoService;
import br.yurijivago.empresa.boot.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
@RequestMapping(path = "/cargos")
public class CargoController {

    @Autowired
    private CargoService service;

    @Autowired
    private DepartamentoService dptosService;

    @Autowired
    private DepartamentoDao dptoDao;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastrar")
    public String cadastrar(Cargo cargo, ModelMap model){
        model.addAttribute("departamentos", dptosService.buscarTodos());
        return "/cargo/cadastro.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listar")
    public String listar(ModelMap model){
        model.addAttribute("cargos", service.buscarTodos());
        return "cargo/lista.html";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/salvar")
    public String salvar(Cargo cargo, Departamento departamento){
        //cargo.setDepartamento(departamento);
        service.salvar(cargo);
        return "redirect:/cargos/cadastrar";
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder){
        binder.registerCustomEditor(Departamento.class, new ProfilePropertyEditor(dptoDao));
    }
}
