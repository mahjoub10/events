
-- create table use --

CREATE TABLE event (
  id INT NOT NULL AUTO_INCREMENT,
  name varchar(255)  DEFAULT NULL,
  speaker varchar(255)  DEFAULT NULL,
  subject varchar(255)  DEFAULT NULL,
  address varchar(255)  DEFAULT NULL,
  start DATE  DEFAULT NULL,
  PRIMARY KEY (id),
) ;