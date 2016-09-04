# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table campos (
  id                        bigint not null,
  region                    integer,
  constraint ck_campos_region check (region in (0,1,2,3,4)),
  constraint pk_campos primary key (id))
;

create table pozos (
  id                        bigint not null,
  estado                    integer,
  campo_id                  bigint,
  constraint ck_pozos_estado check (estado in (0,1,2,3)),
  constraint pk_pozos primary key (id))
;

create table registros (
  id                        bigint not null,
  sensor_id                 bigint,
  created_at                timestamp,
  medida                    float,
  constraint pk_registros primary key (id))
;

create table sensores (
  id                        bigint not null,
  pozo_id                   bigint,
  tipo                      integer,
  constraint ck_sensores_tipo check (tipo in (0,1,2,3)),
  constraint pk_sensores primary key (id))
;

create sequence Campo;

create sequence Pozo;

create sequence Registro;

create sequence Sensor;

alter table pozos add constraint fk_pozos_campo_1 foreign key (campo_id) references campos (id);
create index ix_pozos_campo_1 on pozos (campo_id);
alter table registros add constraint fk_registros_sensor_2 foreign key (sensor_id) references sensores (id);
create index ix_registros_sensor_2 on registros (sensor_id);
alter table sensores add constraint fk_sensores_pozo_3 foreign key (pozo_id) references pozos (id);
create index ix_sensores_pozo_3 on sensores (pozo_id);



# --- !Downs

drop table if exists campos cascade;

drop table if exists pozos cascade;

drop table if exists registros cascade;

drop table if exists sensores cascade;

drop sequence if exists Campo;

drop sequence if exists Pozo;

drop sequence if exists Registro;

drop sequence if exists Sensor;

