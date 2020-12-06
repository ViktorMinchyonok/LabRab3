package bsu.rfe.java.group9.lab3.Minchyonok.var2A;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ResetActionListener implements ActionListener {
	final MainFrame this$0;

	ResetActionListener(MainFrame var1) {
		this.this$0 = var1;
	}

	public void actionPerformed(ActionEvent ev) {
		this$0.getTextFieldFrom().setText("0.0");
		this$0.getTextFieldTo().setText("10");
		this$0.getTextFieldStep().setText("0.1");
		this$0.getBoxResult().removeAll();
		this$0.getBoxResult().add(new JPanel());
		this$0.getSaveToTextMenuItem().setEnabled(false);
		this$0.getSaveToGraphicsMenuItem().setEnabled(false);
		this$0.getSearchValueMenuItem().setEnabled(false);
		this$0.getContentPane().validate();
	}
}
