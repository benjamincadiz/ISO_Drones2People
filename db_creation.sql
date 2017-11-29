CREATE TABLE Usuario (
  DNI       int(8) NOT NULL AUTO_INCREMENT, 
  Nombre    varchar(40), 
  Apellidos varchar(50), 
  email     varchar(255) UNIQUE, 
  password  varchar(255), 
  Telefono  varchar(12), 
  isAdmin   tinyint DEFAULT 0, 
  isArtist  tinyint DEFAULT 0, 
  PRIMARY KEY (DNI));

CREATE TABLE Album (
  ID               int(15) NOT NULL AUTO_INCREMENT, 
  Artista          int(8), 
  Nombre           varchar(255), 
  NumeroCanciones  int(3), 
  Duracion         double, 
  FechaLanzamiento date, 
  PRIMARY KEY (ID),
  FOREIGN KEY (Artista) REFERENCES Usuario(DNI));

CREATE TABLE Cancion (
  Nombre   varchar(255) NOT NULL, 
  Artista  int(8) NOT NULL, 
  Año      date, 
  Album    int(15), 
  Duracion double, 
  PRIMARY KEY (Nombre, 
  Artista),
  FOREIGN KEY(Artista) REFERENCES Usuario(DNI),
  FOREIGN KEY(Album) REFERENCES Album(ID));

CREATE TABLE PlayList (
  ID            varchar(255) NOT NULL, 
  Usuario       int(8), 
  Nombre        varchar(255), 
  FechaCreacion date, 
  PRIMARY KEY (ID),
  FOREIGN KEY (Usuario) REFERENCES Usuario(DNI));

CREATE TABLE `Cancion-Playlist` (
  ID_Playlist   varchar(255) NOT NULL, 
  NombreCancion varchar(255) NOT NULL, 
  Artista       int(8) NOT NULL, 
  PRIMARY KEY (ID_Playlist, 
  NombreCancion, 
  Artista),
  FOREIGN KEY (ID_Playlist) REFERENCES PlayList(ID),
  FOREIGN KEY (NombreCancion, Artista) REFERENCES Cancion(Nombre, Artista));





