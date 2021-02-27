package repository.telefone;

import model.TelefoneUtil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneUtilRepository extends JpaRepository<TelefoneUtil, Long>, TelefoneUtilRepositoryQuery {

}
