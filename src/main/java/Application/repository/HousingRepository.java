package Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Application.entity.Housing;
@Repository
public interface HousingRepository extends JpaRepository<Housing, Integer> {

  @Query(value = "SELECT * FROM Housing WHERE EXTRACT(MONTH FROM account_date) =  ?1 AND EXTRACT(YEAR FROM account_date) = ?2", nativeQuery = true)
  List<Housing> findHousingByMonth(int Month, int year);
}
