create or replace PROCEDURE prc_excluir_lista(p_id_lista listas.id_lista%TYPE)
AS
v_tipoLista VARCHAR2(1);
BEGIN 
  select TIPO_LISTA INTO v_tipoLista from listas where id_lista = p_id_lista;
  IF v_tipoLista = 'P' THEN
    DELETE FROM listas_x_produtos
    WHERE id_lista = p_id_lista;
  ELSE
    DELETE FROM eventos_x_produtos
    WHERE id_lista = p_id_lista;
  END IF;
  UPDATE listas
  SET ativa = 'N'
  WHERE id_lista = p_id_lista;
END;