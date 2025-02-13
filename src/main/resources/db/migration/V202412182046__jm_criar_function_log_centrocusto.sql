CREATE OR REPLACE FUNCTION log_centrocusto()
 RETURNS trigger
 LANGUAGE plpgsql
AS $$
declare 
   chave bigint;
   modulo varchar(20);
   usuario varchar(20);
   detalhamento text;
begin

   modulo       = 'CENTROCUSTO';
   usuario      = new.ccu_usuario;
   chave        = new.ccu_codigo;
   
   detalhamento = 'campo DESCRICAO alterado; Vrl.Anterior: '||old.ccu_descricao;
   if new.ccu_descricao<>old.ccu_descricao then
      INSERT INTO tb_log(log_modulo, log_detalhamento, log_chave, log_lancamento)
      VALUES(modulo,usuario||': '||detalhamento,chave,now());
    end if;	
   
   detalhamento = 'DESATIVADO alterado; Vrl.Anterior: '||old.ccu_desativado;
   if new.ccu_desativado<>old.ccu_desativado then
      INSERT INTO tb_log(log_modulo, log_detalhamento, log_usuario, log_chave, log_lancamento)
      VALUES(modulo,usuario||': '||detalhamento,chave,now());
    end if;	

  RETURN NEW;
END;
$$;
