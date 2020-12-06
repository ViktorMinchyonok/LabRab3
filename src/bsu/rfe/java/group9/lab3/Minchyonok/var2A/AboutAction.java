package bsu.rfe.java.group9.lab3.Minchyonok.var2A;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


@SuppressWarnings("serial")
public class AboutAction extends AbstractAction {
	final MainFrame this$0;

	AboutAction(MainFrame var1, String $anonymous0) {
		super($anonymous0);
		this.this$0 = var1;
	}

	public void actionPerformed(ActionEvent event) {
//		JLabel formImag = new JLabel();
//		ImageIcon formul1 = new ImageIcon("������.jpg");
//		formImag.setIcon(formul1);
		Spravka spr = new Spravka();
		spr.setSize(370, 240);
		spr.setDefaultCloseOperation(1);
		spr.setVisible(true);
	}
}

