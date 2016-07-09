package br.edu.ifba.mobile.estacao.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.estacao.bd.FachadaBD;
import br.edu.ifba.mobile.estacao.bd.Alimentos;

/**
 * Created by Talita on 08/07/2016.
 */
public class ListagemAlimentos extends AsyncTask<Void, Void, List<Alimentos>> {

    private Context contexto = null;
    private ListView listaAlimentos = null;

    public ListagemAlimentos(Context contexto, ListView listaAlimentos){
        this.contexto = contexto;
        this.listaAlimentos = listaAlimentos;
    }

    @Override
    protected List<Alimentos> doInBackground(Void... params) {
        List<Alimentos> alimentos = FachadaBD.getInstancia().listarAlimentos();

        return alimentos;
    }

    @Override
    protected void onPostExecute(List<Alimentos> alimentos){
        if(alimentos.isEmpty()){
            Toast.makeText(contexto, "Lista vazia. Cadastre uma alimento!", Toast.LENGTH_LONG).show();
        }else {
            ArrayAdapter<Alimentos> adapter = new ArrayAdapter<Alimentos>(contexto, android.R.layout.simple_list_item_single_choice, alimentos);
            listaAlimentos.setAdapter(adapter);
        }
    }
}
