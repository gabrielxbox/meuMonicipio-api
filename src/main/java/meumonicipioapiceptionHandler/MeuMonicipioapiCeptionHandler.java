package meumonicipioapiceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //  um controle e vai observa a plicação
public class MeuMonicipioapiCeptionHandler extends ResponseEntityExceptionHandler { // capitura exerçao de erro do render

	// obter a mensagem do mensagem 
	@Autowired
	private MessageSource messageSource;
	
	
	protected ResponseEntity<Object> habderMessagemNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		String mensagemUsuario = this.messageSource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
									
		String  mensagemDesenvolvedor = exception.getCause() !=null ? exception.getCause().toString() : exception.toString();

		List<TratamentoErro> erros = Arrays.asList(new TratamentoErro(mensagemUsuario, mensagemDesenvolvedor));
		
		return handleExceptionInternal(exception, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * tratamento de argumento nao valido
	 */
	protected ResponseEntity<Object> handleMethodArmumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		List<TratamentoErro> erros = criaListaDeErros(ex.getBindingResult());
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<TratamentoErro> criaListaDeErros(BindingResult bindingResult){  //bindingResult lista de erros
		List<TratamentoErro> erros = new ArrayList<>();
		for(FieldError fieldError :bindingResult.getFieldErrors()) {//retorna um FieldError
			String messagemUsuario = this.messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDsenvolvedor = fieldError.toString();
			erros.add(new TratamentoErro(messagemUsuario, mensagemDsenvolvedor));
		}
		
		return erros;
	}
	
	/**
	 *  metodo que vai tratar erro quando deleta o
	 *   mesmo id 2 ves 
	 */
	@ExceptionHandler({EmptyResultDataAccessException.class})// AS CLASSE QUE DEVE TRATAR
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){ // mensagem de erro gerada
		
		String messagemUsuario = this.messageSource.getMessage("recurso.nao-encontrado",null, LocaleContextHolder.getLocale());
		String mensagemDsenvolvedor = ex.toString();
		
		List<TratamentoErro> erros = Arrays.asList(new TratamentoErro(messagemUsuario, mensagemDsenvolvedor));
		
		return handleExceptionInternal(ex,erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	/**
	 * metodo para tratar A exeçao da data
	 */
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handlerDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		
		String messagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida",null, LocaleContextHolder.getLocale());
		
		//String mensagemDsenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

		String mensagemDsenvolvedor = ex.toString();
		
		List<TratamentoErro> erros =Arrays.asList(new TratamentoErro(messagemUsuario, mensagemDsenvolvedor));
		
	return handleExceptionInternal(ex,  erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	
	}
}
