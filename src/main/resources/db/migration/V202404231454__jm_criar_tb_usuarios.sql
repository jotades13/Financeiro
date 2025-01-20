create sequence usu_id_seq
    START WITH 1;
  
create table tb_usuarios (
    usu_codigo        bigint not null default nextval('usu_id_seq') ,
    usu_nome          varchar(15) not null,
    usu_nomecompleto  varchar(30),
    usu_senha         varchar(10),
    usu_funcao 	      varchar(15),
    usu_refazsenha    boolean default true,
    primary key (usu_codigo)
);
