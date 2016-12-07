create or replace PROCEDURE deleta_produto(p_id_lista IN listas.id_lista%TYPE,
                                           p_id_prod IN listas_x_produtos.id_produto%TYPE)
AS
BEGIN
DELETE FROM listas_x_produtos
WHERE id_lista = p_id_lista AND id_produto = p_id_prod;
END;