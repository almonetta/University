package translators;

import java.util.HashSet;
import java.util.Set;

public class TranslationService {

  private Set<Translator> employees;

  public TranslationService() {
    this.employees = new HashSet<Translator>();
  }

  public void addTranslator(Translator employee) {
    this.employees.add(employee);
  }

  /**
   * Método que tiene que devolver el traductor o equipo de traducción más barato para
   * la traducción pasada por parámetro
   * @param translation traducción a realizar
   * @return traductor o equipo de traducción más barato para la traducción pasada
   *         por parámetro
   */
  public Translator getTranslator(Translation translation) {
    Set<Translator> originEmployees = this.getSpeakEmployees(translation.getOriginLanguage());
    if (originEmployees.isEmpty()){
      return null;
    }
    Set<Translator> targetEmployees = this.getSpeakEmployees(translation.getTargetLanguage());
    if (targetEmployees.isEmpty()){
      return null;
    }    
    Set<Translator> bothEmployees = this.intersection(originEmployees, targetEmployees);
    if (!bothEmployees.isEmpty()) {
      return this.getCheapestEmployee(bothEmployees, translation);
    }
    return this.getCheapestTeam(originEmployees, targetEmployees, translation);

  }

  /**
   * Método que tiene que devolver los empleados que hablan el idioma pasado por parámetro
   * @param language idioma buscado
   * @return conjunto de empleados que hablan el idioma pasado por parámetro
   */
  public Set<Translator> getSpeakEmployees(String language) {
    Set<Translator> employees = new HashSet<Translator>();
    for(Translator employee : this.employees){
      if(employee.getLanguages().contains(language)){
        employees.add(employee);
      }
    }
    return employees;
  }

  /**
   * Método que devuelve la intersección de dos conjuntos
   * @param originEmployees
   * @param targetEmployees
   * @return conjunto intersección
   */
  private Set<Translator> intersection(Set<Translator> originEmployees,
      Set<Translator> targetEmployees) {
    Set<Translator> intersection = new HashSet<Translator>(originEmployees);
    intersection.retainAll(targetEmployees);
    return intersection;
  }

  /**
   * Método que tiene que devolver el elemento del conjunto que tiene el
   * precio más barato para la traducción pasada por parámetro
   * @param employees conjunto a evaluar
   * @param translation traducción a realizar
   * @return elemento con el precio más barato
   */
  private Translator getCheapestEmployee(Set<? extends Translator> employees,
      Translation translation) {
    double minCost = Float.POSITIVE_INFINITY;
    Translator minEmployee = null;
    for (Translator employee : employees) {
      int numPalabras = translation.getNumWordsInText();
      double cost = employee.getCostePalabra()*numPalabras;
      if (minCost > cost) {
        minCost = cost;
        minEmployee = employee;
      }
    }
    return minEmployee;
  }

  /**
   * Método que tiene que devolver el equipo de traducción más barato para la traducción pasada por parámetro
   * @param originEmployees conjunto de empleados que hablan el idioma origen
   * @param targetEmployees conjunto de empleados que hablan el idioma destino
   * @return equipo de traducción más barato
   */
  private Translator getCheapestTeam(Set<Translator> originEmployees,
      Set<Translator> targetEmployees, Translation translation) {
    Set<Translator> teams = new HashSet<Translator>();
    double minCostTeam = Float.POSITIVE_INFINITY;
    TranslatorTeam minTeam = null;
    for (Translator originEmployee : originEmployees) {
      for (Translator targetEmployee : targetEmployees) {
        if (originEmployee.speak(targetEmployee)) {
          // Crear el equipo de traducción con originEmployee y targetEmployee
          // y añadirlo al conjunto teams
          TranslatorTeam equipo = new TranslatorTeam(originEmployee, targetEmployee);
          double costTeam = equipo.getCoste();
          if(minCostTeam > costTeam){
            minCostTeam = costTeam;
            minTeam = equipo;
          }
        }
      }
    }
    if (teams.isEmpty()) {
      return null;
    }
    return this.getCheapestEmployee(minTeam.getEquipo(), translation);
      //return this.getCheapestEmployee(targetEmployees, translation);
  }
}
