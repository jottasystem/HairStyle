package com.example.hairstyle.CRUD;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hairstyle.Pessoa;

public class Insert extends SQLiteOpenHelper {

    private static final String NOME_DB = "MEU_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_PESSOA = "TABELA_PESSOA";

    private static final String PATH_DB = "/data/user/0/com.example.hairstyle/databases/MEU_DB";
    private Context mContext;
    private SQLiteDatabase db;


    public Insert(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
        this.mContext = context;
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // logica pra atualiza db
    }

    public boolean insertPessoa(Pessoa p) {
        System.out.println("PESSOAAA"+p.getLogin());
        System.out.println("PESSOAAA"+p.getNome());
        System.out.println("PESSOAAA"+p.getPassword());

        openDB();
        try {
            ContentValues cv = new ContentValues();
            cv.put("NOME", p.getNome());
            cv.put("LOGIN", p.getLogin());
            cv.put("PASSWORD", p.getPassword());
            db.insert(TABELA_PESSOA, null, cv);
            System.out.println("VALUUUESSS"+cv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }


    private void openDB() {
        if (!db.isOpen()) {
            db = mContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }

    }


}


