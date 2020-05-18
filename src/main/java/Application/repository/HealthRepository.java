package Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Application.entity.Health;
@Repository
public interface HealthRepository extends JpaRepository<Health, Integer> {

  @Query(value = "SELECT * FROM Health EXTRACT(MONTH FROM account_date) =  ?1 AND EXTRACT(YEAR FROM account_date) = ?2", nativeQuery = true)
  List<Health> findHealthByMonth(int Month, int year);
}
