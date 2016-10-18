package listbuy.me.listbuy.lista;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Talitadossantoscastr on 06/10/2016.
 */

public class DbConn {
   private final SQLiteDatabase db;

    public DbConn(Context c){
        DbCore dbcore = new DbCore(c);
        db = dbcore.getWritableDatabase();
    }
    public void insertListaProd(int id_lista,int id_produto, String nome_produto, int qtd, String un_medida){
        ContentValues valor = new ContentValues();
        valor.put("id_lista",id_lista);
        valor.put("id_produto",id_produto);
        valor.put("nome_prod",nome_produto);
        valor.put("quantidade",qtd);
        valor.put("unidade_medida",un_medida);
        db.insert("produtos_lista",null,valor);
    }
    private void insertListas(int id_lista,String nome_lista, String data_criacao){
        ContentValues valor = new ContentValues();
        valor.put("id_lista",id_lista);
        valor.put("nome_lista",nome_lista);
        valor.put("data_cricao",data_criacao);
        db.insert("listas_criadas",null,valor);
    }

    public void deleteListaprod(int id){
       db.delete("produtos_lista","id_lista ="+id,null);
    }
    public void deleteLista(int id){
        db.delete("listas_criadas","id_lista = "+id,null);
    }
    public ArrayList<Produtos> selectProdLista(){
        ArrayList<Produtos> prodLista = new ArrayList<Produtos>();
        // antes tinha o id da lista e o id do produto
        String[] colunas_db = new String[]{"nome_prod","quantidade","unidade_medida"};// dentro do parenteses coloca todas as colunas a serem retornadas
        Cursor cursor = db.query("produtos_lista",colunas_db,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                prodLista.add(new Produtos(cursor.getString(0),cursor.getInt(2),cursor.getString(3)));

            }while(cursor.moveToNext());
        }
        return prodLista;
    }
    public ArrayList<ListasCriadas> selectListasCriadas(){
        ArrayList<ListasCriadas> listas = new ArrayList<ListasCriadas>();

        String[] colunas_db = new String[]{"nome_lista","data_cricao"};
        Cursor cursor  = db.query("listas_criadas",colunas_db,null,null,null,null,null);

        if(cursor.moveToFirst()){

            do{
                listas.add(new ListasCriadas(cursor.getString(0),cursor.getString(1)));
            }while(cursor.moveToNext());

        }

            return listas;
    }
    public void insereProdutos(Produtos prod){
        ContentValues valor = new ContentValues();
        //valor.put("id_lista",prod.getIdLista().getId());
        //valor.put("id_produto",prod.getIdprodutos());
        valor.put("nome_prod",prod.getDescricao());
        valor.put("quantidade",prod.getQtd());
        valor.put("unidade_medida",prod.getUnMed());
        db.insert("produtos_lista",null,valor);
    }
    public void insereListas(ListasCriadas lc){
        ContentValues valor = new ContentValues();
        //valor.put("id_lista",lc.getId());
        valor.put("nome_lista",lc.getNomeLista());
        valor.put("data_cricao",lc.getDataCriacao());
        db.insert("listas_criadas",null,valor);
    }
    public ListasCriadas selectIdLista(String nome){

        String[] colunas_db = new String[]{"id_lista","nome_lista","data_cricao"};
        Cursor cursor  = db.query(true,"listas_criadas", colunas_db, "nome_lista = '"+nome+"'", null,null, null, null,null);

        if(cursor.moveToFirst()){

            do{
                return new ListasCriadas(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            }while(cursor.moveToNext());

        }
        return null;
    }
    public ArrayList<Produtos> selectProdId(ListasCriadas lc){
        ArrayList<Produtos> prodLista = new ArrayList<Produtos>();
        // antes tinha o id da lista e o id do produto
        String[] colunas_db = new String[]{"nome_prod","quantidade","unidade_medida"};// dentro do parenteses coloca todas as colunas a serem retornadas
        Cursor cursor = db.query(true,"produtos_lista",colunas_db,"id_lista  ="+lc.getId(),null,null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                prodLista.add(new Produtos(cursor.getString(0),cursor.getInt(1),cursor.getString(2)));

            }while(cursor.moveToNext());
        }
        return prodLista;
    }
    public void deleteListaId(ListasCriadas lc){
        db.delete("listas_criadas","id_lista  ="+lc.getId(),null);
    }
    public void deleteListaprodIdLista(ListasCriadas lc){
        db.delete("produtos_lista","id_lista  ="+lc.getId(),null);
    }
    public void deleteProdNome(String nome){
        db.delete("produtos_lista","nome_prod ="+nome,null);
    }

    public void insereProdutosArray(ArrayList<Produtos> prod){
        int i = 0;
        while(i<prod.size()) {
            ContentValues valor = new ContentValues();
            valor.put("id_lista",prod.get(i).getIdLista().getId());
            //valor.put("id_produto",prod.getIdprodutos());
            valor.put("nome_prod", prod.get(i).getDescricao());
            valor.put("quantidade", prod.get(i).getQtd());
            valor.put("unidade_medida", prod.get(i).getUnMed());
            db.insert("produtos_lista", null, valor);
            i++;
        }
    }

    public void insereNovaLista(ArrayList<Produtos> prod,String nome){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        String dateString = sdf.format(date);
        int id=0;
        ContentValues valor = new ContentValues();
        //valor.put("id_lista",lc.getId());
        valor.put("nome_lista",nome);
        valor.put("data_cricao",dateString);
        db.insert("listas_criadas",null,valor);

        String[] colunas_db = new String[]{"id_lista"};
        Cursor cursor  = db.query(true,"listas_criadas", colunas_db, "nome_lista = '"+nome+"'", null,null, null, null,null);

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(0);
            }while(cursor.moveToNext());
        }

        int i = 0;
        while(i<prod.size()) {
            ContentValues valor2 = new ContentValues();
            valor2.put("id_lista",id);
            //valor.put("id_produto",prod.getIdprodutos());
            valor2.put("nome_prod", prod.get(i).getDescricao());
            valor2.put("quantidade", prod.get(i).getQtd());
            valor2.put("unidade_medida", prod.get(i).getUnMed());
            db.insert("produtos_lista", null, valor2);
            i++;
        }
    }
    public Produtos selectIdProd(String nome){

        String[] colunas_db = new String[]{"nome_prod","quantidade","unidade_medida"};
        Cursor cursor  = db.query(true,"produtos_lista", colunas_db, "nome_prod = '"+nome+"'", null,null, null, null,null);

        if(cursor.moveToFirst()){

            do{
                return new Produtos(cursor.getString(0),cursor.getInt(1),cursor.getString(2));
            }while(cursor.moveToNext());

        }
        return null;
    }

}
