
-- create table request --

CREATE TABLE request (
  id INT NOT NULL AUTO_INCREMENT,
  type varchar(255)  DEFAULT NULL,
  status varchar(255)  DEFAULT NULL,
  date DATE  DEFAULT NULL,
  speaker_id  INT NOT NULL,
  PRIMARY KEY (id)
) ;

