 -- Quais usuario que logaram com conta google ?
		create or replace view consumidor_google as
			select id_consumidor, nome, email from consumidor where id_tipo_acesso = 'G';
