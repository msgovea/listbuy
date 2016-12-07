 -- Quantidade de consumidores que logaram com conta facebook ?
		create or replace function fnc_qtd_consumidor_facebook
		return number is
		
		v_qtd_consumidor_f number;
		
		begin
      select count(*) into v_qtd_consumidor_f from consumidor where id_tipo_acesso = 'F';
			return v_qtd_consumidor_f;
		end;