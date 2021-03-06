package Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Application.entity.Insurance;
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

  @Query(value = "SELECT * FROM Insurance WHERE EXTRACT(MONTH FROM account_date) =  ?1 AND EXTRACT(YEAR FROM account_date) = ?2", nativeQuery = true)
  List<Insurance> findInsuranceByMonth(int Month, int year);
}
