 -- Quais são esses consumidores inativos ?
		create or replace view consumidores_inativos as
			select id_consumidor, nome, email from consumidor where id_tipo_acesso = 'A';
