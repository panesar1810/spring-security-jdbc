DROP DATABASE IF EXISTS security;
CREATE DATABASE security;
USE security;

CREATE TABLE secuser (
	userId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE secrole (
	roleId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	rolename VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE secuserrole (
	userRoleId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId BIGINT NOT NULL,
	roleId BIGINT NOT NULL
);

ALTER TABLE secuserrole
	ADD CONSTRAINT USER_ROLE_UK unique (userId, roleId);

ALTER TABLE secuserrole
	ADD CONSTRAINT USER_ROLE_FK1 FOREIGN KEY (userId)
	REFERENCES secuser (userId);
 
ALTER TABLE secuserrole
	ADD CONSTRAINT USER_ROLE_FK2 FOREIGN KEY (roleId)
	REFERENCES secrole (roleId);

INSERT INTO secrole (rolename) VALUES ('ROLE_ADMIN');
INSERT INTO secrole (rolename) VALUES ('ROLE_USER');


INSERT INTO secuser (username, password, enabled)  
    VALUES ('root', '$2a$10$wiAVv6tDkXGkEAujaWYB6eXSEzF53yuJzsPI.eo43BA3FsQgQn3MO', true);

INSERT INTO secuser (username, password, enabled)  
    VALUES ('admin', '$2a$10$wiAVv6tDkXGkEAujaWYB6eXSEzF53yuJzsPI.eo43BA3FsQgQn3MO', true);
    
INSERT INTO secuser (username, password, enabled)  
    VALUES ('user', '$2a$10$wiAVv6tDkXGkEAujaWYB6eXSEzF53yuJzsPI.eo43BA3FsQgQn3MO', true);

INSERT INTO secuserrole (userId, roleId) 
    VALUES (1, 1);

INSERT INTO secuserrole (userId, roleId) 
    VALUES (2, 1);  

INSERT INTO secuserrole (userId, roleId) 
    VALUES (3, 2);  
