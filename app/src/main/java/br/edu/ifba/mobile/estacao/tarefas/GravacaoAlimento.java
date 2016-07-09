package br.edu.ifba.mobile.estacao.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.estacao.bd.Alimentos;
import br.edu.ifba.mobile.estacao.bd.FachadaBD;

/**
 * Created by Talita on 08/07/2016.
 */
public class GravacaoAlimento extends AsyncTask<Void, Void, String> {

    private Alimentos alimentos = null;
    private Context contexto = null;

    public GravacaoAlimento(Context contexto, Alimentos alimento){
        this.contexto = contexto;
        this.alimentos = alimento;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        long codigo = -1;
        if(alimentos.getCodigo() == -1){
            codigo = FachadaBD.getInstancia().inserir(alimentos);
        }else {
            codigo = FachadaBD.getInstancia().atualizar(alimentos);
        }

        if(codigo > 0){
            mensagem = "Alimento gravado com sucesso!";
        }else {
            mensagem = "Erro de gravação!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }

}
