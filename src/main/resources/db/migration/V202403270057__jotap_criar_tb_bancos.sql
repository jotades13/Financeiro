create sequence bnc_id_seq
    START WITH 1;

create table tb_bancos (
    bnc_codigo  bigint not null default nextval('bnc_id_seq'),
    bnc_banco   char(3) not null,
    bnc_nome  	varchar(30),
    bnc_ispb 	varchar(8),
    bnc_usuario varchar(30),
    primary key (bnc_codigo)
);
