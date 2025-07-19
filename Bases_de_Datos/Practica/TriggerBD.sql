# Trigger n
-- Crear la tabla de revisiones
CREATE TABLE revision (
    revision_id INT PRIMARY KEY AUTO_INCREMENT,
    DOI VARCHAR(200),
    revisor_id BIGINT,
    FOREIGN KEY (DOI) REFERENCES article(DOI),
    FOREIGN KEY (revisor_id) REFERENCES author(author_id)
);

-- Crear el trigger para evitar que un revisor sea también autor del mismo artículo
DELIMITER //
CREATE TRIGGER before_insert_revision
BEFORE INSERT ON revision
FOR EACH ROW
BEGIN
    DECLARE author_count INT;

    -- Verificar si el revisor ya es autor del mismo artículo
    SELECT COUNT(*) INTO author_count
    FROM author_article
    WHERE author_id = NEW.revisor_id 
    AND DOI = NEW.DOI;

    -- Si el revisor ya es autor, lanzar una excepción
    IF author_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede asignar a un revisor que ya es autor del mismo artículo.';
    END IF;
END //
DELIMITER ;

-- Intentar asignar autor que no es autor de su articulo
INSERT INTO revision (DOI, revisor_id) VALUES ('10.1016/j.ijinfomgt.2021.102361', 6504100776);

-- Intentar asignar autor que si es autor de su articulo 
INSERT INTO revision (DOI, revisor_id) VALUES ('10.1002/cnm.3343', 6503851171);


