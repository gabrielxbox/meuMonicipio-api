package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Veiculo;
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
import repository.veiculo.VeiculoRepository;

@RestController
@RequestMapping("/VeiculoResoucer")
public class VeiculoResoucer {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public Veiculo obter(@PathVariable Veiculo veiculo){
	
		Veiculo veiculoObter  = this.veiculoRepository.getOne(veiculo.getRoteiro());
		
		return veiculoObter;	
	}
	
	@GetMapping 
	public List<Veiculo>pesquisar(){
		return this.veiculoRepository.findAll();
	}
	
	
	public Veiculo atualizar(Long id, Veiculo veiculo) {
		
		Veiculo veiculoSalvo = this.buscarVeiculoExistente(id);

		 BeanUtils.copyProperties(veiculo, veiculoSalvo, "id");
		 
		return this.veiculoRepository.save(veiculo);
		 
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Veiculo>inserir(@Validated@RequestBody Veiculo veiculo, HttpServletResponse response){
	
		Veiculo veiculoSalvar = this.veiculoRepository.save(veiculo);
		
		publisher.publishEvent(new RecursooCriadoEvent(this,response ,veiculoSalvar.getRoteiro().longValue()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvar);
	}
	
	@GetMapping("/id")
	public void deletar(@Validated@RequestBody Veiculo veiculo){
		
		 this.veiculoRepository.delete(veiculo);
		 
	}
	
	private Veiculo buscarVeiculoExistente(Long id) {
		Veiculo veiculoSalvo = this.veiculoRepository.getOne(id);
		if(StringUtils.isEmpty(veiculoSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return veiculoSalvo;
		
	}
}
