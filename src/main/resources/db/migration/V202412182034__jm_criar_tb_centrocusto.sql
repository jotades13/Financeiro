create sequence ccu_id_seq
    START WITH 1;

create table tb_centrocusto (
    ccu_codigo         bigint not null default nextval('ccu_id_seq'),
    ccu_descricao      varchar(30),
    ccu_ativo          boolean,
    ccu_usuario        varchar(20),
    primary key (ccu_codigo)
);


