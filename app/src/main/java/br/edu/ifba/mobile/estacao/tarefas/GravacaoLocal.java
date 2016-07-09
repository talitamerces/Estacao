package br.edu.ifba.mobile.estacao.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.estacao.bd.FachadaBD;
import br.edu.ifba.mobile.estacao.bd.Locais;
/**
 * Created by Talita on 08/07/2016.
 */
public class GravacaoLocal extends AsyncTask<Void, Void, String> {

    private Locais local = null;
    private Context contexto = null;

    public GravacaoLocal(Context contexto, Locais local) {
        this.contexto = contexto;
        this.local = local;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        long codigo = -1;
        if (local.getCodigo() == -1) {
            codigo = FachadaBD.getInstancia().inserir(local);
        } else {
            codigo = FachadaBD.getInstancia().atualizar(local);
        }

        if (codigo > 0) {
            mensagem = "Local gravado com sucesso!";
        } else {
            mensagem = "Erro de gravação!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem) {
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}