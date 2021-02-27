package repository.academico;

import model.Colegio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ColegioRepository extends JpaRepository <Colegio, Long>  {

}
