package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Colegio;
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
import repository.academico.ColegioRepository;

@RestController
@RequestMapping("/Colegio")
public class ColegioResoucer {

	@Autowired
	private ColegioRepository colegioRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping("/id")
	public  ResponseEntity<Colegio> obter (@PathVariable Colegio colegio){
		
		 Colegio colegioobter = this.colegioRepository.getOne(colegio.getIdColegio());
		
		 return colegioobter != null  ?  ResponseEntity.ok(colegioobter) : ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public List<Colegio> listar (){
		return this.colegioRepository.findAll();
	}
	
	public Colegio atualizar (Long id, Colegio Colegio) {
		
		Colegio ColegioSalvo = this.buscarColegioExistente(id);
		
		BeanUtils.copyProperties(Colegio, ColegioSalvo, "id");
	
		return this.colegioRepository.save(Colegio);
	}

	
	public ResponseEntity<Colegio> inserir(@Validated@RequestBody Colegio colegio, HttpServletResponse response){
		
		Colegio colegioSalvo = this.colegioRepository.save(colegio);
		
		this.publisher.publishEvent(new RecursooCriadoEvent(this, response, colegio.getIdColegio()));
	
		return ResponseEntity.status(HttpStatus.CREATED).body(colegio);
	}
	
	@GetMapping("id")
	public void deletar(@Validated@RequestBody Colegio colegio) {
		this.colegioRepository.delete(colegio);
	}
	
	private Colegio buscarColegioExistente(Long id) {

		Colegio colegioSalvo = this.colegioRepository.getOne(id);
		
		if(StringUtils.isEmpty(colegioSalvo)) {
			throw new IllegalArgumentException();
		}
		
		return colegioSalvo;
	}

}