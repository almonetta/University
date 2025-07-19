import translators.Translation;
import translators.TranslationService;
import translators.Translator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> andrea_lan = new ArrayList<String>();
        andrea_lan.add("es");
        andrea_lan.add("en");
        andrea_lan.add("ru");
        andrea_lan.add("it");
        andrea_lan.add("se");
        ArrayList<String> luis_lan = new ArrayList<String>();
        luis_lan.add("es");
        luis_lan.add("en");
        luis_lan.add("al");
        luis_lan.add("ch");
        ArrayList<String> jesus_lan = new ArrayList<String>();
        jesus_lan.add("en");
        jesus_lan.add("ch");
        jesus_lan.add("nd");
        ArrayList<String> javier_lan = new ArrayList<String>();
        javier_lan.add("es");
        javier_lan.add("en");
        javier_lan.add("it");
        javier_lan.add("fr");
        ArrayList<String> joaquin_lan = new ArrayList<String>();
        joaquin_lan.add("en");
        joaquin_lan.add("kr");
        joaquin_lan.add("po");
        joaquin_lan.add("es");
        joaquin_lan.add("su");
        ArrayList<String> agustin_lan = new ArrayList<String>();
        agustin_lan.add("es");
        agustin_lan.add("en");
        agustin_lan.add("fr");
        agustin_lan.add("al");
        agustin_lan.add("jp");

        Translator translator1 = new Translator("andrea", andrea_lan, 100 );
        Translator translator2 = new Translator("luis", luis_lan, 150);
        Translator translator3 = new Translator("jesus", jesus_lan, 350);
        Translator translator4 = new Translator("javier", javier_lan, 170);
        Translator translator5 = new Translator("joaquin", joaquin_lan, 350);
        Translator translator6 = new Translator("agustin", agustin_lan, 180);

        String originLanguage = "es";
        String targetLanguage = "fr";
        String originText = "Hola blah blah";

        Translation translation = new Translation(originLanguage, originText, targetLanguage);

        TranslationService servicio = new TranslationService();
        servicio.addTranslator(translator1);
        servicio.addTranslator(translator2);
        servicio.addTranslator(translator3);
        servicio.addTranslator(translator4);
        servicio.addTranslator(translator5);
        servicio.addTranslator(translator6);

        Translator trans1 = servicio.getTranslator(translation);
        System.out.println("Traductor o equipo mas barato para la traduccion es - fr: "+
                trans1.getNombre());

        Set<Translator> employees = new HashSet<Translator>();
        employees = servicio.getSpeakEmployees("it");
        for(Translator translator : employees){
            System.out.println("Traductor: " + translator.getNombre());
        }
    }
}
