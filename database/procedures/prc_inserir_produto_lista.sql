create or replace PROCEDURE inserir_produto_lista (p_id_lista IN listas.id_lista%TYPE,
                                                   p_id_produto IN produtos.id_produto%TYPE,
                                                   p_quantidade IN listas_x_produtos.quantidade%TYPE,
                                                   p_id_unidade_medida IN unidade_medida.id_unidade_medida%TYPE,
                                                   p_tipo_lista IN listas.tipo_lista%TYPE,
                                                   p_id_consumidor IN eventos_x_produtos.id_consumidor_assign%TYPE)
AS
BEGIN
  IF p_tipo_lista = 'P' THEN
    INSERT INTO listas_x_produtos(id_lista,id_produto,quantidade,id_unidade_medida)
    VALUES (p_id_lista,p_id_produto,p_quantidade,p_id_unidade_medida);
  ELSE
    INSERT INTO eventos_x_produtos(id_lista,id_produto,id_consumidor_assign,quantidade,id_unidade_medida)
    VALUES (p_id_lista,p_id_produto,p_id_consumidor,p_quantidade,p_id_unidade_medida);
  END IF;
END;