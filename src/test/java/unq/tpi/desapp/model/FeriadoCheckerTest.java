package unq.tpi.desapp.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class FeriadoCheckerTest {

    @Test
    public void testEsFeriado(){
        FeriadoChecker feriadoChecker = new FeriadoChecker(2019);
        LocalDateTime feriado9DeJulio = LocalDateTime.of(2019, 7, 9, 12, 0);
        assertTrue(feriadoChecker.esFeriado(feriado9DeJulio));
    }

    @Test
    public void testNoEsFeriado(){
        FeriadoChecker feriadoChecker = new FeriadoChecker(2019);
        LocalDateTime diaHabil = LocalDateTime.of(2019, 9, 18, 12, 0);
        assertFalse(feriadoChecker.esFeriado(diaHabil));
    }

    @Test
    public void testEsFinDeSemana(){
        FeriadoChecker feriadoChecker = new FeriadoChecker(2019);
        LocalDateTime domingo = LocalDateTime.of(2019, 9, 22, 12, 0);
        assertTrue(feriadoChecker.esFinDeSemana(domingo));
    }

    @Test
    public void testNoEsFinDeSemana(){
        FeriadoChecker feriadoChecker = new FeriadoChecker(2019);
        LocalDateTime diaHabil = LocalDateTime.of(2019, 9, 23, 12, 0);
        assertFalse(feriadoChecker.esFinDeSemana(diaHabil));
    }

}



