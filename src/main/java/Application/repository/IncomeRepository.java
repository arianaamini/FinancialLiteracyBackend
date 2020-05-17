package Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Application.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

  @Query(value = "SELECT * FROM Income WHERE MONTH(account_date) =  ?1 AND YEAR(account_date) = ?2", nativeQuery = true)
  List<Income> findIncomeByMonth(int Month, int year);
}
