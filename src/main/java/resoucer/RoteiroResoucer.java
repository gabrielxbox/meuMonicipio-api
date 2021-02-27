package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Roteiro;
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
import repository.roteiro.RoteiroRepository;

@RestController
@RequestMapping("/RoteiroResoucer")
public class RoteiroResoucer {

	@Autowired
	private RoteiroRepository roteiroRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public Roteiro obter(@PathVariable Roteiro roteiro){
	
		Roteiro roteiroObter  = this.roteiroRepository.getOne(roteiro.getId());
		
		return roteiroObter;	
	}
	
	@GetMapping 
	public List<Roteiro>pesquisar(){
		return this.roteiroRepository.findAll();
	}
	
	
	public Roteiro atualizar(Long id, Roteiro roteiro) {
		
		Roteiro roteiroSalvo = this.buscarRoteiroExistente(id);

		 BeanUtils.copyProperties(roteiro, roteiroSalvo, "id");
		 
		return this.roteiroRepository.save(roteiroSalvo);
		 
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Roteiro>inserir(@Validated@RequestBody Roteiro roteiro, HttpServletResponse response){
	
		Roteiro roteiroSalvar = this.roteiroRepository.save(roteiro);
		
		publisher.publishEvent(new RecursooCriadoEvent(this,response , roteiroSalvar.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(roteiroSalvar);
	}
	
	@GetMapping("/id")
	public void deletar(@Validated@RequestBody Roteiro roteiro){
		
		 this.roteiroRepository.delete(roteiro);
		 
	}
	
	private Roteiro buscarRoteiroExistente(Long id) {
		Roteiro roteiroSalvo = this.roteiroRepository.getOne(id);
		if(StringUtils.isEmpty(roteiroSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return roteiroSalvo;
		
	}
	
}
