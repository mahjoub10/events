
-- create table request --

CREATE TABLE request (
  id INT NOT NULL AUTO_INCREMENT,
  type varchar(255)  DEFAULT NULL,
  status varchar(255)  DEFAULT NULL,
  date DATE  DEFAULT NULL,
  speaker_id  INT NOT NULL,
  PRIMARY KEY (id)
) ;



-- create event speaker table

CREATE TABLE `events_requests` (
  `event_id` bigint(20) NOT NULL,
  `request_id` bigint(20) NOT NULL,
  PRIMARY KEY (`event_id`,`request_id`)
) ;