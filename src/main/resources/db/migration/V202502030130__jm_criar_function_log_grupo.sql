CREATE OR REPLACE FUNCTION log_grupo()
 RETURNS trigger
 LANGUAGE plpgsql
AS $$
declare 
   chave bigint;
   modulo varchar(20);
   usuario varchar(20);
   detalhamento text;
begin

   modulo       = 'GRUPOFINANCEIRO';
   usuario      = new.grp_usuario;
   chave        = new.grp_codigo;
   
   detalhamento = 'campo DESCRICAO alterado; Vrl.Anterior: '||old.grp_descricao;
   if new.grp_descricao<>old.grp_descricao then
      INSERT INTO tb_log(log_modulo, log_detalhamento, log_chave, log_lancamento)
      VALUES(modulo,usuario||': '||detalhamento,chave,now());
    end if;	

   detalhamento = 'campo OPERACAO alterado; Vrl.Anterior: '||old.grp_operacao;
   if new.grp_operacao<>old.grp_operacao then
      INSERT INTO tb_log(log_modulo, log_detalhamento, log_chave, log_lancamento)
      VALUES(modulo,usuario||': '||detalhamento,chave,now());
    end if;	
   	
  RETURN NEW;
END;
$$;
