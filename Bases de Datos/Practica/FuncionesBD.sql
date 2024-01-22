# Funcion m

DELIMITER // 

CREATE FUNCTION ObtenerPromedioArticulosPorAnio( journal_id_param BIGINT) 
RETURNS DECIMAL(10, 2)
DETERMINISTIC
BEGIN
    DECLARE total_articulos INT;
    DECLARE total_anios INT;
    DECLARE promedio DECIMAL(10, 2);

    -- Obtener el total de artículos y años para la revista dada
    SELECT COUNT(*) INTO total_articulos 
    FROM article 
    WHERE journal_id = journal_id_param;
    
    SELECT COUNT(DISTINCT YEAR(publication_date)) INTO total_anios 
    FROM article 
    WHERE journal_id = journal_id_param;

    -- Calcular el promedio
    IF total_anios > 0 THEN
        SET promedio = total_articulos / total_anios;
    ELSE
        SET promedio = 0;
    END IF;

    -- Devolver el resultado
    RETURN promedio;
END //
DELIMITER ;

SELECT ObtenerPromedioArticulosPorAnio(21100900379)


