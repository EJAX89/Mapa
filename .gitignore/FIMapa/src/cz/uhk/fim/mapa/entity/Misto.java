package cz.uhk.fim.mapa.entity;

import java.awt.Graphics;

/**
 * Trida popisujici jedno misto vyznacene na mape
 * @author Ales Jirasek
 */
public class Misto {
	//==================================================================
	//Atributy
	//------------------------------------------------------------------
	/** 
	 * id mista. Jednoznacne identifikuje misto a je jedinecne v cele aplikace.
	 * Pokud je id==0, pak se jedna o instanci, ktera doposud nebyla zapsana do
	 * uloziste ostatnoch mist, tj. neni zaevidovana.
	 */
	private int id = 0;
	
	/** x-ova souradnice mista na mape, 
	 * pocatek souradneho systemu mapy je v levem hornim rohu obrazku mapy.
	 */
	
	protected int x;
	/** y-ova souradnice mista na mape, 
	 * pocatek souradneho systemu mapy je v levem hornim rohu obrazku mapy.
	 */
	
	protected int y;
	/** Kratky a vystizny nazev mista */
	String nazev;
	
	/** Popis mista */
	String popis;
	
	//==================================================================
	//Kontruktory
	//------------------------------------------------------------------
	public Misto() {}

	public Misto(int x, int y, String nazev, String popis) {
		this.x = x;
		this.y = y;
		this.nazev = nazev;
		this.popis = popis;
	}
	
	public Misto(int id, int x, int y, String nazev, String popis) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.nazev = nazev;
		this.popis = popis;
	}

	//==================================================================
	//Getry a setry
	//------------------------------------------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}
	
	//==================================================================
	//Vykreslovani
	//------------------------------------------------------------------
	public void paint(Graphics g) {
		throw new UnsupportedOperationException(
				"Nutno doprogramovat.");
	}
	
}
