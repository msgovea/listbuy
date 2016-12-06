CREATE OR REPLACE FUNCTION quantidade_itens(
    p_id_lista   IN listas.id_lista%TYPE,
    p_tipo_lista IN listas.tipo_lista%TYPE)
  RETURN INTEGER
AS
  qtd_itens INTEGER;
BEGIN
  IF p_tipo_lista = 'P' THEN
    SELECT COUNT(quantidade)
    INTO qtd_itens
    FROM listas_x_produtos
    WHERE id_lista = p_id_lista;
  ELSE
    SELECT COUNT(quantidade)
    INTO qtd_itens
    FROM eventos_x_produtos
    WHERE id_lista = p_id_lista;
  END IF;
RETURN(qtd_itens);
END;