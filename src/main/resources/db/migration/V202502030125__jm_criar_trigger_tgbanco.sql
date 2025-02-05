create trigger tgbanco 
after update
    on
    public.tb_bancos 
    for each row execute 
    function log_banco()