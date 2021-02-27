package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Endereco;
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
import repository.endereco.EnderecoRepository;

@RestController
@RequestMapping("/EnderecoResoucer")
public class EnderecoResoucer {

	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public Endereco obter(@PathVariable Endereco endereco){
	
		Endereco enderecor = this.enderecoRepository.getOne(endereco.getId());
		
		return enderecor;	
	}
	@GetMapping 
	public List<Endereco>pesquisar(){
		return this.enderecoRepository.findAll();
	}
	
	
	public Endereco atualizar(Long id, Endereco endereco) {
		
		Endereco EnderecoSalvo = this.buscarEnderecoExistente(id);

		 BeanUtils.copyProperties(endereco, EnderecoSalvo, "id");
		 
		return this.enderecoRepository.save(endereco);
		 
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Endereco>inserir(@Validated@RequestBody Endereco endereco, HttpServletResponse response){
	
		Endereco enderecoSalvar = this.enderecoRepository.save(endereco);
		
		publisher.publishEvent(new RecursooCriadoEvent(this,response ,enderecoSalvar.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoSalvar);
	}
	
	@GetMapping("/id")
	public void deletar(@Validated@RequestBody Endereco endereco){
		
		 this.enderecoRepository.delete(endereco);
		 
	}
	
	private Endereco buscarEnderecoExistente(Long id) {
		Endereco enderecoSalvo = this.enderecoRepository.getOne(id);
		if(StringUtils.isEmpty(enderecoSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return enderecoSalvo;
		
	}
}
