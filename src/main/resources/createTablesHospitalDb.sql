CREATE TABLE IF NOT EXISTS Ort (
	PLZ int NOT NULL PRIMARY KEY,
	Name varchar(255) NOT NULL,
	Region varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS  Krankenhaus (
	KrankenhausID int NOT NULL PRIMARY KEY,
	Name varchar(255) NOT NULL,
	Strasse varchar(255) NOT NULL,
	Ansprechpartner varchar(255) NULL,
	PLZ FOREIGN KEY REFERENCES Ort(PLZ)
);

CREATE TABLE IF NOT EXISTS  Fachrichtung (
	FachrichtungsID int NOT NULL PRIMARY KEY,
	Name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Station (
	StationsID int NOT NULL PRIMARY KEY,
	Name varchar(255) NOT NULL,
	AnzahlFreieBetten int NOT NULL,
	AnzahlBelegteBetten int NOT NULL,
	KrankenhausID FOREIGN KEY REFERENCES Krankenhaus(KrankenhausID)
);

CREATE TABLE IF NOT EXISTS  FachrichtungStation (
	FachrichtungsID FOREIGN KEY REFERENCES Fachrichtung(FachrichtungsID),
	StationsID FOREIGN KEY REFERENCES Station(StationsID),
);
