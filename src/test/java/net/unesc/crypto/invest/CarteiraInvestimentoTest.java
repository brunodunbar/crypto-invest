package net.unesc.crypto.invest;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CarteiraInvestimentoTest {


    @Test
    public void deveAdicionarInvestimento() {

        CarteiraInvestimento carteiraInvestimento = new CarteiraInvestimento("Teste");
        Investimento investimento = mock(Investimento.class);

        carteiraInvestimento.addInvestimento(investimento);

        assertEquals(1, carteiraInvestimento.getInvestimentos().size());
        assertEquals(investimento, carteiraInvestimento.getInvestimentos().get(0));
    }


}