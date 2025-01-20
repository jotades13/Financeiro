CREATE OR REPLACE FUNCTION log_banco()
 RETURNS trigger
 LANGUAGE plpgsql
AS $$
declare 
   chave bigint;
   modulo varchar(20);
   usuario varchar(20);
   detalhamento text;
begin

   modulo       = 'BANCO';
   usuario      = new.bnc_usuario;
   chave        = new.bnc_codigo;
   
   detalhamento = 'campo NOME alterado; Vrl.Anterior: '||old.bnc_nome;
   if new.bnc_nome<>old.bnc_nome then
      INSERT INTO tb_log(log_modulo, log_detalhamento, log_chave, log_lancamento)
      VALUES(modulo,usuario||': '||detalhamento,chave,now());
    end if;	

   detalhamento = 'campo BANCO alterado; Vrl.Anterior: '||old.bnc_banco;
    if new.bnc_banco<>old.bnc_banco then
      INSERT INTO tb_log(log_modulo, log_detalhamento, log_chave, log_lancamento)
      VALUES(modulo,usuario||': '||detalhamento,chave,now());
    end if;	

   detalhamento = 'campo ISPB alterado; Vrl.Anterior: '||old.bnc_ispb;
   if new.bnc_ispb<>old.bnc_ispb then
      INSERT INTO tb_log(log_modulo, log_detalhamento, log_chave, log_lancamento)
      VALUES(modulo,usuario||': '||detalhamento,chave,now());
    end if;	

  RETURN NEW;
END;
$$;
