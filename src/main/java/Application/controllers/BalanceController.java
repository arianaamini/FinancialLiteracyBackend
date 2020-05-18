package Application.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Application.entity.Debt;
import Application.entity.Health;
import Application.entity.Housing;
import Application.entity.Income;
import Application.entity.Insurance;
import Application.entity.Personal;
import Application.entity.Savings;
import Application.entity.Transportation;
import Application.entity.Utilities;
import Application.repository.DebtRepository;
import Application.repository.HealthRepository;
import Application.repository.HousingRepository;
import Application.repository.IncomeRepository;
import Application.repository.InsuranceRepository;
import Application.repository.PersonalRepository;
import Application.repository.SavingsRepository;
import Application.repository.TransportationRepository;
import Application.repository.UtilitiesRepository;

@RestController
@RequestMapping("/")
public class BalanceController {

  @Autowired
  private IncomeRepository incomeRepository;

  @Autowired
  private DebtRepository debtRepository;

  @Autowired
  private HealthRepository healthRepository;

  @Autowired
  private HousingRepository housingRepository;

  @Autowired
  private InsuranceRepository insuranceRepository;

  @Autowired
  private PersonalRepository personalRepository;

  @Autowired
  private SavingsRepository savingsRepository;

  @Autowired
  private TransportationRepository transportationRepository;

  @Autowired
  private UtilitiesRepository utilitiesRepository;

  @RequestMapping(value = "getMonthData", method = RequestMethod.GET)
  public ResponseEntity<?> getMonthData(@RequestParam Integer month, @RequestParam Integer year, @RequestParam String table) {

      switch (table){

      case "income":
        return new ResponseEntity<>(incomeRepository.findIncomeByMonth(month, year),HttpStatus.OK);
      case "debt":
        return new ResponseEntity<>(debtRepository.findDebtByMonth(month, year),HttpStatus.OK);
      case "health":
        return new ResponseEntity<>(healthRepository.findHealthByMonth(month, year),HttpStatus.OK);
      case "housing":
        return new ResponseEntity<>(housingRepository.findHousingByMonth(month, year),HttpStatus.OK);
      case "insurance":
        return new ResponseEntity<>(insuranceRepository.findInsuranceByMonth(month, year),HttpStatus.OK);
      case "personal":
        return new ResponseEntity<>(personalRepository.findPersonalByMonth(month, year),HttpStatus.OK);
      case "savings":
        return new ResponseEntity<>(savingsRepository.findSavingsByMonth(month,year),HttpStatus.OK);
      case "transportation":
        return new ResponseEntity<>(transportationRepository.findTransportationByMonth(month, year),HttpStatus.OK);
      case "utilities":
        return new ResponseEntity<>(utilitiesRepository.findUtilitiesByMonth(month, year),HttpStatus.OK);
      default:
        return new ResponseEntity<>(HttpStatus.OK);
      }
  }

  @RequestMapping(value = "saveMonthData", method = RequestMethod.POST)
  public ResponseEntity<?> saveMonthData(@RequestParam Integer month, @RequestParam Integer year,
                                         @RequestParam String table, @RequestParam (defaultValue = "false") boolean isAdmin,
                                         @RequestParam String name, @RequestParam double estimate, @RequestParam double actual) {

    Calendar cal = Calendar.getInstance();
    cal.set(year,month -1,1);
    Date date = cal.getTime();
    switch (table){

    case "income":
      if (isAdmin)
        incomeRepository.save(Income.builder().name(name).estimate(estimate).actual(actual).accountDate(date).build());
      else
        incomeRepository.save(Income.builder().name(name).actual(estimate).accountDate(date).build());
      return new ResponseEntity<>(HttpStatus.CREATED);
    case "debt":
      if (isAdmin)
        debtRepository.save(Debt.builder().name(name).estimate(estimate).actual(actual).accountDate(date).build());
      else
        debtRepository.save(Debt.builder().name(name).actual(estimate).accountDate(date).build());
      return new ResponseEntity<>(HttpStatus.CREATED);
    case "health":
      if (isAdmin)
        healthRepository.save(Health.builder().name(name).estimate(estimate).actual(actual).accountDate(date).build());
      else
        healthRepository.save(Health.builder().name(name).actual(estimate).accountDate(date).build());
      return new ResponseEntity<>(HttpStatus.CREATED);
    case "housing":
      if (isAdmin)
        housingRepository.save(Housing.builder().name(name).estimate(estimate).actual(actual).accountDate(date).build());
      else
        housingRepository.save(Housing.builder().name(name).actual(estimate).accountDate(date).build());
      return new ResponseEntity<>(HttpStatus.CREATED);
    case "insurance":
      if (isAdmin)
        insuranceRepository.save(Insurance.builder().name(name).estimate(estimate).actual(actual).accountDate(date).build());
      else
        insuranceRepository.save(Insurance.builder().name(name).actual(estimate).accountDate(date).build());
      return new ResponseEntity<>(HttpStatus.CREATED);
    case "personal":
      if (isAdmin)
        personalRepository.save(Personal.builder().name(name).estimate(estimate).actual(actual).accountDate(date).build());
      else
        personalRepository.save(Personal.builder().name(name).actual(estimate).accountDate(date).build());
      return new ResponseEntity<>(HttpStatus.CREATED);
    case "savings":
      if (isAdmin)
        savingsRepository.save(Savings.builder().name(name).estimate(estimate).actual(actual).accountDate(date).build());
      else
        savingsRepository.save(Savings.builder().name(name).actual(estimate).accountDate(date).build());
      return new ResponseEntity<>(HttpStatus.CREATED);
    case "transportation":
      if (isAdmin)
        transportationRepository.save(Transportation.builder().name(name).estimate(estimate).actual(actual).accountDate(date).build());
      else
        transportationRepository.save(Transportation.builder().name(name).actual(estimate).accountDate(date).build());
      return new ResponseEntity<>(HttpStatus.CREATED);
    case "utilities":
      if (isAdmin)
        utilitiesRepository.save(Utilities.builder().name(name).estimate(estimate).actual(actual).accountDate(date).build());
      else
        utilitiesRepository.save(Utilities.builder().name(name).actual(estimate).accountDate(date).build());
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(value = "changeMonthData", method = RequestMethod.PUT)
  public ResponseEntity<?> changeMonthData(@RequestParam Integer month, @RequestParam Integer year,
                                         @RequestParam String table, @RequestParam (defaultValue = "false") boolean isAdmin,
                                         @RequestParam String name, @RequestParam double estimate,
                                           @RequestParam Integer id, @RequestParam double actual) {

    Calendar cal = Calendar.getInstance();
    cal.set(year,month,1);
    Date date = cal.getTime();
    switch (table){

    case "income":
      Income income = incomeRepository.findById(id).get();
      income.setName(name);
      income.setEstimate(estimate);
      income.setAccountDate(date);
      if (isAdmin)
        income.setActual(actual);
        incomeRepository.save(income);
      return new ResponseEntity<>(HttpStatus.OK);
    case "debt":
      Debt debt = debtRepository.findById(id).get();
      debt.setName(name);
      debt.setEstimate(estimate);
      debt.setAccountDate(date);
      if (isAdmin)
        debt.setActual(actual);
        debtRepository.save(debt);
      return new ResponseEntity<>(HttpStatus.OK);
    case "health":
      Health health = healthRepository.findById(id).get();
      health.setName(name);
      health.setEstimate(estimate);
      health.setAccountDate(date);
      if (isAdmin)
        health.setActual(actual);
        healthRepository.save(health);
      return new ResponseEntity<>(HttpStatus.OK);
    case "housing":
      Housing housing = housingRepository.findById(id).get();
      housing.setName(name);
      housing.setEstimate(estimate);
      housing.setAccountDate(date);
      if (isAdmin)
        housing.setActual(actual);
      housingRepository.save(housing);
      return new ResponseEntity<>(HttpStatus.OK);
    case "insurance":
      Insurance insurance = insuranceRepository.findById(id).get();
      insurance.setName(name);
      insurance.setEstimate(estimate);
      insurance.setAccountDate(date);
      if (isAdmin)
        insurance.setActual(actual);
      insuranceRepository.save(insurance);
      return new ResponseEntity<>(HttpStatus.OK);
    case "personal":
      Personal personal = personalRepository.findById(id).get();
      personal.setName(name);
      personal.setEstimate(estimate);
      personal.setAccountDate(date);
      if (isAdmin)
        personal.setActual(actual);
      personalRepository.save(personal);
      return new ResponseEntity<>(HttpStatus.OK);
    case "savings":
      Savings savings = savingsRepository.findById(id).get();
      savings.setName(name);
      savings.setEstimate(estimate);
      savings.setAccountDate(date);
      if (isAdmin)
        savings.setActual(actual);
      savingsRepository.save(savings);
      return new ResponseEntity<>(HttpStatus.OK);
    case "transportation":
      Transportation transportation = transportationRepository.findById(id).get();
      transportation.setName(name);
      transportation.setEstimate(estimate);
      transportation.setAccountDate(date);
      if (isAdmin)
        transportation.setActual(actual);
      transportationRepository.save(transportation);
      return new ResponseEntity<>(HttpStatus.OK);
    case "utilities":
      Utilities utilities = utilitiesRepository.findById(id).get();
      utilities.setName(name);
      utilities.setEstimate(estimate);
      utilities.setAccountDate(date);
      if (isAdmin)
        utilities.setActual(actual);
      utilitiesRepository.save(utilities);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

}
