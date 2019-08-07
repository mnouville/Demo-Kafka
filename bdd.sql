drop schema if exists `demo-kafka`;
create schema if not exists `demo-kafka`;
use `demo-kafka`;

drop table if exists CLIENT;
drop table if exists COMPTE;

CREATE TABLE CLIENT (
	id bigint PRIMARY KEY NOT NULL auto_increment,
	nom varchar(50),
    prenom varchar(50),
    email varchar(50),
    phonenumber varchar(10)
);

CREATE TABLE COMPTE (
	id bigint PRIMARY KEY NOT NULL auto_increment,
    idclient bigint,
    solde double(50,3)
);

INSERT INTO CLIENT(id, nom, prenom, email, phonenumber) VALUES (1, "Maxime", "Nouville", "mnouville@excilys.com", "0000000000");

INSERT INTO COMPTE(id, idclient, solde) VALUES (1, 1, 150000.0);