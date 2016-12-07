CREATE OR REPLACE PROCEDURE deleta_oferta(
    p_id_oferta ofertas.id_oferta%TYPE)
AS
BEGIN
  UPDATE ofertas SET ativa = 'N' WHERE id_oferta = p_id_oferta;
END;