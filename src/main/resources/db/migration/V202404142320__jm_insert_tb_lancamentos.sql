insert into tb_lancamentos
(
  lan_descricao,lan_contas,lan_status,lan_despesafixa,lan_valor,lan_vencimento,
  lan_tipodocumento,grp_codigo,ccu_codigo,lan_bxdata,
  usu_data_cadastro,usu_cadastro
)
values
('CAERN 01','PAGAR','AVENCER',false,1200,'02/02/2024','BOLETO',1,1,null,'01/01/2024','JOTAHEMMY'),
('CAERN 02','PAGAR','AVENCER',false,460,'02/02/2024','BOLETO',1,1,null,'01/01/2024','JOTAHEMMY')