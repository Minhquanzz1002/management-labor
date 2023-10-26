package UI;

import javax.swing.SwingUtilities;


public class Application {
	public static void main(String[] args) {
	 SwingUtilities.invokeLater(()-> new GUI_DangNhap().setVisible(true));
	}

}
