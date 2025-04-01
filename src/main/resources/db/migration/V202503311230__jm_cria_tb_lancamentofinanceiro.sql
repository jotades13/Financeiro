create sequence lanfin_id_seq
    START WITH 1;

create table tb_lancamentofinanceiro (
    fin_sequencia        bigint not null default nextval('lanfin_id_seq'),
    fin_descricao        varchar(30),
    fin_valor            numeric(15,2),
    fin_lancamento       date,
    gpf_codigo           integer,
    fin_usuariocadastro  varchar(20),
    fin_datacadastro     date,
    foreign key (gpf_codigo) references tb_grupofinanceiro(gpf_codigo) ON DELETE SET NULL,
    primary key (fin_sequencia)
);


