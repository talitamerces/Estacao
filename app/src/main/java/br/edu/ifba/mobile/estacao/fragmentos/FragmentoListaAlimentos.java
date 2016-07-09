package br.edu.ifba.mobile.estacao.fragmentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.estacao.R;
import br.edu.ifba.mobile.estacao.bd.Alimentos;
import br.edu.ifba.mobile.estacao.tarefas.ListagemAlimentos;
import br.edu.ifba.mobile.estacao.tarefas.RemocaoAlimento;

/**
 * Created by Talita on 08/07/2016.
 */
public class FragmentoListaAlimentos extends Fragment {

    private static FragmentoListaAlimentos instancia = null;

    public static FragmentoListaAlimentos getInstancia(){
        if(instancia == null){
            instancia = new FragmentoListaAlimentos();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;
    private Alimentos alimentos = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_lista_alimentos, vgroup, false);

        preparar();

        return tela;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador){
        super.onCreateOptionsMenu(menu, inflador);

        inflador.inflate(R.menu.menu_estacao, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        long id =item.getItemId();

        if(id != AdapterView.INVALID_ROW_ID){
            if(id == R.id.cadastro_remover){
                RemocaoAlimento remocao = new RemocaoAlimento(this.getContext(), this.getAlimentoSelecionadoExclusao());
                remocao.execute();
                atualizar();
            }
        }

        return super.onOptionsItemSelected(item);
    }



    private void preparar(){
        lista = (ListView) tela.findViewById(R.id.listaAlimentos);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void atualizar(){
        ListagemAlimentos listagemAlimentos = new ListagemAlimentos(this.getContext(), lista);
        listagemAlimentos.execute();
    }

    public Alimentos getAlimentoSelecionadoExclusao(){

        int posicao = lista.getCheckedItemPosition();

        if (posicao != ListView.INVALID_POSITION){
            alimentos = (Alimentos) lista.getItemAtPosition(posicao);
        }

        return alimentos;
    }

    public Alimentos getAlimentoSelecionado(){

        int posicao = lista.getCheckedItemPosition();

        if (posicao != ListView.INVALID_POSITION && alimentos == null){
            alimentos = (Alimentos) lista.getItemAtPosition(posicao);
        }

        return alimentos;
    }

    public void limparAlimentos(){
        alimentos = null;
    }
}
