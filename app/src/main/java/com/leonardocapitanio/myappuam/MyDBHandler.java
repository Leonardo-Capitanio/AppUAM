package com.leonardocapitanio.myappuam;

/**
 * Created by Leonardo on 15/11/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "produtosDB.db";
    public static final String TABELA_PRODUTOS = "produtos";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOMEPRODUTO = "nomeproduto";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, NOME_DB, factory, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String consulta = "CREATE TABLE " + TABELA_PRODUTOS + "(" +
                COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOMEPRODUTO + " TEXT " +
                ");";
        db.execSQL(consulta);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTOS);
        onCreate(db);
    }

    //Adicionar produtos na DB:
    public void adicProduto(Produtos produto)
    {
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOMEPRODUTO, produto.get_nomeproduto());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABELA_PRODUTOS, null, values);
        db.close();
    }

    //Deletar produtos da DB:
    public void deletarProduto(String nomeProduto)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABELA_PRODUTOS + " WHERE " + COLUNA_NOMEPRODUTO + "=\"" + nomeProduto + "\";");
    }

    //Imprimir string da DB:
    public String dbToString()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String consulta = "SELECT * FROM " + TABELA_PRODUTOS + " WHERE 1";

        //Apontar o cursor para a localização do resultado:
        Cursor c = db.rawQuery(consulta, null);
        //Move o cursor para a primeira linha do resultado:
        c.moveToFirst();

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("nomeproduto")) != null)
            {
                dbString += c.getString(c.getColumnIndex("nomeproduto"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }


}
