package Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Application.entity.Debt;
@Repository
public interface DebtRepository extends JpaRepository<Debt, Integer> {

  @Query(value = "SELECT * FROM Debt EXTRACT(MONTH FROM account_date) =  ?1 AND EXTRACT(YEAR FROM account_date)= ?2", nativeQuery = true)
  List<Debt> findDebtByMonth(int Month, int year);
}
