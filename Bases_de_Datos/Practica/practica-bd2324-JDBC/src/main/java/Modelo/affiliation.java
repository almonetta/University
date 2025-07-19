package Modelo;

import java.util.Date;

public class affiliation {

    private Long affiliation_id;
    private String affiliation_name;
    private String city;
    private String country_name;

    private int numAut;

    public affiliation(Long affiliation_id, String affiliation_name, String city,String country_name) {
        this.affiliation_id = affiliation_id;
        this.affiliation_name = affiliation_name;
        this.city = city;
        this.country_name = country_name;

    }

    public affiliation(String affiliation_name, int numAut) {
        this.affiliation_name = affiliation_name;
        this.numAut= numAut;
    }

    public Long getAffiliation_id() {
        return affiliation_id;
    }

    public void setAffiliation_id(Long affiliation_id) {
        this.affiliation_id = affiliation_id;
    }

    public String getAffiliation_name() {
        return affiliation_name;
    }

    public void setAffiliation_name(String affiliation_name) {
        this.affiliation_name = affiliation_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getNumAut() {
        return numAut;
    }

}
