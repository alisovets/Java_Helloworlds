
CREATE DATABASE IF NOT EXISTS `CAR_RENT`; 

drop table if exists `CAR_RENT`.`BILL`;

drop table if exists `CAR_RENT`.`RENTORDER`;

drop table if exists `CAR_RENT`.`CAR`;

drop table if exists `CAR_RENT`.`CLIENT`;

drop table if exists `CAR_RENT`.`USER`;


/*==============================================================*/
/* Table: User                                                   */
/*==============================================================*/
create table `CAR_RENT`.`USER`
(
   USER_ID               int not null AUTO_INCREMENT,
   NAME                 varchar(127) not null,
   PASSWORD             varchar(127) not null,
   primary key (USER_ID)
)
ENGINE = InnoDB;

/*==============================================================*/
/* Table: Car                                                   */
/*==============================================================*/
create table `CAR_RENT`.`CAR`
(
   CAR_ID               int not null AUTO_INCREMENT,
   NAME                 varchar(127) not null,
   MODEL                varchar(127) not null,
   KILOMETRAGE          int not null,
   PRICE                int not null,
   primary key (CAR_ID)
)
ENGINE = InnoDB;

/*==============================================================*/
/* Table: Client                                                */
/*==============================================================*/
create table `CAR_RENT`.`CLIENT`
(
   CLIENT_ID            int not null AUTO_INCREMENT,
   NAME                 varchar(127) not null,
   ADDRESS               varchar(127) not null,
   primary key (CLIENT_ID)
)
ENGINE = InnoDB;

/*==============================================================*/
/* Table: RentOrder                                             */
/*==============================================================*/
create table `CAR_RENT`.`RENTORDER`
(
   RENTORDER_ID         int not null AUTO_INCREMENT,
   CAR_ID               int not null,
   CLIENT_ID            int not null,
   START_DATE           date not null,
   END_DATE             date,
   COST                 int not null,
   STATUS               tinyint not null default 1,
   primary key (RENTORDER_ID),
   constraint FK_fk_order_car foreign key (CAR_ID)
      references `CAR_RENT`.`CAR` (CAR_ID) on delete restrict on update restrict,
   constraint FK_fk_order_client foreign key (CLIENT_ID)
      references `CAR_RENT`.`CLIENT` (CLIENT_ID) on delete restrict on update restrict
)
ENGINE = InnoDB;

/*==============================================================*/
/* Table: Bill                                                  */
/*==============================================================*/
create table `CAR_RENT`.`BILL`
(
   BILL_ID              int not null AUTO_INCREMENT,
   RENTORDER_ID         int not null,
   SUMM                 float(10,2) not null,
   PAY_DATE             date,
   APPROVED             bool,
   STATUS               int default 0,
   primary key (BILL_ID),
   constraint FK_fk_bill_order foreign key (RENTORDER_ID)
      references `CAR_RENT`.`RENTORDER` (RENTORDER_ID) on delete restrict on update restrict
)
ENGINE = InnoDB;


insert into `CAR_RENT`.`USER` (USER_ID, NAME, PASSWORD) VALUES(1, 'user', '123');

insert into `CAR_RENT`.`CAR` (CAR_ID, NAME, MODEL, KILOMETRAGE, PRICE) VALUES(1, 'Lada Red', 'Lada', 1200, 186);
insert into `CAR_RENT`.`CAR` (NAME, MODEL, KILOMETRAGE, PRICE) VALUES('Lada black', 'Lada', 1000, 196);
insert into `CAR_RENT`.`CAR` (NAME, MODEL, KILOMETRAGE, PRICE) VALUES('Lada traspsrent', 'Lada', 800, 211);
insert into `CAR_RENT`.`CAR` (NAME, MODEL, KILOMETRAGE, PRICE) VALUES('Ford yellow', 'Ford', 920, 318);

insert into `CAR_RENT`.`CLIENT` (CLIENT_ID, NAME, ADDRESS) VALUES(1, 'Main Client', "Bankova 1");

insert into `CAR_RENT`.`RENTORDER` (RENTORDER_ID, CAR_ID, CLIENT_ID, START_DATE, COST) VALUES(1, 1, 1, '2011.09.25', 2000 );

/* insert into `CAR_RENT`.`RENTORDER` (CAR_ID, CLIENT_ID, START_DATE, COST) VALUES( 1, 1, '2011.09.25', 2001 ); */


