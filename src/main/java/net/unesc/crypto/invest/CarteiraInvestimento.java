package net.unesc.crypto.invest;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class CarteiraInvestimento {

    private String nome;
    private List<Investimento> investimentos;

    public CarteiraInvestimento(String nome) {
        this.nome = nome;
        this.investimentos = new LinkedList<>();
    }

    public void addInvestimento(Investimento investimento) {
        this.investimentos.add(investimento);
    }

    public String getNome() {
        return nome;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    public BigDecimal getValorInvestido() {
        return investimentos.stream().map(Investimento::getValorInicial)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return nome + ", valor investido: " + getValorInvestido();
    }
}
