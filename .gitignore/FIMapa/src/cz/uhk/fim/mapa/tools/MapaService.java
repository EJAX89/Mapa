package cz.uhk.fim.mapa.tools;

import java.awt.Image;
import java.util.List;

import cz.uhk.fim.mapa.entity.Misto;

/**
 * Rozhrani sluzby pro poskytnuti mapy a mist na ni vyznacenych.
 * @author Ales Jirasek
 */
public interface MapaService {
	/**
	 * Metoda, ktera vraci prislusny mapovy podklad
	 * @return obrazek mapy
	 */
	public Image getMapImage();
	
	/**
	 * Pridani mista m do evidence
	 * @param m instance, kterou chceme pridat 
	 */
	public void pridejMisto(Misto m);
	
	/**
	 * Smaze z evidence misto se zadanym id
	 * @param id identifikator mista, ktere chceme smazat z evidence
	 */
	public void smazMisto(int id);
	
	/**
	 * Opravi v evidenci misto se stejnym id tak, jak je zaslano v parametru 
	 * metody. Id mista v parametru musi byt ruzne od 0.
	 * @param m upravovane misto
	 */
	public void aktualizujMisto(Misto m);
	
	/**
	 * Vrati misto podle zadaneho id
	 * @param id hledane id
	 * @return misto
	 */
	public Misto getPodleId(int id);
	
	/**
	 * Vraci seznam vsech evidovanych mist
	 * @return seznam mist
	 */
	public List<Misto> getMista();
}
