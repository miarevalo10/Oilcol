# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table campoentity (
  id                        bigint not null,
  constraint pk_campoentity primary key (id))
;

create table pozo (
  id                        bigint not null,
  estado                    integer,
  constraint ck_pozo_estado check (estado in (0,1,2,3)),
  constraint pk_pozo primary key (id))
;

create table sensor (
  id                        bigint not null,
  created_at                timestamp,
  updated_at                timestamp,
  pozo_id                   bigint,
  caudal                    double,
  consumo_energia           double,
  temperatura               double,
  constraint pk_sensor primary key (id))
;

create table usuarioentity (
  id                        bigint not null,
  nombre                    varchar(255),
  tipo                      integer,
  constraint ck_usuarioentity_tipo check (tipo in (0,1)),
  constraint pk_usuarioentity primary key (id))
;

create sequence Campo;

create sequence Pozo;

create sequence Sensor;

create sequence Usuario;

alter table sensor add constraint fk_sensor_pozo_1 foreign key (pozo_id) references pozo (id) on delete restrict on update restrict;
create index ix_sensor_pozo_1 on sensor (pozo_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists campoentity;

drop table if exists pozo;

drop table if exists sensor;

drop table if exists usuarioentity;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists Campo;

drop sequence if exists Pozo;

drop sequence if exists Sensor;

drop sequence if exists Usuario;

