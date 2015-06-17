import java.awt.FlowLayout;

import gui.ChoicePanel;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class test {

	public static void main(String[] args) {
		JFrame test = new JFrame();
		test.add(new ChoicePanel());
		test.setLayout(new FlowLayout());
		test.pack();
		test.setVisible(true);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
