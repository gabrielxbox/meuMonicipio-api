package event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursooCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4127749096122431184L;

	private HttpServletResponse response;
	
	private Long id;
	
	/**
	 *  para controlar um evento ele recebe um objeto qur pode ser um this
	 *  tambem um HttpServletResponse e um id 
	 */
	public RecursooCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);

		this.response = response;
		this.id = codigo;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
