package listbuy.me.listbuy.lista;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Talitadossantoscastr on 06/10/2016.
 */

public class DbCore extends SQLiteOpenHelper {

    private static final String DB_NAME = "LISTA_DE_COMPRAS";
    private static final int DB_VERSION = 4; // toda vez que for alterar algo na tabela, como drop ou inserir sempre mudar a versão senão não funciona

    public DbCore(Context c){
        super(c,DB_NAME,null,DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table produtos_lista(id_lista integer,id_produto integer primary key autoincrement,nome_prod text,quantidade int,unidade_medida)");
        db.execSQL("create table listas_criadas(id_lista integer primary key autoincrement, nome_lista text, data_cricao text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table produtos_lista;");
        db.execSQL("drop table listas_criadas;");
        onCreate(db);
    }


}
