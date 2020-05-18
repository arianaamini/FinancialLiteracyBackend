package Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Application.entity.Personal;
@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {

  @Query(value = "SELECT * FROM Personal WHERE EXTRACT(MONTH FROM account_date) =  ?1 AND EXTRACT(YEAR FROM account_date) = ?2", nativeQuery = true)
  List<Personal> findPersonalByMonth(int Month, int year);
}
