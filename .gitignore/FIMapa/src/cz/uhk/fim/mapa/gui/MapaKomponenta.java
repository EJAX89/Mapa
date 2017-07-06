package cz.uhk.fim.mapa.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import cz.uhk.fim.mapa.entity.Misto;

public class MapaKomponenta extends JLabel{
	
	private Misto misto;
	
	public void zvyrazni(Misto m) {
		misto = m;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (misto!=null) {
			int r = 5;
			g.setColor(Color.RED);
			g.drawString(misto.getNazev(), misto.getX()-10, misto.getY()-10);
			g.fillOval(misto.getX()-r, misto.getY()-r, 2*r, 2*r);
			
			int r1 = 2;
			g.setColor(Color.WHITE);
			g.fillOval(misto.getX()-r1, misto.getY()-r1, 2*r1, 2*r1);
			
		}
		
	}
}
