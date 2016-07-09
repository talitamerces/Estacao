package br.edu.ifba.mobile.estacao.fragmentos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import br.edu.ifba.mobile.estacao.R;
import br.edu.ifba.mobile.estacao.bd.Alimentos;
import br.edu.ifba.mobile.estacao.bd.FachadaBD;
import br.edu.ifba.mobile.estacao.bd.Locais;
import br.edu.ifba.mobile.estacao.tarefas.GravacaoAlimento;

/**
 * Created by Talita on 08/07/2016.
 */
public class FragmentoCadastroAlimentos extends Fragment {

    private static FragmentoCadastroAlimentos instancia = null;

    public static FragmentoCadastroAlimentos getInstancia(){
        if(instancia == null){
            instancia = new FragmentoCadastroAlimentos();
        }
        return instancia;
    }

    private View tela = null;

    private EditText nome = null;
    private EditText valorNutricional = null;
    private Spinner listaLocais = null;
    private EditText estacao = null;
    private long codigoSelecionado;
    private Button gravar = null;

    private Alimentos alimentos = null;
    private Locais locais = null;


    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_cadastro_alimentos, vgroup, false);
        preparar();
        return tela;
    }

    private void preparar(){
        nome = (EditText) tela.findViewById(R.id.txtNome);
        valorNutricional = (EditText) tela.findViewById(R.id.txtValNutri);
        estacao = (EditText) tela.findViewById(R.id.txtEstacaoAlimentos);
        listaLocais = (Spinner) tela.findViewById(R.id.spinLocaisAlimentos);
        List<Locais> locais =  FachadaBD.getInstancia().listarLocais();
        ArrayAdapter<Locais> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, locais);
        listaLocais.setAdapter(adapter);


        listaLocais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Locais localSelecionado = (Locais) parent.getItemAtPosition(position);
                setCodigoSelecionado(localSelecionado.getCodigo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gravar = (Button) tela.findViewById(R.id.botaoGravarAlimentos);

        gravar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                GravacaoAlimento gravacao = new GravacaoAlimento(getContexto(), getAlimentos());
                gravacao.execute();
                limparCampos();

            }
        });



    }

    public long getCodigoSelecionado() {
        return codigoSelecionado;
    }

    public void setCodigoSelecionado(long codigoSelecionado) {
        this.codigoSelecionado = codigoSelecionado;
    }


    public Context getContexto(){
        return this.getContext();
    }

    public Alimentos getAlimentos(){

        if(alimentos == null){
            alimentos = new Alimentos();
        }
        alimentos.setNome(nome.getText().toString());
        alimentos.setValorNutricional(valorNutricional.getText().toString());
        alimentos.setEstacao(estacao.getText().toString());
        alimentos.setCodLocal(getCodigoSelecionado());

        return alimentos;
    }

    public void exibiAlimentosSelecionados(){
        alimentos = FragmentoListaAlimentos.getInstancia().getAlimentoSelecionado();

        if(alimentos != null){
            if(alimentos.getCodigo() == -1){
                limparCampos();
            }else {
                carregarCampos(alimentos);
            }
        }else{
            limparCampos();
        }

    }

    public void limparCampos(){
        nome.setText("");
        valorNutricional.setText("");
        estacao.setText("");
    }

    public void carregarCampos(Alimentos alimentos){
        nome.setText(alimentos.getNome());
        valorNutricional.setText(alimentos.getValorNutricional());
        estacao.setText(alimentos.getEstacao());
    }

}
