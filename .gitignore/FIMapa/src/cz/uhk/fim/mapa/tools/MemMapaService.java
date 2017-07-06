package cz.uhk.fim.mapa.tools;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import cz.uhk.fim.mapa.entity.Misto;
import cz.uhk.fim.mapa.gui.MapaKomponenta;

/**
 * @author Ales Jirasek
 * 
 */
public class MemMapaService implements MapaService {
	private List<Misto> mista = new ArrayList<Misto>();
	private static int id = 0;
	
	public MemMapaService(){
		Misto m = new Misto(250,200, "Hospodu U Zvonu", "popisek");
		pridejMisto(m);
		Misto n = new Misto(100,300, "Hospoda H", "prima");
		pridejMisto(n);
	}
	@Override
	public void aktualizujMisto(Misto m) {
		
		
	}

	@Override
	public Image getMapImage() {
		// TODO Auto-generated method stub
		File f = new File("mapa/mapaHK.jpg");
		Image i = null;
		try {
			i = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<Misto> getMista() {
		return mista;
	}

	@Override
	public Misto getPodleId(int id) {
		Misto a = null;
		for (Misto m : mista) {
			if (id==m.getId()) {
				a= m;
			}
		}
		return a;
	}

	@Override
	public void pridejMisto(Misto m) {
		id++;
		m.setId(id);
		mista.add(m);
	}

	@Override
	public void smazMisto(int id) {
		for (int i = 0; i < mista.size(); i++) {
			if (id==mista.get(i).getId()) {
				mista.remove(i);
			}
		}
	}
	public void zobrazMistaNaMape(){
		MapaKomponenta komponentaMapa = new MapaKomponenta();
		for (Misto m : mista) { 
		komponentaMapa.zvyrazni(m);
		}
	}
	
}
