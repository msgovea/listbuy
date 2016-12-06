 -- Quantidade de consumidores inativos ?
		create or replace function fnc_qtd_consumidor_inativos
		return number is
		
		v_qtd_inativos number;
		
		begin
			select count(*) into v_qtd_inativos from consumidor where id_tipo_acesso = 'A';

			return v_qtd_inativos;
		end;