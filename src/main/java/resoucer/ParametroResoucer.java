package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Parametro;
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
import repository.parametro.ParametroRepository;

@RestController
@RequestMapping("/ParametroResoucer")
public class ParametroResoucer {

	@Autowired
	private ParametroRepository parametroRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public Parametro obter(@PathVariable Parametro parametro){
	
		Parametro paradaObter  = this.parametroRepository.getOne(parametro.getId());
		
		return paradaObter;	
	}
	
	@GetMapping 
	public List<Parametro>pesquisar(){
		return this.parametroRepository.findAll();
	}
	
	
	public Parametro atualizar(Long id, Parametro parametro) {
		
		Parametro parametroSalvo = this.buscarParametroExistente(id);

		 BeanUtils.copyProperties(parametro, parametroSalvo, "id");
		 
		return this.parametroRepository.save(parametroSalvo);
		 
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Parametro>inserir(@Validated@RequestBody Parametro parametro, HttpServletResponse response){
	
		Parametro parametroSalvar = this.parametroRepository.save(parametro);
		
		publisher.publishEvent(new RecursooCriadoEvent(this,response ,parametroSalvar.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(parametroSalvar);
	}
	
	@GetMapping("/id")
	public void deletar(@Validated@RequestBody Parametro parametro){
		
		 this.parametroRepository.delete(parametro);
		 
	}
	
	private Parametro buscarParametroExistente(Long id) {
		Parametro parametroSalvo = this.parametroRepository.getOne(id);
		if(StringUtils.isEmpty(parametroSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return parametroSalvo;
		
	}
	
}
