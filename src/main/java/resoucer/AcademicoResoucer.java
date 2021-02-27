package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Academico;
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
import repository.academico.AcademicoRepository;

@RestController
@RequestMapping("/Academico")
public class AcademicoResoucer {

	@Autowired
	private AcademicoRepository academicoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public ResponseEntity<Academico> obter(@PathVariable Academico academico){
		
		Academico academicoObter = this.academicoRepository.getOne(academico.getId());
		
		return academicoObter != null  ?  ResponseEntity.ok(academicoObter) : ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public List<Academico> listar (){
		return this.academicoRepository.findAll();
	}
	
	public Academico atualizar (Long id, Academico academico) {
		
		Academico academicoSalvo = this.buscarAcademicoExistente(id);
		
		BeanUtils.copyProperties(academico, academicoSalvo, "id");
	
		return this.academicoRepository.save(academico);
	}

	
	public ResponseEntity<Academico> inserir(@Validated@RequestBody Academico academico, HttpServletResponse response){
		
		Academico academicoSalvo = this.academicoRepository.save(academico);
		
		this.publisher.publishEvent(new RecursooCriadoEvent(this, response, academico.getId()));
	
		return ResponseEntity.status(HttpStatus.CREATED).body(academico);
	}
	
	@GetMapping("id")
	public void deletar(@Validated@RequestBody Academico academico) {
		this.academicoRepository.delete(academico);
	}
	
	private Academico buscarAcademicoExistente(Long id) {

		Academico academicoSalvo = this.academicoRepository.getOne(id);
		
		if(StringUtils.isEmpty(academicoSalvo)) {
			throw new IllegalArgumentException();
		}
		
		return academicoSalvo;
	}
	
}
