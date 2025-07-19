package translators;

import java.util.ArrayList;

public abstract class ITranslator {
    String nombre;
    ArrayList<String> languages;
    double costePalabra;
    public abstract void setNombre(String nombre);
    public abstract String getNombre();
    public abstract void setLanguages(ArrayList<String> languages);
    public abstract ArrayList<String> getLanguages();
    public abstract void setCostePalabra(double costePalabra);
    public abstract double getCostePalabra();
}
