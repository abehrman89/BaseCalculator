import javax.swing.JFrame;

public class Base4Calculator {

	public static void main(String[] args) {
		createAndShowGUI();
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Base 4 Calculator");

		frame.add(new Base4Panel());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(325, 325);
		frame.setVisible(true);
	}
}
