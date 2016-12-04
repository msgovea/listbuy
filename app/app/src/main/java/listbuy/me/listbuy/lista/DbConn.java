package listbuy.me.listbuy.lista;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import listbuy.me.listbuy.entities.Consumidor;
import listbuy.me.listbuy.entities.Produtos;

public class DbConn {
    private final SQLiteDatabase db;

    public DbConn(Context c) {
        DbCore dbcore = new DbCore(c);
        db = dbcore.getWritableDatabase();
    }

    public void insertConsumidor(Long idConsumidor, String nome, String email, String senha, String id_tipo_acesso) {
        ContentValues valor = new ContentValues();
        valor.put("id_consumidor", idConsumidor);
        valor.put("nome_consumidor", nome);
        valor.put("email_cosumidor", email);
        valor.put("senha", senha);
        valor.put("id_tipo_acesso", id_tipo_acesso);
        db.insert("consumidor", null, valor);
    }

    public Consumidor selectConsumidor() {

        Cursor cursor = db.query(true, "consumidor", null, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            return new Consumidor(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
        }
        return null;
    }

    public void deleteConsumidor() {
        db.execSQL("delete from consumidor");
    }

}
