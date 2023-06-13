CREATE TABLE IF NOT EXISTS ort (
	plz int NOT NULL,
	name varchar(255) NOT NULL,
	region varchar(255) NOT NULL,
	PRIMARY KEY (plz)
);

CREATE TABLE IF NOT EXISTS krankenhaus (
	krankenhaus_id int NOT NULL,
	name varchar(255) NOT NULL,
	strasse varchar(255) NOT NULL,
	ansprechpartner varchar(255) NULL,
	plz int NOT NULL,
	PRIMARY KEY (krankenhaus_id),
	FOREIGN KEY (plz) REFERENCES ort(plz)
);

CREATE TABLE IF NOT EXISTS fachrichtung (
	fachrichtungs_id int NOT NULL,
	name varchar(255) NOT NULL,
	PRIMARY KEY (fachrichtungs_id)
);

CREATE TABLE IF NOT EXISTS station (
	stations_id int NOT NULL,
	name varchar(255) NOT NULL,
	anzahlFreieBetten int NOT NULL,
	anzahlBelegteBetten int NOT NULL,
	krankenhaus_id int NOT NULL,
	PRIMARY KEY (stations_id),
	FOREIGN KEY (krankenhaus_id) REFERENCES krankenhaus(krankenhaus_id)
);

CREATE TABLE IF NOT EXISTS fachrichtungStation (
    fachrichtungs_id int NOT NULL,
    stations_id int NOT NULL,
    FOREIGN KEY (fachrichtungs_id) REFERENCES fachrichtung(fachrichtungs_id),
    FOREIGN KEY (stations_id) REFERENCES station(stations_id)
);
