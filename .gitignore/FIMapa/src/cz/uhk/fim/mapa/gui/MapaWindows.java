package cz.uhk.fim.mapa.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cz.uhk.fim.mapa.entity.Misto;
import cz.uhk.fim.mapa.tools.MapaService;
import cz.uhk.fim.mapa.tools.MemMapaService;

public class MapaWindows extends JFrame implements ActionListener {
	private Action actPridat;
	private Action actUpravit;
	private Action actPoloha;
	private Action actSmaz;
	private Action actNovy;
	private Action actUloz;
	private Action actOtevri;
	private Action actObnovit;
	private Action actO;
	private JTextArea txtPopisky = new JTextArea(12,8);
	private MapaKomponenta lblMapa = new MapaKomponenta();
	private MapaService service;
	private MistoDialog md;
	private SeznamTableModel model;
	BufferedImage bi;
	private boolean pridat;
	private boolean zmenit;
	private Misto mi;
	
	public MapaWindows() {
		try {
			bi = ImageIO.read(getClass().getResource("img/ico.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(bi);
		setSize(800, 600);
		setTitle("Mapa hospùdek");
		service = new MemMapaService();
		add(lblMapa, BorderLayout.EAST);
		Image img = service.getMapImage();
		Icon icon = new ImageIcon(img);
		lblMapa.setIcon(icon);
		add(new JScrollPane(lblMapa));
		
		vytvorAkce();
		vytvorToolbar();
		vytvorMenu();
		vytvorSeznam();
		
		MouseListener ml = new MouseAdapter(){
			public void mouseClicked(MouseEvent ev){
				if (pridat) {
					pridat=false;
					md.getMisto().setX(ev.getX());
					md.getMisto().setY(ev.getY());
					lblMapa.zvyrazni(md.getMisto());
					model.fireTableDataChanged();
				} 
			}
			public void mouseReleased(MouseEvent ev) {
				zmenit = false;
			}
		};
		
		MouseMotionListener mml = new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent ev) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent ev) {
				// TODO Auto-generated method stub
				if (zmenit) {
					mi.setX(ev.getX());
					mi.setY(ev.getY());
					lblMapa.zvyrazni(mi);
				}
			}
		};
		
		lblMapa.addMouseMotionListener(mml);
		lblMapa.addMouseListener(ml);
		
		pack();
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void vytvorSeznam() {
		final JTable tblTabulka = new JTable();
		JPanel k = new JPanel();
		JPanel dole = new JPanel();
		JLabel lblpopisek = new JLabel();
		model = new SeznamTableModel((MemMapaService) service);
		lblpopisek.setText("Popis vybraneho mista: ");
		
		tblTabulka.setModel(model);

        k.add(new JScrollPane(tblTabulka), BorderLayout.NORTH);
		add(k, BorderLayout.WEST);
		k.setLayout(new BorderLayout());
		k.add(dole, BorderLayout.SOUTH);
		k.add(new JScrollPane(tblTabulka), BoxLayout.Y_AXIS);
		dole.setLayout(new BorderLayout());
		dole.add(new JScrollPane(lblpopisek), BorderLayout.NORTH);
		dole.add(new JScrollPane(txtPopisky),BorderLayout.SOUTH);
		dole.setVisible(true);
		
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true, k, new JScrollPane(lblMapa));
		add(jsp);
		jsp.setDividerLocation(300);

			
		tblTabulka.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    tblTabulka.setPreferredScrollableViewportSize(new Dimension(300,100));
	    tblTabulka.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        	   public void valueChanged(ListSelectionEvent e) {
		                int pos = tblTabulka.getSelectedRow();
		                if (pos != -1) {
		                    mi = service.getMista().get(pos);
		                    txtPopisky.setText(mi.getPopis());
		                    lblMapa.zvyrazni(mi);
		                    
		        			actUpravit.setEnabled(true);
		        			actPoloha.setEnabled(true);
		        			actSmaz.setEnabled(true);
		                } else {
							lblMapa.zvyrazni(null);
							actUpravit.setEnabled(false);
							actPoloha.setEnabled(false);
							actSmaz.setEnabled(false);	
						}
	            }
	        });
	       
		pack();
	}
	
	private void vytvorPopUp(String title){
		try {
			bi = ImageIO.read(getClass().getResource("img/ico.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame frmOkno = new JFrame();
		JLabel lblpopis = new JLabel("Vytvoøil Aleš Jirásek (c)2009");
		frmOkno.setTitle(title);
		frmOkno.setSize(300, 100);
		frmOkno.setVisible(true);
		frmOkno.setIconImage(bi);
		frmOkno.add(lblpopis, BorderLayout.CENTER);
		frmOkno.setLocation(200, 200);
	}

	private void vytvorMenu() {
		JMenuBar mnu = new JMenuBar();
		JMenu mnuMisto = new JMenu("Místo");
		JMenu mnuSoubor = new JMenu("Soubor");
		JMenu mnuOprogramu = new JMenu("O programu");
		
		mnu.add(mnuSoubor);
		mnu.add(mnuMisto);
		mnu.add(mnuOprogramu);
		mnuSoubor.add(actNovy);
		mnuSoubor.add(actOtevri);
		mnuSoubor.add(actUloz);
		mnuSoubor.add(actObnovit);
		mnuMisto.add(actPridat);
		mnuMisto.add(actUpravit);
		mnuMisto.add(actSmaz);
		mnuMisto.add(actPoloha);
		mnuOprogramu.add(actO);
		
		setJMenuBar(mnu);
	}

	private void vytvorToolbar() {
		JToolBar tb = new JToolBar();
		tb.add(new JButton(actPridat));
		tb.add(new JButton(actUpravit));
		tb.add(new JButton(actSmaz));
		tb.add(new JButton(actPoloha));
		
		tb.addSeparator();
		
		tb.add(new JButton(actObnovit));
		add(tb, BorderLayout.NORTH);
	}

	private void vytvorAkce() {
		// TODO Auto-generated method stub
		actPridat = new AbstractAction("Pridat", new ImageIcon(getClass().getResource("img/newItem.png"))) {			
			@Override
			public void actionPerformed(ActionEvent e) {
				md = new MistoDialog();
				md.zobrazProVytvoreni();
				Misto m = md.getMisto();
				service.pridejMisto(m);
				pridat = true;
			}
		};
		actUpravit = new AbstractAction("Upravit", new ImageIcon(getClass().getResource("img/editItem.png"))) {
			@Override
			public void actionPerformed(ActionEvent e) {
				md = new MistoDialog();
				md.zobrazProEditaci(mi);
				service.aktualizujMisto(mi);
				model.fireTableDataChanged();
				lblMapa.repaint();
			}
		};
		actPoloha = new AbstractAction("Zmìnit polohu", new ImageIcon(getClass().getResource("img/moveItem.png"))) {
			@Override
			public void actionPerformed(ActionEvent e) {
				zmenit = true;	
				service.aktualizujMisto(mi);
				model.fireTableDataChanged();
				lblMapa.repaint();
			}
		}; 
		actSmaz = new AbstractAction("Smazat", new ImageIcon(getClass().getResource("img/delItem.png"))) {
			@Override
			public void actionPerformed(ActionEvent e) {
				service.smazMisto(mi.getId());
				model.fireTableDataChanged();
				txtPopisky.setText("Hospoda smazána");
			}
		};
		actObnovit = new AbstractAction("Obnovit", new ImageIcon(getClass().getResource("img/reload.png"))) {			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			model.fireTableDataChanged();
			}
		};
		actNovy = new AbstractAction("Novy", new ImageIcon(getClass().getResource("img/new.png"))) {			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			System.out.println("pokus");	
			}
		};
		actOtevri = new AbstractAction("Otevrit", new ImageIcon(getClass().getResource("img/open.png"))) {			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			System.out.println("pokus");	
			}
		};
		actUloz = new AbstractAction("Ulozit", new ImageIcon(getClass().getResource("img/save.png"))) {			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			System.out.println("pokus");	
			}
		};
		actO = new AbstractAction("O programu", new ImageIcon(getClass().getResource("img/ico.png"))) {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				vytvorPopUp("O programu");
				
			}
			
		};
		actUpravit.setEnabled(false);
		actPoloha.setEnabled(false);
		actSmaz.setEnabled(false);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MapaWindows();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
