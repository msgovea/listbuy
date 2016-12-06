 -- Quais são os usuario que logaram com conta facebook ?
		create or replace view consumidor_facebook as
			select id_consumidor, nome from consumidor where id_tipo_acesso = 'F';	
