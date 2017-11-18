package net.unesc.crypto.invest;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;

public class CryptoMoeda {

    private static final BigDecimal CEM = new BigDecimal("100");

    private String nome;
    private BigDecimal valorCompra;
    private Map<YearMonth, BigDecimal> variacaoPorMes;

    public CryptoMoeda(String nome, BigDecimal valorCompra) {
        this.nome = nome;
        this.valorCompra = valorCompra;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public Map<YearMonth, BigDecimal> getVariacaoPorMes() {
        return variacaoPorMes;
    }

    public BigDecimal getValorMes(BigDecimal valorInicial, YearMonth inicioInvestimento, YearMonth mes) {
        while(inicioInvestimento.isBefore(mes)) {

            BigDecimal variacao = variacaoPorMes.getOrDefault(inicioInvestimento, BigDecimal.ZERO);
            if(!variacao.equals(BigDecimal.ZERO)) {
                valorInicial = valorInicial.add(valorInicial.multiply(variacao.divide(CEM)));
            }

            inicioInvestimento = inicioInvestimento.plusMonths(1);
        }

        return valorInicial;
    }

    @Override
    public String toString() {
        return nome + " - " + valorCompra;
    }
}
