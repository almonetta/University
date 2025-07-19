# Procedimiento l
DELIMITER //

CREATE PROCEDURE ObtenerRevistaMaxAutores(IN anio INT, OUT journal_name VARCHAR(255), OUT num_autores INT)
BEGIN
    -- Variables para controlar el bucle del cursor
    DECLARE done INT DEFAULT FALSE;

    -- Variables para almacenar temporalmente los resultados del cursor
    DECLARE temp_nombre_revista VARCHAR(255);
    DECLARE temp_num_autores INT;

    -- Inicializar la variable max_num_autores con un valor bajo
    DECLARE max_num_autores INT DEFAULT 0;

    -- Variables para almacenar los resultados
    DECLARE journal_ids TEXT DEFAULT '';

    -- Crear un cursor para obtener la información necesaria
    DECLARE cur CURSOR FOR
        SELECT journal.journal_name, COUNT(DISTINCT author_article.author_id) AS num_authors
        FROM journal
        JOIN article ON journal.journal_id = article.journal_id
        JOIN author_article ON article.DOI = author_article.DOI
        WHERE YEAR(article.publication_date) = anio
        GROUP BY journal.journal_id;

    -- Manejador para el final del cursor
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- Abrir el cursor y procesar los resultados
    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO temp_nombre_revista, temp_num_autores;

        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Actualizar max_num_autores y revista_ids si encontramos un nuevo máximo
        IF temp_num_autores > max_num_autores THEN
            SET max_num_autores = temp_num_autores;
            SET journal_ids = temp_nombre_revista;
        ELSEIF temp_num_autores = max_num_autores THEN
            SET journal_ids = CONCAT(journal_ids, ';', temp_nombre_revista);
        END IF;
    END LOOP;

    -- Cerrar el cursor
    CLOSE cur;

    -- Asignar los valores finales a los parámetros de salida
    SET journal_name = journal_ids;
    SET num_autores = max_num_autores;
END //

DELIMITER ;

CALL ObtenerRevistaMaxAutores(2021, @abc, @cba);

SELECT @abc AS journal_name, @cba AS maxAutores;
