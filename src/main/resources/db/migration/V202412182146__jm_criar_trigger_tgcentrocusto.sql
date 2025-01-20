create trigger tgcentrocusto
after update
    on
    public.tb_centrocusto
    for each row execute 
    function log_centrocusto()