package cz.uhk.fim.mapa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cz.uhk.fim.mapa.entity.Misto;
import cz.uhk.fim.mapa.tools.MapaService;

public class MistoDialog extends JDialog implements ActionListener{
	private JTextField txtNazev = new JTextField(15);
	private JTextArea txtPopisky = new JTextArea(10,15);
	private JButton btnOk = new JButton("Potvrdit");
	private JLabel lblNazev = new JLabel("Název hospody");
	private JLabel lblPopis= new JLabel("Popis hospody");
	private MapaService service;
	Misto misto;	
	BufferedImage bi;
	
	public MistoDialog() {
		setLayout(new BorderLayout(5,5));
		try {
			bi = ImageIO.read(getClass().getResource("img/ico.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(bi);
		
		JPanel pnl = new JPanel();
		JPanel n = new JPanel();
		JPanel p = new JPanel();
		
		add(pnl, BorderLayout.NORTH);
		add(n, BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);
		
		pnl.add(lblNazev,BorderLayout.NORTH);
		pnl.add(txtNazev, BorderLayout.SOUTH);
		n.add(lblPopis,BorderLayout.NORTH);
		n.add(txtPopisky, BorderLayout.SOUTH);
		p.add(btnOk, BorderLayout.CENTER);
		btnOk.addActionListener(this);
		
		setLocation(200, 200);
		pnl.setVisible(true);
		n.setVisible(true);
		p.setVisible(true);
		
		setModal(true);
		
		pack();
	}
	
	public void zobrazProEditaci(Misto m){
		setTitle("Upravení hospody: " + m.getNazev());
		misto = m;
		txtNazev.setText(misto.getNazev());
		txtPopisky.setText(misto.getPopis());
		setModal(true);
		setVisible(true);
	}
	
	public void zobrazProVytvoreni(){
		setTitle("Pøidání nového místa");
		misto = new Misto();
		//nastavit atributy mista podle obsahu fieldu
		setVisible(true);
	}
	
	public Misto getMisto(){
		return misto;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		setVisible(false);
		if (e.getSource()==btnOk) {
			misto.setNazev(txtNazev.getText());
			misto.setPopis(txtPopisky.getText());
		}
	}
	
}