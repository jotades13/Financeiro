create sequence log_id_seq
START WITH 1;

create table tb_log(
  log_sequencia bigint not null default nextval('log_id_seq'),
  log_modulo varchar(15),
  log_chave int,
  log_detalhamento text,
  log_datahora timestamp
);