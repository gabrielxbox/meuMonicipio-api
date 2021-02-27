package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Solicitacao;
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
import repository.solicitacao.SolicitacaoRepository;

@RestController
@RequestMapping("/SolicitacaoRsoucer")
public class SolicitacaoRsoucer {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public Solicitacao obter(@PathVariable Solicitacao solicitacao){
	
		Solicitacao solicitacaoObter  = this.solicitacaoRepository.getOne(solicitacao.getIdSolicitacao());
		
		return solicitacaoObter;	
	}
	
	@GetMapping 
	public List<Solicitacao>pesquisar(){
		return this.solicitacaoRepository.findAll();
	}
	
	
	public Solicitacao atualizar(Long id, Solicitacao solicitacao) {
		
		Solicitacao solicitacaoSalvo = this.buscarSolicitacaoExistente(id);

		 BeanUtils.copyProperties(solicitacao, solicitacaoSalvo, "id");
		 
		return this.solicitacaoRepository.save(solicitacao);
		 
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Solicitacao>inserir(@Validated@RequestBody Solicitacao solicitacao, HttpServletResponse response){
	
		Solicitacao solicitacaoSalvar = this.solicitacaoRepository.save(solicitacao);
		
		publisher.publishEvent(new RecursooCriadoEvent(this,response ,solicitacaoSalvar.getIdSolicitacao()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoSalvar);
	}
	
	@GetMapping("/id")
	public void deletar(@Validated@RequestBody Solicitacao solicitacao){
		
		 this.solicitacaoRepository.delete(solicitacao);
		 
	}
	
	private Solicitacao buscarSolicitacaoExistente(Long id) {
		Solicitacao solicitacaoSalvo = this.solicitacaoRepository.getOne(id);
		if(StringUtils.isEmpty(solicitacaoSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return solicitacaoSalvo;
		
	}
}
