package Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import Application.entity.Transportation;
@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Integer> {

  @Query(value = "SELECT * FROM Transportation WHERE EXTRACT(MONTH FROM account_date) =  ?1 AND EXTRACT(YEAR FROM account_date) = ?2", nativeQuery = true)
  List<Transportation> findTransportationByMonth(int Month, int year);
}
