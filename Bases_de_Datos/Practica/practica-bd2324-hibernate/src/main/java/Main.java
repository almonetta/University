import model.Affiliation;
import model.Author;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class Main {

    public static void main (String[] args) throws Exception {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        // @TODO Crea una afiliación con nombre "Universidad Politécnica de Madrid" de la
        // ciudad de Madrid, España y guarda dicha afiliación en la base de datos.
        Affiliation afiliacion = new Affiliation("Universidad Politécnica de Madrid", "Madrid", "España");
        session.beginTransaction();
        session.save(afiliacion);
        session.getTransaction().commit();
        System.out.println("Directorio de Trabajo: " + System.getProperty("user.dir"));
        // @TODO Lee el fichero CSV authors.csv que encontrarás en resources y recorrelo usando
        // CSVParser para crear los autores que en el se encuentran. Asigna dichos autores a la
        // afiliación creada anteriormente. Guarda estos autores y sus afiliaciones en la base
        // de datos.

        String csvFilePath = "E:/Users/CRISTIAN/Descargas/practica-bd2324-hibernate-main/src/main/resources/authors.csv";
        Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
        for (CSVRecord record : csvParser) {
            session.beginTransaction();

            String authorName = record.get("author_name");
            double importance = Double.parseDouble(record.get("importance"));
            Author autor = new Author(authorName, importance);
            autor.getAffiliations().add(afiliacion);
            session.saveOrUpdate(autor);
            session.getTransaction().commit();
        }

        session.close();
    }
}
