-- MySQL dump 8.23
--
-- Host: localhost    Database: ecole
---------------------------------------------------------
-- Server version	3.23.58-nt

--
-- Table structure for table `atelier`
--

DROP TABLE IF EXISTS atelier;
CREATE TABLE atelier (
  eleve_id int(10) unsigned NOT NULL default '0',
  atelier_id int(10) unsigned default '0',
  prixname varchar(50) default NULL,
  prix double default NULL,
  datevalidite datetime default NULL,
  nbrjours int(10) unsigned default '1'
) TYPE=MyISAM;

--
-- Dumping data for table `atelier`
--


LOCK TABLES atelier WRITE;
INSERT INTO atelier VALUES (185,10,'Tarif D',3.89,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (185,9,'Tarif D',3.89,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (184,9,'Tarif B',1,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (184,8,'Tarif B',1,'1999-09-09 00:00:00',4);
INSERT INTO atelier VALUES (135,5,'Tarif A',12.22,'2004-10-10 00:00:00',1);
INSERT INTO atelier VALUES (137,9,'Tarif C',50.25,'2001-09-09 00:00:00',1);
INSERT INTO atelier VALUES (137,8,'Tarif C',50.25,'2001-09-09 00:00:00',2);
INSERT INTO atelier VALUES (192,5,'Tarif B',1,'2004-12-29 00:00:00',1);
INSERT INTO atelier VALUES (136,8,'Tarif B',1,'2010-09-09 00:00:00',4);
INSERT INTO atelier VALUES (186,9,'Tarif A',12.22,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (186,11,'Tarif A',12.22,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (181,11,'Tarif E',22.3,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (184,4,'Tarif B',1,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (180,6,'Tarif D',3.89,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (180,5,'Tarif D',3.89,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (183,11,'Tarif B',1,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (182,9,'Tarif C',50.25,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (179,4,'Tarif C',50.25,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (179,11,'Tarif C',50.25,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (177,6,'Tarif C',50.25,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (176,4,'Tarif B',1,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (176,11,'Tarif B',1,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (178,6,'Tarif C',50.25,'1999-09-09 00:00:00',1);
INSERT INTO atelier VALUES (178,9,'Tarif C',50.25,'1999-09-09 00:00:00',1);
UNLOCK TABLES;

--
-- Table structure for table `cantine`
--

DROP TABLE IF EXISTS cantine;
CREATE TABLE cantine (
  eleve_id int(10) unsigned NOT NULL default '0',
  prixname varchar(50) default NULL,
  prix double default NULL,
  datevalidite datetime default NULL,
  nbrjours int(10) unsigned NOT NULL default '4'
) TYPE=MyISAM;

--
-- Dumping data for table `cantine`
--


LOCK TABLES cantine WRITE;
INSERT INTO cantine VALUES (135,'Tarif C',2,'2000-09-09 00:00:00',1);
INSERT INTO cantine VALUES (175,'Tarif A',4.68,'2000-09-09 00:00:00',2);
INSERT INTO cantine VALUES (137,'Tarif A',4.68,'2000-12-12 00:00:00',1);
UNLOCK TABLES;

--
-- Table structure for table `classe`
--

DROP TABLE IF EXISTS classe;
CREATE TABLE classe (
  id int(10) unsigned NOT NULL auto_increment,
  classe_nom varchar(10) NOT NULL default '',
  instituteur varchar(30) default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY classeID (id),
  KEY classeID_2 (id)
) TYPE=MyISAM;

--
-- Dumping data for table `classe`
--


LOCK TABLES classe WRITE;
INSERT INTO classe VALUES (1,'CE2A','Mr Laurente');
INSERT INTO classe VALUES (2,'CE1a','Mme Catherine');
INSERT INTO classe VALUES (3,'CPa','Mme Dahan');
INSERT INTO classe VALUES (4,'CM1b','toto');
INSERT INTO classe VALUES (14,'CMM','moi');
INSERT INTO classe VALUES (10,'CE2c','jerome');
UNLOCK TABLES;

--
-- Table structure for table `eleve`
--

DROP TABLE IF EXISTS eleve;
CREATE TABLE eleve (
  id int(10) unsigned NOT NULL auto_increment,
  nom varchar(50) NOT NULL default '',
  prenom varchar(50) NOT NULL default '',
  sexe char(1) default NULL,
  dob datetime default '0000-00-00 00:00:00',
  rue varchar(50) default NULL,
  codepostal varchar(6) default NULL,
  ville varchar(30) default NULL,
  telephone1 varchar(11) default NULL,
  telephone2 varchar(11) default NULL,
  telephone3 varchar(11) default NULL,
  classeid int(10) default NULL,
  dateentree datetime default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY id (id),
  KEY id_2 (id)
) TYPE=MyISAM;

--
-- Dumping data for table `eleve`
--


LOCK TABLES eleve WRITE;
INSERT INTO eleve VALUES (135,'Dahan','David','M','0000-00-00 00:00:00','112 rue de la republique','75019','Parise','0142068165','','',1,'2994-09-09 00:00:00');
INSERT INTO eleve VALUES (136,'Scheiner','Marni D','F','1981-04-07 00:00:00','23255 Park de sa rue','91302','calabasas','0198767654','','',1,'0000-00-00 00:00:00');
INSERT INTO eleve VALUES (137,'Du Pont','Charles S','M','1954-08-09 00:00:00','23 rue le rue','76879','paris','09878765','','',1,'0000-00-00 00:00:00');
INSERT INTO eleve VALUES (175,'cartier','jack','M','1978-09-07 00:00:00','67 rie de','98777','pppp','0987890','','',1,'2000-09-01 00:00:00');
INSERT INTO eleve VALUES (176,'telle','laurent','F','1978-09-09 00:00:00','89 ruie ljklj','89777','versailels','0988979','878707','8708789',1,'2000-09-09 00:00:00');
INSERT INTO eleve VALUES (177,'mont','val','F','1967-09-08 00:00:00','98 hjjkh ljh','98786','iolkj','08789','','',1,'2000-09-09 00:00:00');
INSERT INTO eleve VALUES (178,'toto','toto','M','1999-09-09 00:00:00','897 jhljh','98777','lll','89797','','',1,'1998-09-01 00:00:00');
INSERT INTO eleve VALUES (179,'mkomm','mko','m','1987-09-09 00:00:00','897 jhhjhj','78666','paris le','0897890','','',1,'1999-09-09 00:00:00');
INSERT INTO eleve VALUES (180,'ljh','asjh','m','1999-09-09 00:00:00','897 jhljhl','12098','oiup','0897','80970','807',1,'1999-09-09 00:00:00');
INSERT INTO eleve VALUES (181,'jlkhjh','jjhhj','m','1999-09-09 00:00:00','897 jhlh','18987','paris','98798','','',1,'1999-09-09 00:00:00');
INSERT INTO eleve VALUES (182,'lkjhjl','askljdh','f','1976-09-09 00:00:00','987 jhjhhj','89765','paris','79','087','87',1,'1999-09-09 00:00:00');
INSERT INTO eleve VALUES (183,'ljkhljh','asdjhlj','m','1999-09-09 00:00:00','78 jhljh','98777','paris','987','87','87',1,'1999-09-09 00:00:00');
INSERT INTO eleve VALUES (187,'lkjljlhj','asd','M','2000-09-09 00:00:00','ajsdhjk','09876','asd','','','',1,'2000-09-09 00:00:00');
INSERT INTO eleve VALUES (185,'jkhjlkhjlkhjl','asdhg','m','1987-09-09 00:00:00','78 jhlkjl','98777','paris','897','','',1,'1909-09-09 00:00:00');
INSERT INTO eleve VALUES (186,'jlkhj','kajshgd','m','2000-10-09 00:00:00','98 jkashdljh','98777','hjlkh','87','807','87',1,'2002-12-09 00:00:00');
INSERT INTO eleve VALUES (189,'rent','lau','M','2000-09-09 00:00:00','12 kjhl','78666','paris','9870987','','',1,'2000-00-00 00:00:00');
INSERT INTO eleve VALUES (190,'rab','la','M','1989-12-28 00:00:00','123 kjhl','0879','paris','087','','',1,'2021-09-20 04:00:00');
INSERT INTO eleve VALUES (191,'ok','ok','M','1979-09-20 00:00:00','12 jhklh','09876','paris','9879780','','',1,'2020-08-20 04:00:00');
INSERT INTO eleve VALUES (192,'kl','kl','M','0000-00-00 00:00:00','897 jhlkjh','9089','asd','80707','','',1,'0000-00-00 00:00:00');
INSERT INTO eleve VALUES (193,'ljhl','kjh','M','1979-09-20 00:00:00','6789 hjlh','88890','asjdh','7697969','','',1,'2004-10-12 00:00:00');
UNLOCK TABLES;

--
-- Table structure for table `periscotarifs`
--

DROP TABLE IF EXISTS periscotarifs;
CREATE TABLE periscotarifs (
  id int(10) unsigned NOT NULL auto_increment,
  tarifa int(10) unsigned default NULL,
  tarifb int(10) unsigned default NULL,
  tarifc int(10) unsigned default NULL,
  tarifd int(10) unsigned default NULL,
  tarife int(10) unsigned default NULL,
  tariff int(10) unsigned default NULL,
  tarifg int(10) unsigned default NULL,
  tarifh int(10) unsigned default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY id (id),
  KEY id_2 (id)
) TYPE=MyISAM;

--
-- Dumping data for table `periscotarifs`
--


LOCK TABLES periscotarifs WRITE;
INSERT INTO periscotarifs VALUES (1,10,12,23,44,32,456,76,78);
UNLOCK TABLES;

--
-- Table structure for table `refatelier`
--

DROP TABLE IF EXISTS refatelier;
CREATE TABLE refatelier (
  id int(10) unsigned NOT NULL auto_increment,
  atelier_nom varchar(100) NOT NULL default '',
  type char(1) NOT NULL default 'C',
  jour varchar(50) default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY id (id),
  KEY id_2 (id)
) TYPE=MyISAM;

--
-- Dumping data for table `refatelier`
--


LOCK TABLES refatelier WRITE;
INSERT INTO refatelier VALUES (4,'Echecs','p','Lundi');
INSERT INTO refatelier VALUES (5,'Informatique','v','Mardi');
INSERT INTO refatelier VALUES (6,'Cuisine','v','Mercredi');
INSERT INTO refatelier VALUES (8,'Etude','E','Tous');
INSERT INTO refatelier VALUES (9,'mecanique','p',NULL);
INSERT INTO refatelier VALUES (10,'manger','p','mardi');
INSERT INTO refatelier VALUES (11,'strategie','P','samedi');
UNLOCK TABLES;

--
-- Table structure for table `tarifsateliers`
--

DROP TABLE IF EXISTS tarifsateliers;
CREATE TABLE tarifsateliers (
  id int(10) unsigned NOT NULL auto_increment,
  tarif_nom varchar(50) NOT NULL default '',
  tarif_prix double default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY id (id),
  KEY id_2 (id)
) TYPE=MyISAM;

--
-- Dumping data for table `tarifsateliers`
--


LOCK TABLES tarifsateliers WRITE;
INSERT INTO tarifsateliers VALUES (15,'Tarif A',12.22);
INSERT INTO tarifsateliers VALUES (2,'Tarif C',50.25);
INSERT INTO tarifsateliers VALUES (16,'Tarif B',1);
INSERT INTO tarifsateliers VALUES (12,'Tarif D',3.89);
INSERT INTO tarifsateliers VALUES (18,'Tarif E',22.3);
INSERT INTO tarifsateliers VALUES (19,'Tarif o',12.27);
UNLOCK TABLES;

--
-- Table structure for table `tarifscantine`
--

DROP TABLE IF EXISTS tarifscantine;
CREATE TABLE tarifscantine (
  id int(10) unsigned NOT NULL auto_increment,
  tarif_nom varchar(50) NOT NULL default '',
  tarif_prix double default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY id (id),
  KEY id_2 (id)
) TYPE=MyISAM;

--
-- Dumping data for table `tarifscantine`
--


LOCK TABLES tarifscantine WRITE;
INSERT INTO tarifscantine VALUES (3,'Tarif A',4.68);
INSERT INTO tarifscantine VALUES (2,'Tarif E',1.34);
INSERT INTO tarifscantine VALUES (4,'Tarif B',8.23);
INSERT INTO tarifscantine VALUES (5,'Tarif C',2);
INSERT INTO tarifscantine VALUES (8,'Tarif F',222);
UNLOCK TABLES;

