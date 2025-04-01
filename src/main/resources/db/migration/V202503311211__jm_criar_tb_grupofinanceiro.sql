create sequence gpf_id_seq
    START WITH 1;

create table tb_grupofinanceiro(
    gpf_codigo          bigint not null default nextval('gpf_id_seq'),
    gpf_descricao       varchar(30),
    primary key (gpf_codigo)
);


