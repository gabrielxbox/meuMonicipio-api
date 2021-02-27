package resoucer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Usuario;
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
import repository.usuario.UsuarioRepository;

@RestController
@RequestMapping("/UsuarioResoucer")
public class UsuarioResoucer {
	

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/id")
	public Usuario obter(@PathVariable Usuario usuario){
	
		Usuario usuarioObter  = this.usuarioRepository.getOne(usuario.getId());
		
		return usuario;	
	}
	
	@GetMapping 
	public List<Usuario>pesquisar(){
		return this.usuarioRepository.findAll();
	}
	
	
	public Usuario atualizar(Long id, Usuario usuario) {
		
		Usuario usuarioSalvo = this.buscarUsuarioExistente(id);

		 BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		 
		return this.usuarioRepository.save(usuarioSalvo);
		 
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Usuario>inserir(@Validated@RequestBody Usuario usuario, HttpServletResponse response){
	
		Usuario usuarioSalvar = this.usuarioRepository.save(usuario);
		
		publisher.publishEvent(new RecursooCriadoEvent(this,response ,usuarioSalvar.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvar);
	}
	
	@GetMapping("/id")
	public void deletar(@Validated@RequestBody Usuario usuario){
		
		 this.usuarioRepository.delete(usuario);
		 
	}
	
	private Usuario buscarUsuarioExistente(Long id) {
		Usuario usuarioSalvo = this.usuarioRepository.getOne(id);
		if(StringUtils.isEmpty(usuarioSalvo)) {
			throw new  IllegalArgumentException();
		}
		
		return usuarioSalvo;
		
	}

}
