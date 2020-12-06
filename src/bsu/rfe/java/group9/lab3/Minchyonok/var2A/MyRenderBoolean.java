package bsu.rfe.java.group9.lab3.Minchyonok.var2A;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyRenderBoolean implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus,
			int row, int column) {
JCheckBox hboxBool = new JCheckBox();

hboxBool.setSelected((Boolean)value);

if ((Boolean)value) {
hboxBool.setText("Да");
} else {
hboxBool.setText("Нет");
}

return hboxBool;
}

}

