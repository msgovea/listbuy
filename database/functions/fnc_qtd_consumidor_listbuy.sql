  -- Quantidade de consumidores que criaram e logaram com conta listbuy ?
		create or replace function fnc_qtd_consumidor_listbuy
		return number is
		
		v_qtd_consumidor_l number;
		
		begin
			select count(*) into v_qtd_consumidor_l from consumidor where id_tipo_acesso = 'N';
		
			return v_qtd_consumidor_l;
		end; 