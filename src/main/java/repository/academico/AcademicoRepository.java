package repository.academico;

import model.Academico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AcademicoRepository extends JpaRepository<Academico, Long> {

}
