package weektwo.mahderf.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import weektwo.mahderf.demo.Controllers.EmployeInfo;


public interface EmployeRepository extends CrudRepository<EmployeInfo,Long> {
}
