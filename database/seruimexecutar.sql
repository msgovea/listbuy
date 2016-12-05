drop table "GRUPO1"."LISTAS_X_PRODUTOS"  cascade constraints 


SELECT * 
  FROM PRODUTOS PROD, 
       LISTAS_X_PRODUTOS PL,
       PRODUTO_CADASTRADO PC
 WHERE PROD.ID_PRODUTO = PL.ID_PRODUTO
   AND PROD.ID_PRODUTO = PC.ID_PRODUTO;
   
   
select * from produtos;
select * from listas_x_produtos;
select * from produto_cadastrado;



INSERT INTO grupo1.LISTAS_X_PRODUTOS VALUES (1, 1, 1, 1);
insert into produto_cadastrado values (1, 1,sysdate);

SELECT
    O.OBJECT_NAME,
    S.SID,
    S.SERIAL#,
    P.SPID,
    S.PROGRAM,
    SQ.SQL_FULLTEXT,
    S.LOGON_TIME
FROM
    V$LOCKED_OBJECT L,
    DBA_OBJECTS O,
    V$SESSION S,
    V$PROCESS P,
    V$SQL SQ
WHERE
    L.OBJECT_ID = O.OBJECT_ID
    AND L.SESSION_ID = S.SID
    AND S.PADDR = P.ADDR
    AND S.SQL_ADDRESS = SQ.ADDRESS;
    
    
    alter system kill session '109,6033';
    alter system kill session '15,3933';
    alter system kill session '107,963';