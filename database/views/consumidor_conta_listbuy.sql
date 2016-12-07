 -- Quais os usuario que criaram conta ListBuy
		create or replace view consumidor_conta_lisbuy as
			select id_consumidor, nome, email from consumidor where id_tipo_acesso = 'N';
