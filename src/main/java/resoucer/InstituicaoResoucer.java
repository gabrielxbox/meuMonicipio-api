package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Instituicao;
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
import repository.instituicao.InstituicaoRepository;

@RestController
@RequestMapping("/InstituicaoResoucer")
public class InstituicaoResoucer  {
	

	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public Instituicao obter(@PathVariable Instituicao instituicao){
	
		Instituicao instituicaoObter = this.instituicaoRepository.getOne(instituicao.getId());
		
		return instituicaoObter;	
	}
	
	@GetMapping 
	public List<Instituicao>pesquisar(){
		return this.instituicaoRepository.findAll();
	}
	
	
	public Instituicao atualizar(Long id, Instituicao instituicao) {
		
		Instituicao instituicaoSalvo = this.buscarinstituicaoExistente(id);

		 BeanUtils.copyProperties(instituicao, instituicaoSalvo, "id");
		 
		return this.instituicaoRepository.save(instituicao);
		 
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Instituicao>inserir(@Validated@RequestBody Instituicao instituicao, HttpServletResponse response){
	
		Instituicao instituicaoSalvar = this.instituicaoRepository.save(instituicao);
		
		publisher.publishEvent(new RecursooCriadoEvent(this,response ,instituicaoSalvar.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(instituicaoSalvar);
	}
	
	@GetMapping("/id")
	public void deletar(@Validated@RequestBody Instituicao instituicao){
		
		 this.instituicaoRepository.delete(instituicao);
		 
	}
	
	private Instituicao buscarinstituicaoExistente(Long id) {
		Instituicao instituicaoSalvo = this.instituicaoRepository.getOne(id);
		if(StringUtils.isEmpty(instituicaoSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return instituicaoSalvo;
		
	}

}
