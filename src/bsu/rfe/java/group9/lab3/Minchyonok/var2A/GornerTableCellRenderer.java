package bsu.rfe.java.group9.lab3.Minchyonok.var2A;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class GornerTableCellRenderer implements TableCellRenderer {
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel(); 
	private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int col) {
		
		String znakX = formatter.format(value);
		label.setText(znakX);
		panel.setBackground(Color.WHITE);
		label.setForeground(Color.BLACK);

		if (col == 1 && Double.parseDouble(znakX) < 0.0D) {
			panel.setBackground(Color.GREEN);
			label.setForeground(Color.RED);
		}

		return panel;
	}

	public GornerTableCellRenderer() {
		panel.add(label);
		panel.setLayout(new FlowLayout(0));
		formatter.setMaximumFractionDigits(5);
		formatter.setGroupingUsed(false);
		DecimalFormatSymbols dotDouble = formatter.getDecimalFormatSymbols();
		dotDouble.setDecimalSeparator('.');
		formatter.setDecimalFormatSymbols(dotDouble);
	}
	 
}
