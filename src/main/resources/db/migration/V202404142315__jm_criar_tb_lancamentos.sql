create sequence lan_id_seq
    START WITH 1;

create table tb_lancamentos (
    lan_titulo          bigint not null default nextval('lan_id_seq'),
    lan_documento       varchar(45),
    lan_descricao       varchar(50),
    lan_contas          char(7) not null,
    lan_status          char(9) not null default 'EM_ABERTO',
    lan_despesafixa     boolean,
    lan_valor 	        numeric(15,2),
    lan_vencimento      date,
    lan_observacao      text,
    lan_tipodocumento   varchar(20),
    grp_codigo          integer not null,
    ccu_codigo          integer not null,
    lan_bxdata          date,
    lan_bxusu           varchar(15),
    lan_bxvalor         numeric(15,2),
    lan_bxbanco         char(3),
    lan_bxforma         char(14),
    usu_data_cadastro   date, 
    usu_cadastro        varchar(15),
    usu_data_alteracao  date,
    usu_alteracao       varchar(15),
    foreign KEY (grp_codigo) references tb_grupos (grp_codigo) ON DELETE SET NULL,
    foreign key (ccu_codigo) references tb_centrocusto (ccu_codigo) ON DELETE SET NULL,
    primary key (lan_titulo)
);