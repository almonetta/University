#CONSULTAS

#Consulta c 

SELECT DISTINCT author.author_name
FROM author
JOIN author_affiliation ON author.author_id = author_affiliation.author_id
JOIN affiliation ON  author_affiliation.affiliation_id = affiliation.affiliation_id
JOIN author_article ON author.author_id = author_article.author_id
JOIN article ON author_article.DOI = article.DOI
WHERE affiliation.affiliation_name = 'Universidad Politécnica de Madrid'
AND EXTRACT(YEAR FROM article.publication_date) IN (2020,2021)
ORDER BY author.author_name;

#Consulta d

SELECT DISTINCT author.author_name
FROM author
JOIN author_affiliation ON author.author_id = author_affiliation.author_id
JOIN affiliation ON  author_affiliation.affiliation_id = affiliation.affiliation_id
JOIN author_article ON author.author_id = author_article.author_id
JOIN article ON author_article.DOI = article.DOI
WHERE affiliation.affiliation_name = 'Universidad Politécnica de Madrid'
AND author.author_id IN (SELECT author_id FROM author_article
								INNER JOIN article ON author_article.DOI = article.DOI
                                WHERE EXTRACT(YEAR FROM publication_date) = 2020
                                )
AND author.author_id IN (SELECT author_id FROM author_article
								INNER JOIN article ON author_article.DOI = article.DOI
                                WHERE EXTRACT(YEAR FROM publication_date) = 2021
                                )
ORDER BY author.author_name;

# Consulta e 
SELECT author.author_name, affiliation.affiliation_name
FROM author 
JOIN author_affiliation ON author.author_id = author_affiliation.author_id
JOIN affiliation ON author_affiliation.affiliation_id = affiliation.affiliation_id
LEFT JOIN
    author_article  ON author.author_id = author_article.author_id
LEFT JOIN
    article  ON author_article.DOI = article.DOI
WHERE
    affiliation.country_name = 'Spain'
    AND author.author_id NOT IN (SELECT author_id FROM author_article
								INNER JOIN article ON author_article.DOI = article.DOI
                                WHERE EXTRACT(YEAR FROM publication_date) = 2020
                                )
	AND author.author_id NOT IN (SELECT author_id FROM author_article
								INNER JOIN article ON author_article.DOI = article.DOI
                                WHERE EXTRACT(YEAR FROM publication_date) = 2021
                                )
ORDER BY
    affiliation_name,
    author_name;



# Consulta f
SELECT journal_name, issn, SUM(num_citations) AS cit_totales
FROM journal
INNER JOIN article ON journal.journal_id = article.journal_id
WHERE JIF_Quartile = 'Q1'
GROUP BY journal_name, issn;

/*g) Resolver en SQL la consulta: "Obtener el nombre de la revista y el total de citas (num_citations) que hayan recibido sus artículos 
para aquella/s revista/s que, perteneciendo al primer cuartil del factor de impacto (q1), tengan el mayor número de citas de toda la 
base de datos".
hacer un tabla exclusivamente con las revistas que pertenecen al primer cuartil*/
WITH MaxCitations AS (SELECT journal.journal_id, MAX(article.num_citations) AS max_citas
			FROM journal
			INNER JOIN article ON article.journal_id = journal.journal_id
			GROUP BY journal_id)
SELECT journal.journal_name, article.num_citations AS max_citations
FROM journal
	INNER JOIN article ON article.journal_id = journal.journal_id
	INNER JOIN MaxCitations ON MaxCitations.journal_id = journal.journal_id
WHERE article.num_citations = MaxCitations.max_citas;

# Consulta h
WITH MaxAnio AS (
  SELECT MAX(EXTRACT(YEAR FROM publication_date)) AS max_anio
  FROM article
)

SELECT affiliation_name 
FROM affiliation
WHERE affiliation_id IN (
	SELECT affiliation_id
    FROM 	(SELECT affiliation_id, author.author_id
			FROM author
			INNER JOIN author_affiliation ON author.author_id = author_affiliation.author_id
			INNER JOIN author_article ON author.author_id = author_article.author_id
			INNER JOIN article ON author_article.DOI = article.DOI
			CROSS JOIN MaxAnio
			WHERE EXTRACT(YEAR FROM publication_date) = MaxAnio.max_anio
			GROUP BY affiliation_id, author.author_id
			HAVING COUNT(article.DOI) > 5)
	AS ArticuloAnios
    GROUP BY affiliation_id
    HAVING COUNT(ArticuloAnios.author_id) >= 10
);

/*i) Resolver en SQL la consulta: "Obtener el nombre de aquellas revistas que, habiendo publicado más de 300 artículos en el año de mayor 
antigüedad que figure en la base de datos tengan un factor de impacto (JIF) superior a la media de los factores de impacto del global de 
las revistas de la base datos. El año debe calcularse forma dinámica con la consulta".
subconsulta de año de mayor antigüedad de todos los articulos*/
WITH MinYear AS (SELECT MIN(EXTRACT(YEAR FROM publication_date)) AS year_min
			FROM article)
SELECT journal.journal_name
FROM journal
	INNER JOIN article ON article.journal_id = journal.journal_id
	JOIN MinYear ON EXTRACT(YEAR FROM article.publication_date) = MinYear.year_min
WHERE journal.journal_id IN (SELECT journal.journal_id 
			     FROM journal
				JOIN article ON article.journal_id = journal.journal_id
				JOIN MinYear ON EXTRACT(YEAR FROM article.publication_date) = MinYear.year_min
			     GROUP BY journal.journal_id
			     HAVING COUNT(article.DOI) > 300 AND
				    AVG(journal.JIF) > (SELECT AVG(JIF) FROM journal)
			    );
# Consulta j 
SELECT JOURNAL.journal_name
FROM JOURNAL
JOIN ARTICLE ON JOURNAL.journal_id = ARTICLE.journal_id
GROUP BY JOURNAL.journal_id, JOURNAL.Journal_name
HAVING SUM(ARTICLE.num_citations) > (
    SELECT AVG(subquery.total_citaciones)
    FROM (
        SELECT SUM(ARTICLE.num_citations) AS total_citaciones
        FROM ARTICLE
        GROUP BY ARTICLE.journal_id
    ) AS subquery
    WHERE subquery.total_citaciones IS NOT NULL
);

# Consulta k
WITH AniosUnicos AS (
  SELECT DISTINCT EXTRACT(YEAR FROM publication_date) AS anio
  FROM article
)

SELECT author_name
FROM author
WHERE author_id IN (
    SELECT author_id
    FROM author_article
    INNER JOIN article ON author_article.DOI = article.DOI
    GROUP BY author_id
    HAVING COUNT(DISTINCT EXTRACT(YEAR FROM publication_date)) = (SELECT COUNT(*) FROM AniosUnicos)

);

