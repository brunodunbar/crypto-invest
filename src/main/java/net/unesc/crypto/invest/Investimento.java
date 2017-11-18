package net.unesc.crypto.invest;

import java.math.BigDecimal;
import java.time.YearMonth;

public class Investimento {

    private CryptoMoeda moeda;
    private BigDecimal valorInicial;

    public Investimento(CryptoMoeda moeda, BigDecimal valorInicial) {
        this.moeda = moeda;
        this.valorInicial = valorInicial;
    }

    public CryptoMoeda getMoeda() {
        return moeda;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public BigDecimal getValorMes(YearMonth inicioInvestimento,  YearMonth mes) {
        return moeda.getValorMes(valorInicial, inicioInvestimento, mes);
    }

    @Override
    public String toString() {
        return moeda + ", valor inicial: " + valorInicial;
    }
}
