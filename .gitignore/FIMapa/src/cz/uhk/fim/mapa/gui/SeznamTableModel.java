package cz.uhk.fim.mapa.gui;


import javax.swing.table.AbstractTableModel;
import cz.uhk.fim.mapa.entity.Misto;
import cz.uhk.fim.mapa.tools.MapaService;
import cz.uhk.fim.mapa.tools.MemMapaService;

public class SeznamTableModel extends AbstractTableModel {
	private MemMapaService mista;
	public SeznamTableModel(MemMapaService m) {
		mista=m;
	}	
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return mista.getMista().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Misto m = mista.getMista().get(rowIndex);
		switch (columnIndex) {
		case 0 : return m.getNazev();
		case 1 : return m.getPopis();
		}
		return null;
	}
	public String getColumnName(int column){
		switch (column) {
		case 0 : return "Název";
		case 1 : return "Popis";
		}
		return null;
	}	
}
