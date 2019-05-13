package com.example.hairstyle.CRUD;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Create extends SQLiteOpenHelper {

    private static final String NOME_DB = "MEU_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_PESSOA = "TABELA_PESSOA";

    private static final String PATH_DB = "/data/user/0com.example.hairstyle/databases/MEU_DB";
    private Context mContext;
    private SQLiteDatabase db;


    public Create(Context context) {
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


    public boolean createTable() {
        openDB();
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABELA_PESSOA + " ("
                + "LOGIN TEXT,"
                + "PASSWORD TEXT,"
                + "NOME TEXT)";
        try {
            db.execSQL(createTable);
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
