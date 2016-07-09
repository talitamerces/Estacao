package br.edu.ifba.mobile.estacao.fragmentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ifba.mobile.estacao.R;

/**
 * Created by Talita on 08/07/2016.
 */
public class FragmentoInformacao extends Fragment{

    private static FragmentoInformacao instancia = null;

    public static FragmentoInformacao getInstancia(){
        if(instancia == null){
            instancia = new FragmentoInformacao();
        }
        return instancia;
    }

    private View tela = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_informacao, vgroup, false);
        return tela;
    }

}
