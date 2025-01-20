create sequence grp_id_seq
    START WITH 1;

create table tb_grupos (
    grp_codigo         bigint not null default nextval('grp_id_seq'),
    grp_descricao      varchar(30),
    grp_operacao       char(7),
    grp_usuario        varchar(20),
    primary key (grp_codigo)
);


