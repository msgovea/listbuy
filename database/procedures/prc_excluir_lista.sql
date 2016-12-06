create or replace PROCEDURE excluir_lista(p_id_lista listas.id_lista%TYPE,
                                          p_tipo_lista listas.tipo_lista%TYPE)
AS
BEGIN 
  IF p_tipo_lista = 'P' THEN
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