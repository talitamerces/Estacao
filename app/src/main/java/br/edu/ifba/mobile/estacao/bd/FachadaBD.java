package br.edu.ifba.mobile.estacao.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuário on 07/07/2016.
 */
public class FachadaBD extends SQLiteOpenHelper{

    private static FachadaBD instancia = null;

    public static FachadaBD criarInstancia(Context context){
        if(instancia == null){
            instancia = new FachadaBD(context);
        }
        return instancia;
    }

    public static FachadaBD getInstancia() {
        return instancia;
    }

    private static String NOME_BANCO = "Alimentos";
    private static int VERSAO_BANCO = 1;

    public FachadaBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    private static String COMANDO_CRIACAO_TABELA_ALIMENTOS =
            "CREATE TABLE ALIMENTOS(" +
                    "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, VALORNUTRICIONAL TEXT, ESTACAO TEXT," +
                    "CODIGOLOCAL INTEGER NOT NULL, "+
                    "FOREIGN KEY(CODIGOLOCAL)REFERENCES LOCAIS(CODIGO))";


    private static String COMANDO_CRIACAO_TABELA_LOCAIS =
            "CREATE TABLE LOCAIS(" +
                    "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOME TEXT,  ENDERECO TEXT, TELEFONE TEXT)";

    public void onCreate(SQLiteDatabase db){
        db.execSQL(COMANDO_CRIACAO_TABELA_LOCAIS);
        db.execSQL(COMANDO_CRIACAO_TABELA_ALIMENTOS);
    }

    public  void onUpgrade(SQLiteDatabase db, int versaoAntiga,
                           int versaoNova){

    }

    public long inserir(Alimentos alimentos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", alimentos.getNome());
        valores.put("VALORNUTRICIONAL", alimentos.getValorNutricional());
        valores.put("ESTACAO", alimentos.getEstacao());
        valores.put("CODIGOLOCAL", alimentos.getCodLocal());


        long codigo = db.insert("ALIMENTOS", null, valores);

        return codigo;
    }

    public long atualizar (Alimentos alimentos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", alimentos.getNome());
        valores.put("VALORNUTRICIONAL", alimentos.getValorNutricional());
        valores.put("CODIGOLOCAL", alimentos.getCodLocal());
        valores.put("ESTACAO", alimentos.getEstacao());

        long codigo = db.update("ALIMENTOS", valores, "CODIGO =  " + alimentos.getCodigo(), null);

        return codigo;
    }

    public int remover(Alimentos alimentos){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("ALIMENTOS", "CODIGO = " +
                alimentos.getCodigo(),
                null);

    }

    public Alimentos procurarAlimentos(long codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Alimentos alimentos = null;

        if(codigo != 0) {
            String selecao = "SELECT CODIGO, NOME, VALORNUTRICIONAL, " +
                    "ESTACAO FROM ALIMENTOS WHERE CODIGO = " + codigo;
            Cursor cursor = db.rawQuery(selecao, null);
            alimentos = new Alimentos();
            if (cursor.moveToFirst()) {
                do {
                    alimentos.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                    alimentos.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                    alimentos.setValorNutricional(cursor.getString(
                            cursor.getColumnIndex("VALORNUTRICIONAL")));
                    alimentos.setEstacao(cursor.getString(cursor.getColumnIndex("ESTACAO")));
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        return alimentos;

    }

    public List<Alimentos> listarAlimentos(){
        List<Alimentos> alimentos=  new ArrayList<Alimentos>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT  CODIGO, NOME, VALORNUTRICIONAL, CODIGOLOCAL,  ESTACAO FROM ALIMENTOS";

        Cursor cursor = db.rawQuery(selecao, null);
        if (cursor != null){
            boolean temProximo = cursor.moveToFirst();
            while(temProximo){
                Alimentos alimento = new Alimentos();
                alimento.setCodigo(
                        cursor.getLong(cursor.getColumnIndex("CODIGO")));
                alimento.setNome(
                        cursor.getString(cursor.getColumnIndex("NOME")));
                alimento.setValorNutricional(
                        cursor.getString(cursor.getColumnIndex("VALORNUTRICIONAL")));
                alimento.setEstacao(
                        cursor.getString(cursor.getColumnIndex("ESTACAO")));
                alimento.setCodLocal(
                        cursor.getLong(cursor.getColumnIndex("CODIGOLOCAL")));
                alimentos.add(alimento);
                temProximo = cursor.moveToNext();

            }
        }
        return alimentos;
    }





    public long inserir(Locais locais){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", locais.getNome());
        valores.put("ENDERECO", locais.getEndereco());
        valores.put("TELEFONE", locais.getTelefone());

        long codigo = db.insert("LOCAIS", null, valores);

        return codigo;
    }

    public long atualizar (Locais locais){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", locais.getNome());
        valores.put("ENDERECO", locais.getEndereco());
        valores.put("TELEFONE", locais.getTelefone());

        long codigo = db.update("LOCAIS", valores, "CODIGO =  " +
                locais.getCodigo(), null);

        return codigo;
    }

    public int remover(Locais locais){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("LOCAIS", "CODIGO = " +
                        locais.getCodigo(),
                null);

    }

    public List<Locais> listarLocais(){
        List<Locais> locais =  new ArrayList<Locais>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT CODIGO, NOME, ENDERECO, TELEFONE FROM LOCAIS";

        Cursor cursor = db.rawQuery(selecao, null);
        if (cursor != null){
            boolean temProximo = cursor.moveToFirst();
            while(temProximo){
                Locais local = new Locais();
                local.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                local.setNome(
                        cursor.getString(cursor.getColumnIndex("NOME")));
                local.setEndereco(
                        cursor.getString(cursor.getColumnIndex("ENDERECO")));
                local.setTelefone(
                        cursor.getString(cursor.getColumnIndex("TELEFONE")));
                locais.add(local);
                temProximo = cursor.moveToNext();

            }
        }
        return locais;
    }


}
