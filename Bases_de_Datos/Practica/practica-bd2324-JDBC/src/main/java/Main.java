import Modelo.affiliation;
import Modelo.article;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    // @TODO Sistituye xxx por los parámetros de tu conexión

    private static final String DB_SERVER = "localhost";

    private static final int DB_PORT = 3306;

    private static final String DB_NAME =  "bd2324";

    private static final String DB_USER = "root";

    private static final String DB_PASS = "root";

    private static Connection conn;

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
        conn = DriverManager.getConnection(url, DB_USER, DB_PASS);

        String nombre = "Liu";
        // @TODO Prueba sus funciones
        // 1. Añadete como autor a la base de datos
        nuevoAutor(nombre);
        // 2. Muestra por pantalla la lista de artículos del autor “Ortega F.” en 2021
        listaArticulosPorAutor("Ortega F.", 2021);
        System.out.println("-----------------------------------------------------------------------------\n");
        // 3. Muestra por pantalla una lista de las afiliaciones y el número de autores que
        //    tiene cada una
        listaAfiliaciones();

        conn.close();
    }

    private static void nuevoAutor (String authorName) throws SQLException {
        Random random = new Random();
        long random_id = generarId(random); //author_id
        PreparedStatement stmt;
        float importance = 0;
        stmt = conn.prepareStatement("INSERT INTO author (author_id, author_name, importance) VALUES (?,?,?)");
        stmt.setLong(1,random_id);
        stmt.setString(2,authorName);
        stmt.setFloat(3,importance);

        stmt.executeUpdate();
    }

    private static void listaArticulosPorAutor (String authorName, int year) throws SQLException {
        List<article> articles = new ArrayList<>();
        PreparedStatement stmt;
        stmt = conn.prepareStatement("SELECT article.title, article.publication_date FROM article INNER JOIN author_article on article.DOI=author_article.DOI INNER JOIN author ON author_article.author_id = author.author_id WHERE author_name =  ? AND YEAR(publication_date) = ?");
        // Hacer la consulta en SQL y meterlo dentro
        stmt.setString(1,authorName);
        stmt.setInt(2,year);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            String title=rs.getString("title");
            Date publication_date=rs.getDate("publication_date");
            article a = new article(title,publication_date);
            articles.add(a);
        }
        rs.close();
        System.out.println("Lista de articulos\n");
        for(article a : articles) {
            System.out.println(a.getTitle()+" "+a.getPublication_date()+"\n");
        }
    }

    private static void listaAfiliaciones() throws SQLException {
        // @TODO Muestra por pantalla una lista de las instituciones y el numero de autores que
        //  tienen ordenados de más a menos autores
        List<affiliation> affiliations = new ArrayList<>();
        PreparedStatement stmt;
        stmt = conn.prepareStatement("SELECT affiliation_name, count(author_id) as numAutores FROM affiliation INNER JOIN author_affiliation ON affiliation.affiliation_id = author_affiliation.affiliation_id GROUP BY affiliation.affiliation_name ORDER BY numAutores DESC");
        // Hacer la consulta en SQL y meterlo dentro

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){

            String affiliation_name = rs.getString("affiliation_name");
            int numAut = rs.getInt("numAutores");
            affiliation a = new affiliation(affiliation_name,numAut);
            affiliations.add(a);
        }
        rs.close();
        System.out.println("Lista de afiliaciones\n");
        for(affiliation a : affiliations) {
            System.out.println(a.getAffiliation_name()+" "+a.getNumAut()+"\n");
        }
    }

    private static long generarId(Random random) throws SQLException {
       StringBuilder numeroAleatorio = new StringBuilder();
       for(int i = 0; i < 9; i++){
           int digitoAleatorio = random.nextInt(10);
           numeroAleatorio.append(digitoAleatorio);
       }

       return Long.parseLong(numeroAleatorio.toString());

    }
}


