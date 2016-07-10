package br.edu.ifba.mobile.estacao.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.estacao.bd.Alimentos;
import br.edu.ifba.mobile.estacao.bd.FachadaBD;

/**
 * Created by Talita on 08/07/2016.
 */
public class RemocaoAlimento extends AsyncTask<Void, Void, String> {

    private Context contexto = null;
    private Alimentos alimento = null;

    public RemocaoAlimento(Context contexto, Alimentos alimento){
        this.contexto = contexto;
        this.alimento = alimento;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(alimento.getCodigo() != -1){
            if(FachadaBD.getInstancia().remover(alimento) == 0){
                mensagem = "Problemas com a remoção!";
            }else {
                mensagem = "Alimento removido!";
            }
        }else {
            mensagem = "Selecione um alimento!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }

}
