 -- Quantidade de consumidores que logaram com conta google ?
		create or replace function fnc_qtd_consumidor_google
		return number is
		
		v_qtd_consumidor_g number;
		
		begin
			select count(*) into v_qtd_consumidor_g from consumidor where id_tipo_acesso = 'G';
			return v_qtd_consumidor_g;
		end;