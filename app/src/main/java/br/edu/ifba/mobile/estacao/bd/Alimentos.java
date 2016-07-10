package br.edu.ifba.mobile.estacao.bd;

/**
 * Created by Talita on 08/07/2016.
 */
public class Alimentos {

    private long codigo = -1;
    private String nome;
    private String valorNutricional;
    private String estacao;
    private long codLocal;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValorNutricional() {
        return valorNutricional;
    }

    public void setValorNutricional(String valorNutricional) {
        this.valorNutricional = valorNutricional;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public long getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(long codLocal) {
        this.codLocal = codLocal;
    }


    @Override
    public String toString() {
        return  nome;
    }
}
