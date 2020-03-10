package fr.univparis.metro;
import org.junit.*;
import static org.junit.Assert.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class DijkstraTest{

  static WGraph<Station> w = new WGraph<Station>();

  @BeforeClass
  public static void loadFile() throws IOException {
    URL url = ParserTest.class.getResource("/liste.txt");
    File f = new File(url.getFile());
    w = Parser.loadFrom(f);
  }

  @Test
  public void shortestPathTest(){
    Station a1                  = new Station("Saint-Lazare", "14");
    Station a2                  = new Station("Châtelet", "14");
    Station a3                  = new Station("Châtelet", "4");
    Station a4                  = new Station("Saint-Placide", "4");
    HashMap<Station, Station> stat = new HashMap<Station, Station>();
    HashMap<Station, Double> dist  = new HashMap<Station, Double>();
    Dijkstra.shortestPath(w, a1, stat, dist);
    assertEquals((Double) 270.0,  dist.get(a2));
    assertEquals((Double) 330.0, dist.get(a3));
    assertEquals((Double) 0.0, dist.get(a1));
    assertEquals((Double) 840.0, dist.get(a4));
    assertEquals("Station: Montparnasse - Bienvenüe, 4", stat.get(a4).toString());
    assertEquals("Station: Châtelet, 14", stat.get(a3).toString());
    assertEquals("Station: Pyramides, 14", stat.get(a2).toString());


    // Stations that had bugs
    Station laumS = new Station("Laumière", "Meta Station Start");
    Station mdiS = new Station("Mairie d'Issy", "Meta Station Start");
    Station mdiE = new Station("Mairie d'Issy", "Meta Station End");
    Station aglcS = new Station("Asnières - Gennevilliers - Les Courtilles", "Meta Station Start");
    Station cpS = new Station("Créteil - Préfecture", "Meta Station Start");
    Station gS = new Station("Gallieni", "Meta Station Start");
    Station afS = new Station("Anatole France", "Meta Station Start");
    Dijkstra.shortestPath(w, laumS, stat, dist);
    assertEquals((Double) 2340.0, dist.get(mdiE));

    /*
    Station[] set = {laumS, mdiS, aglcS, cpS, gS, afS};
    for (Station st : set) {
      Dijkstra.shortestPath(w, st, stat, dist);
      for (Station s : w.getVertices()) {
        if (s.getLine() != "Meta Station Start"){
          Double d = dist.get(s);
          assertTrue("" + st + s, d != Double.NaN && d != Double.POSITIVE_INFINITY);
          assertNotNull(stat.get(s));
        }
      }
    }
    */
  }
}
