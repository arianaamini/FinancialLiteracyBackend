package Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Application.entity.Savings;
@Repository
public interface SavingsRepository extends JpaRepository<Savings, Integer> {

  @Query(value = "SELECT * FROM Savings WHERE EXTRACT(MONTH FROM account_date) =  ?1 AND EXTRACT(YEAR FROM account_date) = ?2", nativeQuery = true)
  List<Savings> findSavingsByMonth(int Month, int year);
}
