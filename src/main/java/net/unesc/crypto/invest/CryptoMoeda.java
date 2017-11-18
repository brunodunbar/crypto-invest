package net.unesc.crypto.invest;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;

public class CryptoMoeda {

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

    @Override
    public String toString() {
        return nome + " - " + valorCompra;
    }
}
