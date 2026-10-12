package br.edu.ifrs.petbemestar.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.petbemestar.DAO.ClienteDAO;
import br.edu.ifrs.petbemestar.dominio.Cliente;

@RestController
public class ClienteController {
	
	private final ClienteDAO dao;
	
	public ClienteController(ClienteDAO dao) {
        this.dao = dao;
    }
	
	@GetMapping("/cliente")
	public List<Cliente> listar() {
	    return dao.listarTodos();
	}
	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastrar(@RequestBody Cliente cliente) {
	    dao.salvar(cliente);
	    return cliente;
	}
	
	@GetMapping("/cliente/{id}")
	public Cliente buscarPorId(@PathVariable Long id) {
	    return dao.buscarPorId(id);
	}
	
	@PutMapping("/cliente/{id}")
	public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente dados) {
		Cliente cliente = dao.buscarPorId(id);
		cliente.setNome(dados.getNome());
		cliente.setTelefone(dados.getTelefone());
		cliente.setEmail(dados.getEmail());
	    dao.atualizar(cliente);
	    return cliente;
	}
	
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
	    dao.remover(id);
	}

}
