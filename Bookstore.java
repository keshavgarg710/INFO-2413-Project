//SQL Database
create database lib;  
use lib;  
CREATE TABLE `books` (
  `BOOK_ID` int(4) NOT NULL,
  `TITLE` varchar(100) NOT NULL,
  `AUTHOR` varchar(100) NOT NULL,
  `PUBLICATION` varchar(100) NOT NULL,
  `EDITION` varchar(100) NOT NULL,
  `YEAR` int(4) NOT NULL
);
SELECT * FROM user;
INSERT INTO `books` (`BOOK_ID`, `TITLE`, `AUTHOR`, `PUBLICATION`, `EDITION`, `YEAR` ) VALUES
(1, 'Core Java Volume I – Fundamentals', 'Cay S. Horstmann', 'Prentice Hall', '11th Edition', 2020 );
CREATE TABLE `user` (
  `ID` int(4) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `EMAILID` varchar(100) DEFAULT NULL
);
INSERT INTO `user` (`ID`, `NAME`, `USERNAME`, `PASSWORD`, `EMAILID`) VALUES
(0, 'Administrator', 'admin', '123', 'admin@gmail.com');

INSERT INTO `books` (`BOOK_ID`, `TITLE`, `AUTHOR`, `PUBLICATION`, `EDITION`, `YEAR` ) VALUES
(2, 'Effective Java', 'Joshua Bloch', 'Addison Wesley', '3rd Edition', 2017 ),
(3, 'Java: A Beginner’s Guide', 'Herbert Schildt', 'McGraw-Hill Education', '8th Edition', 2018 ),
(4, 'Java - The Complete Reference', 'Herbert Schildt', 'McGraw Hill Education', '11th Edition', 2018 ),
(5, 'Head First Java', 'Kathy Sierra & Bert Bates', 'Shroff/O’Reilly', '2nd Edition', 2005 ),
(6, 'Java Concurrency in Practice', 'Brian Goetz with Tim Peierls, Joshua Bloch, Joseph Bowbeer, David Holmes, and Doug Lea', 'Addison-Wesley Professional', '1st Edition', 2006 ),
(7, 'Test-Driven: TDD and Acceptance TDD for Java Developers', 'Lasse Koskela', 'Manning Publications', '1st Edition', 2007 );
