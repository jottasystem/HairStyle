package com.example.hairstyle.CRUD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hairstyle.Pessoa;

import java.util.ArrayList;



public class Read extends SQLiteOpenHelper {

    private static final String NOME_DB = "MEU_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_PESSOA = "TABELA_PESSOA";

    private static final String PATH_DB = "/data/user/0/com.example.hairstyle/databases/MEU_DB";
    private Context mContext;
    private SQLiteDatabase db;


    public Read(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
        this.mContext = context;
        db = getReadableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // logica pra atualiza db
    }


    public ArrayList<Pessoa> getPessoas() {
        openDB();
        ArrayList<Pessoa> pArray = new ArrayList<>();
        String getPessoas = "SELECT * FROM " + TABELA_PESSOA;

        try {
            Cursor c = db.rawQuery(getPessoas, null);

            if (c.moveToFirst()) {
                do {
                    Pessoa p = new Pessoa();
                    p.setNome(c.getString(0));
                    p.setLogin(c.getString(1));
                    p.setPassword(c.getString(2));
                    pArray.add(p);
                } while (c.moveToNext());
                c.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }

        return pArray;
    }


    private void openDB() {
        if (!db.isOpen()) {
            db = mContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }

    }


}
