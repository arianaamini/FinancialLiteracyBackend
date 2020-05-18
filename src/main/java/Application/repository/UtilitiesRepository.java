package Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Application.entity.Utilities;
@Repository
public interface UtilitiesRepository extends JpaRepository<Utilities, Integer> {

  @Query(value = "SELECT * FROM Utilities WHERE EXTRACT(MONTH FROM account_date) =  ?1 AND EXTRACT(YEAR FROM account_date) = ?2", nativeQuery = true)
  List<Utilities> findUtilitiesByMonth(int Month, int year);
}
