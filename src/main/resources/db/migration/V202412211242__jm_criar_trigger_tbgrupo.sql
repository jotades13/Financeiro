create trigger tggrupo
after update
    on
    public.tb_grupos 
    for each row execute 
    function log_grupo()