drop table if exists PRODUCT;

create table PRODUCT (
  ID int not null AUTO_INCREMENT,
  NAME varchar(100) not null,
  min_age long,
  max_age long,
  min_income long,
  max_income long,
  for_student bool,
  PRIMARY KEY ( ID )
);