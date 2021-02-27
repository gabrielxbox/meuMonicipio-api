package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Parada;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import repository.parada.ParadaRepository;

@RestController
@RequestMapping("/ParadaResoucer")
public class ParadaResoucer {
	

	@Autowired
	private ParadaRepository paradaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public Parada obter(@PathVariable Parada parada){
	
		Parada paradaObter = this.paradaRepository.getOne(parada.getId());
		
		return paradaObter;	
	}
	
	@GetMapping 
	public List<Parada>pesquisar(){
		return this.paradaRepository.findAll();
	}
	
	
	public Parada atualizar(Long id, Parada parada) {
		
		Parada paradaSalvo = this.buscarParadaExistente(id);

		 BeanUtils.copyProperties(parada, paradaSalvo, "id");
		 
		return this.paradaRepository.save(paradaSalvo);
		 
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Parada>inserir(@Validated@RequestBody Parada parada, HttpServletResponse response){
	
		Parada paradaSalvar = this.paradaRepository.save(parada);
		
		publisher.publishEvent(new RecursooCriadoEvent(this,response ,paradaSalvar.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(paradaSalvar);
	}
	
	@GetMapping("/id")
	public void deletar(@Validated@RequestBody Parada parada){
		
		 this.paradaRepository.delete(parada);
		 
	}
	
	private Parada buscarParadaExistente(Long id) {
		Parada paradaSalvo = this.paradaRepository.getOne(id);
		if(StringUtils.isEmpty(paradaSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return paradaSalvo;
		
	}

}
