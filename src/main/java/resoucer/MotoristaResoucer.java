package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Motorista;
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
import repository.motorista.MotoristaRepository;

@RestController
@RequestMapping("/MotoristaResoucer")
public class MotoristaResoucer {

	
	@Autowired
	private MotoristaRepository motoristaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public Motorista obter(@PathVariable Motorista motorista){
	
		Motorista motoristaObter = this.motoristaRepository.getOne(motorista.getId());
		
		return motoristaObter;	
	}
	
	@GetMapping 
	public List<Motorista>pesquisar(){
		return this.motoristaRepository.findAll();
	}
	
	
	public Motorista atualizar(Long id, Motorista motorista) {
		
		Motorista motoristaSalvo = this.buscarMotoristaExistente(id);

		 BeanUtils.copyProperties(motorista, motoristaSalvo, "id");
		 
		return this.motoristaRepository.save(motorista);
		 
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Motorista>inserir(@Validated@RequestBody Motorista motorista, HttpServletResponse response){
	
		Motorista motoristaSalvar = this.motoristaRepository.save(motorista);
		
		publisher.publishEvent(new RecursooCriadoEvent(this,response ,motoristaSalvar.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(motoristaSalvar);
	}
	
	@GetMapping("/id")
	public void deletar(@Validated@RequestBody Motorista motorista){
		
		 this.motoristaRepository.delete(motorista);
		 
	}
	
	private Motorista buscarMotoristaExistente(Long id) {
		Motorista motoristaSalvo = this.motoristaRepository.getOne(id);
		if(StringUtils.isEmpty(motoristaSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return motoristaSalvo;
		
	}
	
}
