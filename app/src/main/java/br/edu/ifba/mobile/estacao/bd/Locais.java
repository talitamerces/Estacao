package br.edu.ifba.mobile.estacao.bd;



/**
 * Created by Usuário on 07/07/2016.
 */
public class Locais {

    private long codigo = -1;
    private String nome;
    private String endereco;
    private String telefone;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return nome + ", " + endereco;
    }
}
