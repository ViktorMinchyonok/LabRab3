package bsu.rfe.java.group9.lab3.Minchyonok.var2A;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
@SuppressWarnings("serial")
public class SaveToGraphicsAction extends AbstractAction {
	  // $FF: synthetic field
	   final MainFrame this$0;

	   SaveToGraphicsAction(MainFrame var1, String $anonymous0) {
	      super($anonymous0);
	      this.this$0 = var1;
	   }

	   public void actionPerformed(ActionEvent event) {
	      if (this$0.getFileChooser() == null) {
	         this$0.setFileChooser(new JFileChooser());
	         this$0.getFileChooser().setCurrentDirectory(new File("."));
	      }

	      if (this$0.getFileChooser().showSaveDialog(this$0) == 0) {
	      }

	      this.this$0.saveToGraphicsFile(this$0.getFileChooser().getSelectedFile());
	   }
}
