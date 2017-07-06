package cz.uhk.fim.mapa.tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cz.uhk.fim.mapa.entity.Misto;

public class MapaServiceTest {
	static MapaService s;

	@Before
	public void setUp() throws Exception {
		s = new MemMapaService();
	}

	@Test
	public void testGetMapImage() {
		assertNotNull("Obrazek proste neni", s.getMapImage());
	}

	@Test
	public void testPridejMisto() {
		Misto m = new Misto(10, 20, "Hospoda u tri zavorek",
				"Kvalita na prvni pohled");
		int size = s.getMista().size();
		s.pridejMisto(m);
		assertTrue("pridejMisto nenastavuje ID", 0 != m.getId());
		assertEquals("Poèet míst pøidáním nenarostl.",size+1,s.getMista().size());
	}

	@Test
	public void testGetPodleId() {
		Misto m = new Misto(10, 20, "Hospoda u ctyr zavorek",
				"Kvalita na prvni pohled");
		s.pridejMisto(m);
		int id = m.getId();
		Misto m1 = s.getPodleId(id);
		assertEquals(m.getId(), m1.getId());
		assertEquals(m.getNazev(), m1.getNazev());
		assertEquals(m.getPopis(), m1.getPopis());
		assertEquals(m.getX(), m1.getX());
		assertEquals(m.getY(), m1.getY());
	}

	@Test
	public void testSmazMisto() {
		Misto m = new Misto(10, 20, "Hospoda u ctyr zavorek",
				"Kvalita na prvni pohled");
		s.pridejMisto(m);
		int id = m.getId();
		int size = s.getMista().size();
		s.smazMisto(id);
		boolean nalezen = false;
		for (Misto mi : s.getMista()) {
			if (mi.getId() == id) {
				nalezen = true;
				break;
			}
		}
		assertTrue("Misto se nesmazalo", !nalezen);
		assertEquals("Nic neubylo", size - 1, s.getMista().size());
	}

	@Test
	public void testAktualizujMisto() {
		Misto m = new Misto(10, 20, "Hospoda u peti zavorek",
				"Kvalita na prvni pohled");
		s.pridejMisto(m);
		int id = m.getId();
		m.setX(600);
		s.aktualizujMisto(m);
		Misto m1 = s.getPodleId(id);
		assertEquals(600, m1.getX());
	}

	@Test
	public void testGetMista() {
		assertNotNull("Getr seznamu míst nefunguje",s.getMista());
	}

}
