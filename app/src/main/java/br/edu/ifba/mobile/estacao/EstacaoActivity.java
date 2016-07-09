package br.edu.ifba.mobile.estacao;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import br.edu.ifba.mobile.estacao.bd.FachadaBD;
import br.edu.ifba.mobile.estacao.fragmentos.FragmentoCadastroAlimentos;
import br.edu.ifba.mobile.estacao.fragmentos.FragmentoCadastroLocais;
import br.edu.ifba.mobile.estacao.fragmentos.FragmentoInformacao;
import br.edu.ifba.mobile.estacao.fragmentos.FragmentoListaAlimentos;

public class EstacaoActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacao);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mViewPager.addOnPageChangeListener(this);

        FachadaBD.criarInstancia(this.getApplicationContext());

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 1){
            FragmentoListaAlimentos.getInstancia().atualizar();
            FragmentoListaAlimentos.getInstancia().limparAlimentos();
        }else if (position == 2){
            FragmentoCadastroAlimentos.getInstancia().exibiAlimentosSelecionados();
        }else if (position == 3){
            FragmentoCadastroLocais.getInstancia().exibirLocaisSelecionado();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment frag = null;

            switch (position) {
                case 0:
                    frag = FragmentoInformacao.getInstancia();
                    break;
                case 1:
                    frag = FragmentoListaAlimentos.getInstancia();
                    break;
                case 2:
                    frag = FragmentoCadastroAlimentos.getInstancia();
                    break;
                case 3:
                    frag = FragmentoCadastroLocais.getInstancia();
                    break;
            }
            return frag;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Informações";
                case 1:
                    return "Alimentos";
                case 2:
                    return "Cadastro Alimentos";
                case 3:
                    return "Cadastro Locais";
            }
            return null;
        }

    }
}
