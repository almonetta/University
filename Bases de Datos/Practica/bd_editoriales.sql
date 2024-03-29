CREATE SCHEMA bd_editoriales
DEFAULT CHARACTER SET utf8
COLLATE utf8_spanish2_ci;
USE bd_editoriales;


CREATE TABLE EDITORIAL (
	Editorial_id INT UNIQUE NOT NULL AUTO_INCREMENT,
	Editorial_nombre VARCHAR(30) NOT NULL,
	Nacionalidad VARCHAR(50) NOT NULL,
	JIF_Quartile INT NOT NULL,
	PRIMARY KEY (Editorial_id)	
);

CREATE TABLE AUTOR (
	Autor_id INT UNIQUE NOT NULL AUTO_INCREMENT,
	Nombre_c VARCHAR(30) NOT NULL,
	PRIMARY KEY (Autor_id)
);

CREATE TABLE AREA_INVESTIGACION (
	Area_id INT UNIQUE NOT NULL AUTO_INCREMENT,
	Area_nombre VARCHAR(30) NOT NULL,
	PRIMARY KEY (Area_id)
);

CREATE TABLE GRUPO_INVESTIGACION (
	Grupo_id INT UNIQUE NOT NULL AUTO_INCREMENT,
	Area_id INT NOT NULL,
	PRIMARY KEY (Grupo_id),
    CONSTRAINT FOREIGN KEY (Area_id) REFERENCES AREA_INVESTIGACION (Area_id) ON DELETE RESTRICT ON UPDATE CASCADE
);

 CREATE TABLE JOURNAL (
	Journal_id INT UNIQUE NOT NULL AUTO_INCREMENT,
	Journal_nombre VARCHAR(30) NOT NULL,
	Issn INT NOT NULL,
	JIF float NOT NULL,
	JIF_Quartile float NOT NULL,
    Fecha_p DATETIME,
    Editorial_id INT NOT NULL,
	PRIMARY KEY (Journal_id),
	CONSTRAINT FOREIGN KEY (Editorial_id) REFERENCES EDITORIAL (Editorial_id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE AFILIACION (
	Afiliacion_id INT UNIQUE NOT NULL AUTO_INCREMENT,
	Afiliacion_nombre VARCHAR(30) NOT NULL,
	Ciudad VARCHAR(30) NOT NULL,
	Pais VARCHAR(30) NOT NULL,
	PRIMARY KEY (Afiliacion_id)
);

CREATE TABLE ARTICULO (
	DOI VARCHAR(50) UNIQUE NOT NULL,
	Titulo VARCHAR(30) NOT NULL,
    Fecha_publicacion datetime NOT NULL,
    URL VARCHAR(50) NOT NULL,
    Num_citaciones int,
    Journal_id INT NOT NULL,
    Afiliacion_id INT NOT NULL,
	Area_id INT NOT NULL,
    PRIMARY KEY (DOI),
    CONSTRAINT FOREIGN KEY (Journal_id) REFERENCES JOURNAL (Journal_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (Afiliacion_id) REFERENCES AFILIACION (Afiliacion_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (Area_id) REFERENCES AREA_INVESTIGACION (Area_id) ON DELETE RESTRICT ON UPDATE CASCADE
 );
 
 
CREATE TABLE hace_referencia(
DOI1 VARCHAR(50) NOT NULL,
DOI2 VARCHAR(50) NOT NULL,
PRIMARY KEY (DOI1, DOI2),
FOREIGN KEY (DOI1) REFERENCES ARTICULO (DOI),
FOREIGN KEY (DOI2) REFERENCES ARTICULO (DOI)
);

CREATE TABLE colaboraAUT (
Autor_id1 INT NOT NULL,
Autor_id2 INT NOT NULL,
PRIMARY KEY (Autor_id1, Autor_id2),
FOREIGN KEY (Autor_id1) REFERENCES AUTOR (Autor_id),
FOREIGN KEY (Autor_id2) REFERENCES AUTOR (Autor_id)
);

CREATE TABLE firma (
Autor_id INT NOT NULL,
DOI VARCHAR(50) NOT NULL,
PRIMARY KEY (Autor_id, DOI),
CONSTRAINT FOREIGN KEY (Autor_id) REFERENCES AUTOR (Autor_id),
CONSTRAINT FOREIGN KEY (DOI) REFERENCES ARTICULO (DOI)
);

CREATE TABLE revisa (
Autor_id INT NOT NULL,
DOI VARCHAR(50) NOT NULL,
Fecha_Reali DATETIME NOT NULL,
Realizada boolean,
Resultado VARCHAR(500), 
Fecha_Env DATETIME NOT NULL,  
PRIMARY KEY (Autor_id, DOI),
CONSTRAINT FOREIGN KEY (Autor_id) REFERENCES AUTOR (Autor_id),
CONSTRAINT FOREIGN KEY (DOI) REFERENCES ARTICULO (DOI)
);

CREATE TABLE colaboraGRUP (
Autor_id INT NOT NULL,
Grupo_id INT NOT NULL,
PRIMARY KEY (Autor_id, Grupo_id),
CONSTRAINT FOREIGN KEY (Autor_id) REFERENCES AUTOR (Autor_id),
CONSTRAINT FOREIGN KEY (Grupo_id) REFERENCES GRUPO_INVESTIGACION (Grupo_id)
);

CREATE TABLE pertenece (
Afiliacion_id INT NOT NULL,
Autor_id INT NOT NULL,
PRIMARY KEY (Afiliacion_id, Autor_id),
CONSTRAINT FOREIGN KEY (Afiliacion_id) REFERENCES AFILIACION (Afiliacion_id),
CONSTRAINT FOREIGN KEY (Autor_id) REFERENCES AUTOR (Autor_id)
);

CREATE TABLE publicaAR (
DOI VARCHAR(50) NOT NULL,
Afiliacion_id INT NOT NULL,
PRIMARY KEY (DOI, Afiliacion_id),
CONSTRAINT FOREIGN KEY (DOI) REFERENCES ARTICULO (DOI),
CONSTRAINT FOREIGN KEY (Afiliacion_id) REFERENCES AFILIACION (Afiliacion_id)
);

CREATE TABLE tiene_el_mismo_tema_que (
Grupo_id INT NOT NULL,
Area_id INT NOT NULL,
PRIMARY KEY (Grupo_id, Area_id),
CONSTRAINT FOREIGN KEY (Grupo_id) REFERENCES GRUPO_INVESTIGACION (Grupo_id),
CONSTRAINT FOREIGN KEY (Area_id) REFERENCES AREA_INVESTIGACION (Area_id)
);

CREATE TABLE pertenece_A (
DOI VARCHAR(50) NOT NULL,
Area_id INT NOT NULL,
PRIMARY KEY (DOI, Area_id),
CONSTRAINT FOREIGN KEY (DOI) REFERENCES ARTICULO (DOI),
CONSTRAINT FOREIGN KEY (Area_id) REFERENCES AREA_INVESTIGACION (Area_id)
);

CREATE TABLE publicaJ (
Journal_id INT NOT NULL,
Editorial_id INT NOT NULL,
PRIMARY KEY (Journal_id, Editorial_id),
FOREIGN KEY (Journal_id) REFERENCES JOURNAL (Journal_id),
FOREIGN KEY (Editorial_id) REFERENCES EDITORIAL (Editorial_id)
);

CREATE TABLE contiene (
DOI VARCHAR(50) NOT NULL,
Journal_id INT NOT NULL,
PRIMARY KEY (DOI, Journal_id),
CONSTRAINT FOREIGN KEY (DOI) REFERENCES ARTICULO (DOI),
CONSTRAINT FOREIGN KEY (Journal_id) REFERENCES JOURNAL (Journal_id)
);









