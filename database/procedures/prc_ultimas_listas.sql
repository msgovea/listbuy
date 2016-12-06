/* Listas id_lista, data_criacao, id_usuario e email_usuario das 
    ultimas 5 listas criadas. */
   
   create or replace procedure prc_ultimas_listas(pi_n_num number) as 
		cursor c_list_cursor is select l.data_ics,
									   l.id_lista, 									   
									   c.nome 
								from listas l, 
								     consumidor c
								where l.id_consumidor = c.id_consumidor
								order by l.data_ics desc;
		
		v_n_count number := 1;
		v_l_data listas.data_ics%type;
		v_l_lista listas.id_lista%type;
		v_l_consumidor consumidor.nome%type;
		
		begin
			dbms_output.put_line('Ultimas '|| pi_n_num || 'listas criadas: ');
			open c_list_cursor;
				loop
					fetch c_list_cursor into v_l_data, v_l_lista, v_l_consumidor;
					exit when c_list_cursor%notfound or v_n_count > pi_n_num;
					v_n_count := v_n_count + 1;
					dbms_output.put_line('Data de criacao: '|| v_l_data || ' Lista: '|| v_l_lista || ' Criada pelo consumidor: '|| v_l_consumidor);
				end loop;
			close c_list_cursor;
		end;