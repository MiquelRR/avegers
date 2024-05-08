package org.antonio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.antonio.Model.Heroe;
import org.junit.Before;
import org.junit.Test;

public class TestHeroes {
    Heroe ironMan;
    Heroe spiderMan;
    Heroe capitanAmerica;

    @Before
    public void setUp(){
        ironMan= new Heroe("Iron Man", "Traje de alta tecnología", "Millonario y filántropo");
        spiderMan = new Heroe("Spider-Man", "Sentido arácnido, trepador", "Tímido estudiante de secundaria");
        capitanAmerica = new Heroe("Capitán América", "Superfuerza, agilidad, resistencia", "Soldado de la Segunda Guerra Mundial");
    }
    @Test
    public void testGetters(){
        assertEquals("Iron Man", ironMan.getNombre());
        assertNotEquals("feo",spiderMan.getBiografia() );
        assertTrue(capitanAmerica.getSuperpoderes().length()>5);
    }

    @Test
    public void testSetters(){
        spiderMan.setNombre("aracnido");
        assertEquals("aracnido", spiderMan.getNombre());
        String nom=capitanAmerica.getNombre();
        capitanAmerica.setNombre("capi");
        assertNotEquals(nom,capitanAmerica.getNombre());
    }
    
}