package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// @TODO Realiza todas las anotaciones necesarias en esta clase para que
// que sus instancias sean guardadas en la base de datos utilizando
// Hibernate. Respecta las restricciones de modelado impuestas en el
// enunciado de la práctica. No es necesario modificar el código de esta
// clase, únicamente debes hacer las anotaciones que consideres
// necesarias.
@Entity
@Table(name="Author")
public class Author{

    @Id
    @GeneratedValue
    @Column(name="author_id", nullable = false)
    private Long author_id;
    @Column(name = "author_name", nullable = false)
    private String author_name;
    @Column(name = "importance")
    private double importance;

    @ManyToMany
    @JoinTable(name = "author_affiliations")
    private Set<Affiliation> affiliations;

    @ManyToMany()
    @JoinTable(name = "author_article")
    private Set<Article> articles;

    public Author() {

    }

    public Author(String author_name, double importance) {
        this.author_name = author_name;
        this.importance = importance;
        this.articles = new HashSet<>();
        this.affiliations = new HashSet<>();
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public Double getImportance() {
        return importance;
    }

    public Set<Affiliation> getAffiliations () {
        return this.affiliations;
    }

    public Set<Article> getArticles () {
        return this.articles;
    }
}
