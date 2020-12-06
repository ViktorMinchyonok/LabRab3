package bsu.rfe.java.group9.lab3.Minchyonok.var2A;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
	private static final int COLUMN_COUNT = 3;

	private Double[] coefficients;
	private Double from;
	private Double to;
	private Double step;
	public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
		this.from = from;
		this.to = to;
		this.step = step;
		this.coefficients = coefficients;
	}

	public Double getFrom() {
		return from;
	}

	public Double getTo() {
		return to; 
	}

	public Double getStep() {
		return step;
	}

	public int getColumnCount() { 
		return COLUMN_COUNT;
	}

	@SuppressWarnings("deprecation")
	public int getRowCount() {
		return new Double(Math.ceil((to-from)/step)).intValue()+1; 
	}
	
	public Object getValueAt(int row, int col) {
		Double x = from + step * (double)row;
		Double result = 0.0D;

		for(int i = 0; i < coefficients.length - 1; ++i) {
			result = (result + coefficients[i]) * x;
		}

		result = result + coefficients[coefficients.length - 1];
		if (col == 0) {
			return x;
		} else if (col == 1) {
			return result;
		} else if (col == 2) {
			return (result > 0);
		}

		return result;
	}

	public String getColumnName(int col) {
		switch (col) {
		case 0: 
			return "Значение X"; 
		case 1:  
			return "Значение многочлена";
		case 2:
			return "Значение больше нуля?";
		default:
			return "Н/А";
		}
	}

	public Class<?> getColumnClass(int col) {
		return col == 2 ? Boolean.class : Double.class;
	}
}

