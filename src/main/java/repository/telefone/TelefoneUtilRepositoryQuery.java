package repository.telefone;

import java.util.List;

import model.TelefoneUtil;
import org.springframework.data.domain.Page;

public interface TelefoneUtilRepositoryQuery {

public List<TelefoneUtil>pesquisar(TelefoneUtil telefoneUtil);

public Page<TelefoneUtil>filtrar(TelefoneUtil telefoneUtil);

public Page<TelefoneUtil>resumir(TelefoneUtil telefoneUtil);


}
