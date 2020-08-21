package com.cliente.brasilbras.resources;

import java.util.ArrayList;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.brasilbras.models.Cliente;
import com.cliente.brasilbras.repository.CadastroRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="API REST Clientes")
@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteResource {
	@Autowired
	private CadastroRepository er;
	
	@ApiOperation(value="Retorna os dados dos clientes",
				  notes="Essa operação salva um novo registro com as informações de pessoa.")
	@GetMapping(produces="application/json")
	
	public @ResponseBody  ArrayList<Cliente> listaClientes(){
		Iterable<Cliente> listaClientes = er.findAll();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		for(Cliente cliente : listaClientes){
			long codigo = cliente.getCodigo();
			cliente.add(linkTo(methodOn(ClienteResource.class).cliente(codigo)).withSelfRel());
			clientes.add(cliente);
		}
		return clientes;
	}
	
	@ApiOperation(value="Retorna um cliente específico")
	@GetMapping(value="/{codigo}", produces="application/json")
	public @ResponseBody Cliente cliente(@PathVariable(value="codigo") long codigo){
		Cliente cliente = er.findByCodigo(codigo);
		cliente.add(linkTo(methodOn(ClienteResource.class).listaClientes()).withRel("Lista de Clientes"));
		return cliente;
	}
	
	@ApiOperation(value="Salva um cliente")
	@PostMapping()
	public Cliente cadastraCliente(@RequestBody @Valid Cliente cliente){
		er.save(cliente);
		long codigo = cliente.getCodigo();
		cliente.add(linkTo(methodOn(ClienteResource.class).cliente(codigo)).withSelfRel());
		return cliente;
	}
	
	@ApiOperation(value="Deleta um cliente")
	@DeleteMapping()
	public Cliente deletaCadastro(@RequestBody Cliente cliente){
		er.delete(cliente);
		return cliente;
	}
	
//	@RequestMapping("/users")//teste
//	@ResponseBody
//	public String getUsers() {
//	return "{\"users\":[{\"name\":\"Yuri\", \"country\":\"Brazil\"}," +
//	                   "{\"name\":\"Lucas\",\"country\":\"Brazil\"}]}";
//	    }

}
