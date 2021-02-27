package resoucer;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import model.TelefoneUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import event.RecursooCriadoEvent;
import repository.telefone.TelefoneUtilRepository;

@RestController
@RequestMapping("/telefoneUtil")     
public class TelefoneUtilResoucer {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private TelefoneUtilRepository telefoneUtilRepository; 
	
	@SuppressWarnings("unused")
	@GetMapping("/id")
	public ResponseEntity<TelefoneUtil> obter(@PathVariable TelefoneUtil telefoneUtil){

	TelefoneUtil telefone = this.telefoneUtilRepository.getOne(telefoneUtil.getId());
	
	return  ResponseEntity.noContent().build();
	}
	
	@GetMapping(name = "listar")
	public List<TelefoneUtil>listar(){
		return this.telefoneUtilRepository.findAll();
		
	}



	@GetMapping("/id")
	public TelefoneUtil atualizar(Long id, TelefoneUtil telefoneUtil) {
		
		TelefoneUtil telefoneSalvo  = this.buscarTelefoneUtilExistente(telefoneUtil.getId()); 
	
		BeanUtils.copyProperties(telefoneUtil, telefoneSalvo, "id");
		
		return this.telefoneUtilRepository.save(telefoneUtil);
	
	}

	@SuppressWarnings("unused")
	@GetMapping
	public ResponseEntity<TelefoneUtil>inserir(@Validated@RequestBody TelefoneUtil telefoneUtil, HttpServletResponse response){
		
		TelefoneUtil telefoneUtilSalvar = this.telefoneUtilRepository.save(telefoneUtil);
		
		publisher.publishEvent(new RecursooCriadoEvent(this, response, telefoneUtil.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(telefoneUtil);
	}
	
	@GetMapping
	public List<TelefoneUtil>pesquisar(TelefoneUtil telefoneUtil){
		return this.telefoneUtilRepository.pesquisar(telefoneUtil);
	}
	

	@GetMapping("/id")
	public void deletar(@Validated@RequestBody TelefoneUtil telefoneUtil) {
		this.telefoneUtilRepository.delete(telefoneUtil);
	}

	private TelefoneUtil buscarTelefoneUtilExistente(Long id) {

		TelefoneUtil telefoneUtilSalvo = this.telefoneUtilRepository.getOne(id);
	
		if(StringUtils.isEmpty(telefoneUtilSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return telefoneUtilSalvo;
	}
}
