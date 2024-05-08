package org.antonio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.antonio.Exception.HeroeNoEncontradoException;
import org.antonio.Model.GestorHeroes;
import org.antonio.Model.Heroe;
import org.junit.Before;
import org.junit.Test;

public class TestGestorHeroes {
    Heroe ironMan;
    Heroe spiderMan;
    Heroe capitanAmerica;
    Heroe laCosa;
    GestorHeroes teamA;


    @Before
    public void setUp(){
        teamA=new GestorHeroes();
        ironMan= new Heroe("Iron Man", "Traje de alta tecnología", "Millonario y filántropo");
        spiderMan = new Heroe("Spider-Man", "Sentido arácnido, trepador", "Tímido estudiante de secundaria");
        capitanAmerica = new Heroe("Capitán América", "Superfuerza, agilidad, resistencia", "Soldado de la Segunda Guerra Mundial");
        laCosa = new Heroe("La Cosa", "Superfuerza, repartir osties", "li va caure un mur de pedra quan anava de tripi i es va integrar amb ell");
        teamA.agregarHeroe(capitanAmerica);
        teamA.agregarHeroe(ironMan);
        teamA.agregarHeroe(spiderMan);
        teamA.agregarHeroe(laCosa);
    }

    @Test
    public void testGetters(){
        
        GestorHeroes lista= new GestorHeroes();
        lista.agregarHeroe(capitanAmerica);
        lista.agregarHeroe(ironMan);
        lista.agregarHeroe(spiderMan);
        lista.agregarHeroe(ironMan);
        assertNotSame(lista.getHeroes(), teamA.getHeroes());
        assertEquals(lista.getHeroes().size(), teamA.getHeroes().size());
    }

    @Test
    public void testAddSeek()throws HeroeNoEncontradoException{
        teamA.agregarHeroe(laCosa);
        assertSame(laCosa,teamA.buscarHeroe("La Cosa"));
    }


    @Test(expected = HeroeNoEncontradoException.class)
    public void testHeroeNoEncontradoException() throws HeroeNoEncontradoException{
        teamA.buscarHeroe("Miquel");
    }

    @Test
    public void actualizaTest() throws HeroeNoEncontradoException{
        capitanAmerica.setDescripcion("Es un tipo way, que promociona el saludable, humilde y honrado estilo de vida norteamericano, con su escudo y los colores de la bandera en el traje, el equivalente en españa es :https://rb.gy/frk3e8");
        Heroe whoIsThisHeroe = teamA.buscarHeroe(capitanAmerica.getNombre());
        assertNotNull(whoIsThisHeroe.getDescripcion());
        assertEquals(capitanAmerica.getDescripcion(), whoIsThisHeroe.getDescripcion());

    }

    @Test
    public void  eliminaTest() throws HeroeNoEncontradoException{
        int cantidad=teamA.getHeroes().size();
        teamA.eliminarHeroe(spiderMan.getNombre());
        assertTrue(cantidad > teamA.getHeroes().size());       
    }

    @Test(expected = HeroeNoEncontradoException.class)
    public void testBuscarHeroeEliminadoNoEncontrado() throws HeroeNoEncontradoException{
        teamA.eliminarHeroe(spiderMan.getNombre());
        teamA.buscarHeroe(spiderMan.getNombre());
    }

    @Test
    public void testMultipleAgregado() throws HeroeNoEncontradoException {
        Heroe seraSpiderMan = teamA.buscarHeroe(spiderMan.getNombre());
        Heroe seraIronMan = teamA.buscarHeroe(ironMan.getNombre());
        Heroe seraCapitanAmerica = teamA.buscarHeroe(capitanAmerica.getNombre());
        assertNotEquals(ironMan.getNombre(), seraSpiderMan.getNombre());
        assertSame(ironMan, seraIronMan);
        assertEquals(capitanAmerica.getBiografia(), seraCapitanAmerica.getBiografia());
    }

    @Test
    public void testBusquedaHeroePorSuperpoder() throws HeroeNoEncontradoException{
        assertSame(spiderMan, teamA.buscarHeroePorSuperpoder("trepador"));
        assertEquals(laCosa.getNombre(), teamA.buscarHeroePorSuperpoder("osties").getNombre());
    }

    @Test(expected = HeroeNoEncontradoException.class)
    public void testBusquedaHeroesPorSuperpoderNoEncontrado() throws HeroeNoEncontradoException{
        assertSame(spiderMan, teamA.buscarHeroePorSuperpoder("paciente"));
    }

    @Test
    public void testActualizaHeroeGestor() throws HeroeNoEncontradoException {
        Heroe spiderManPocho = new Heroe("Spider-Man", "se le han acabado las telarañas, tiene artrosis y no salta ni a la comba", "es el mismo, pero la edad no perdona" );
        teamA.actualizarHeroe(spiderManPocho);
        assertNotEquals(spiderMan.getBiografia(), teamA.buscarHeroe(spiderMan.getNombre()).getBiografia());
        assertNotSame(spiderMan, teamA.buscarHeroe(spiderMan.getNombre()));
        assertNotNull(teamA.buscarHeroe(spiderManPocho.getNombre()));
    }
    
    @Test
    public void testLista(){
        String esperada=capitanAmerica+","+ironMan+","+spiderMan+","+laCosa;
        assertNotNull(teamA.listarHeroes());
        assertEquals(esperada, teamA.listarHeroes());
        assertNotEquals(ironMan, teamA.listarHeroes());
    }

    @Test
    public void testBusquedaHeroesPorSuperpoder() throws HeroeNoEncontradoException{
        List<Heroe> lista = new ArrayList();
        lista.add(spiderMan);
        lista.add(capitanAmerica);
        assertTrue(teamA.buscarHeroesPorSuperpoder("Superfuerza").contains(laCosa));
        assertNotSame(lista,teamA.buscarHeroesPorSuperpoder("Superfuerza"));
        assertNotNull(teamA.buscarHeroesPorSuperpoder(spiderMan.getSuperpoderes()));        
    }


}
