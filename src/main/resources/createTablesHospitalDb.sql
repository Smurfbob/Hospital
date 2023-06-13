CREATE TABLE IF NOT EXISTS ort (
	plz int NOT NULL PRIMARY KEY,
	name varchar(255) NOT NULL,
	region varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS krankenhaus (
	krankenhaus_id int NOT NULL PRIMARY KEY,
	name varchar(255) NOT NULL,
	strasse varchar(255) NOT NULL,
	ansprechpartner varchar(255) NULL,
	plz int FOREIGN KEY REFERENCES ort(plz)
);

CREATE TABLE IF NOT EXISTS fachrichtung (
	fachrichtungs_id int NOT NULL PRIMARY KEY,
	name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS station (
	stations_id int NOT NULL PRIMARY KEY,
	name varchar(255) NOT NULL,
	anzahlFreieBetten int NOT NULL,
	anzahlBelegteBetten int NOT NULL,
	krankenhaus_id int FOREIGN KEY REFERENCES krankenhaus(krankenhaus_id)
);

CREATE TABLE IF NOT EXISTS fachrichtungStation (
	fachrichtungs_id int FOREIGN KEY REFERENCES fachrichtung(fachrichtungs_id),
	stations_id int FOREIGN KEY REFERENCES station(stations_id),
);
