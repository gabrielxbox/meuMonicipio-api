package repository.administrador;

import model.Administrador;
import org.springframework.data.domain.Page;

public interface AdministradorRepositoryQuery {

	public Page<Administrador>filtrar(Administrador administrador);
	
	public Page<Administrador>resumir(Administrador administrador);
	
}
