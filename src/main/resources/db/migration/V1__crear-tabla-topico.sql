CREATE TABLE topicos (
    id bigint NOT NULL AUTO_INCREMENT,
    titulo varchar(255) NOT NULL,
    mensaje varchar(255) NOT NULL,
    fecha_creacion datetime NOT NULL,
    status varchar(50) NOT NULL,
    autor varchar(255) NOT NULL,
    curso varchar(255) NOT NULL,
    PRIMARY KEY (id)

);