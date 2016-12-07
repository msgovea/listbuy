 -- Quais os ids de ofertas validas ?
		create or replace view id_ofertas_validas as
			select id_oferta from ofertas where ativa = 'Y';