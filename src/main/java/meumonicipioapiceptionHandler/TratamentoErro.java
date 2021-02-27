package meumonicipioapiceptionHandler;

public class TratamentoErro {

	private  String  messagemUsuario;
	
	private  String  mensagemDsenvolvedor;


	public TratamentoErro() {
		this.cleanFiles();
	}
	
	public TratamentoErro(String messagemUsuario, String mensagemDsenvolvedor) {
		this.cleanFiles();
		this.messagemUsuario = messagemUsuario;
		this.mensagemDsenvolvedor = mensagemDsenvolvedor;
	}

	


	private void cleanFiles() {
	
		this.messagemUsuario = null;
		this.mensagemDsenvolvedor = null;
		
	}

	public String getMessagemUsuario() {
		return messagemUsuario;
	}

	public void setMessagemUsuario(String messagemUsuario) {
		this.messagemUsuario = messagemUsuario;
	}

	public String getMensagemDsenvolvedor() {
		return mensagemDsenvolvedor;
	}

	public void setMensagemDsenvolvedor(String mensagemDsenvolvedor) {
		this.mensagemDsenvolvedor = mensagemDsenvolvedor;
	}
	
}
