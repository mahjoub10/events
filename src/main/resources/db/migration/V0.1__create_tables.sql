


-- create table user --

CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  email varchar(255)  DEFAULT NULL,
  password varchar(255)  DEFAULT NULL,
  authority varchar(255)  DEFAULT NULL,
  activated  BOOLEAN NOT NULL DEFAULT 1,
  deleted   BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
) ;

-- create table event --

CREATE TABLE event (
  id INT NOT NULL AUTO_INCREMENT,
  name varchar(255)  DEFAULT NULL,
  subject varchar(255)  DEFAULT NULL,
  start DATE  DEFAULT NULL,
  description  varchar(1000)  DEFAULT NULL,
  PRIMARY KEY (id)
) ;


--  create table attendee.

CREATE TABLE attendee (
  id INT NOT NULL AUTO_INCREMENT,
  first_name varchar(255)  DEFAULT NULL,
  last_name varchar(255)  DEFAULT NULL,
  PRIMARY KEY (id)
) ;


--  create table speaker.

CREATE TABLE speaker (
  id INT NOT NULL AUTO_INCREMENT,
  first_name varchar(255)  DEFAULT NULL,
  last_name varchar(255)  DEFAULT NULL,
  mobile varchar(255)  DEFAULT NULL,
  PRIMARY KEY (id)
) ;


-- create event attendee table

CREATE TABLE `events_attendees` (
  `event_id` bigint(20) NOT NULL,
  `attendee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`event_id`,`attendee_id`)
);


-- create event speaker table

CREATE TABLE `events_speakers` (
  `event_id` bigint(20) NOT NULL,
  `speaker_id` bigint(20) NOT NULL,
  PRIMARY KEY (`event_id`,`speaker_id`)
) ;