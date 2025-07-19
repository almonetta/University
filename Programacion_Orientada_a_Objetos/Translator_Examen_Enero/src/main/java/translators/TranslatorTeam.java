package translators;

import java.util.HashSet;
import java.util.Set;

public class TranslatorTeam {
    private Set<Translator> equipo;
    private Translator translator1;
    private Translator translator2;
    private double coste;


    public TranslatorTeam(Translator translator1, Translator translator2){
        this.translator1 = translator1;
        this.translator2 = translator2;
        this.equipo = new HashSet<Translator>();
        this.equipo.add(translator1);
        this.equipo.add(translator2);
    }

    public Translator getTranslator1() {
        return translator1;
    }

    public Translator getTranslator2() {
        return translator2;
    }

    public Set<Translator> getEquipo() {
        return equipo;
    }

    public void setTranslator1(Translator translator1) {
        this.translator1 = translator1;
    }

    public void setTranslator2(Translator translator2) {
        this.translator2 = translator2;
    }

    public void setEquipo(Set<Translator> equipo) {
        this.equipo = equipo;
    }

    protected double getCoste(){
        double suma = 0;
        for(Translator translator : this.equipo){
            suma += translator.getCostePalabra();
        }
        this.coste = suma * 1.3;
        return this.coste;
    }
}
