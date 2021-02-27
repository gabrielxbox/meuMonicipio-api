package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Administrador;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import event.RecursooCriadoEvent;
import repository.administrador.AdministradorRepository;

@RestController
@RequestMapping("/administrador")
public class AdministradorResoucer {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public ResponseEntity<Administrador> obter(@PathVariable Administrador administrador){
	
		Administrador administrado = this.administradorRepository.getOne(administrador.getId());
		
		return administrado != null  ? ResponseEntity.ok(administrado) : ResponseEntity.noContent().build();	
	}
	@GetMapping 
	public List<Administrador>pesquisar(){
		return this.administradorRepository.findAll();
	}
	
	
	public Administrador atualizar(Long id, Administrador administrador) {
		
		Administrador administradorSalvo = this.buscarAdministradorExistente(id);

		 BeanUtils.copyProperties(administrador, administradorSalvo, "id");
		 
		return this.administradorRepository.save(administrador);
		 
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Administrador>inserir(@Validated@RequestBody Administrador administrador, HttpServletResponse response){
	
		Administrador administradorSalvar = this.administradorRepository.save(administrador);
		
		publisher.publishEvent(new RecursooCriadoEvent(this,response ,administrador.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(administrador);
	}
	
	@GetMapping("/id")
	public void deletar(@Validated@RequestBody Administrador administrador){
		
		 this.administradorRepository.delete(administrador);
		 
	}
	
	@GetMapping
	public Page<Administrador>pesquisar(Administrador administrador){
		return this.administradorRepository.filtrar(administrador);
	}
	

	private Administrador buscarAdministradorExistente(Long id) {
		Administrador administradorSalvo = this.administradorRepository.getOne(id);
		if(StringUtils.isEmpty(administradorSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return administradorSalvo;
		
	}


	
}
