package translators;

import java.util.ArrayList;
import java.util.Iterator;

public class Translator extends ITranslator{
    private String nombre;
    private ArrayList<String> languages;
    private double costePalabra;
    public Translator(String nombre, ArrayList<String> languages, double costePlabra){
        this.nombre = nombre;
        this.languages = languages;
        this.costePalabra = costePlabra;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setLanguages(ArrayList<String> languages){
        this.languages = languages;
    }

    public ArrayList<String> getLanguages(){
        return this.languages;
    }

    public void setCostePalabra(double costePalabra){
        this.costePalabra = costePalabra;
    }

    public double getCostePalabra(){
        return this.costePalabra;
    }

    protected boolean speak(Translator targetEmployee){
        boolean same = false;
        ArrayList<String> languageTargetEmployee = targetEmployee.getLanguages();
        Iterator<String> iterator = languageTargetEmployee.iterator();
        while(iterator.hasNext()){
            if(this.languages.contains(iterator.next())){
                same = true;
            }
        }
        return same;
    }


}
