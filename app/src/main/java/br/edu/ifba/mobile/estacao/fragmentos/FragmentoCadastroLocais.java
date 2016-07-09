package br.edu.ifba.mobile.estacao.fragmentos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.estacao.R;
import br.edu.ifba.mobile.estacao.bd.Alimentos;
import br.edu.ifba.mobile.estacao.bd.Locais;
import br.edu.ifba.mobile.estacao.tarefas.GravacaoLocal;


/**
 * Created by Talita on 08/07/2016.
 */
public class FragmentoCadastroLocais extends Fragment {

    private static FragmentoCadastroLocais instancia = null;

    public static FragmentoCadastroLocais getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoCadastroLocais();
        }
        return instancia;
    }

    private View tela = null;

    private EditText nome = null;
    private EditText endereco = null;
    private EditText telefone = null;
    private Button gravar = null;

    private Locais locais =  null;
    private Alimentos alimento = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_cadastro_locais, vgroup, false);
        preparar();
        return tela;
    }

    private void preparar() {
        nome = (EditText) tela.findViewById(R.id.txtNomeLocais);
        endereco = (EditText) tela.findViewById(R.id.txtEndLocais);
        telefone = (EditText) tela.findViewById(R.id.txtTelLocais);
        gravar = (Button) tela.findViewById(R.id.botaoGravarLocais);

        gravar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GravacaoLocal gravacao = new GravacaoLocal(getContexto(), getLocais());
                gravacao.execute();
                limparCampos();
            }
        });

    }

    public void carregarCampos(){
        nome.setText(locais.getNome());
        endereco.setText(locais.getEndereco());
        telefone.setText(locais.getTelefone());
    }

    public void limparCampos(){
        nome.setText("");
        endereco.setText("");
        telefone.setText("");
    }

    public Context getContexto(){
        return this.getContext();
    }

    public Locais getLocais(){
        if(locais==null){
            locais = new Locais();
        }

        locais.setNome(nome.getText().toString());
        locais.setEndereco(endereco.getText().toString());
        locais.setTelefone(telefone.getText().toString());

        return locais;
    }

    public void exibirLocaisSelecionado(){
       alimento = FragmentoListaAlimentos.getInstancia().getAlimentoSelecionado();


        if(locais != null){
            if(alimento.getCodigo()== -1){
                limparCampos();
            }else{
                carregarCampos();
            }
        }else{
            limparCampos();
        }

    }




}

