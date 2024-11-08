import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

class TTTAppWindow extends JDialog {

	JButton[] buttons = new JButton[9];
	JTextField textfield_output;
	boolean player = false;

	public TTTAppWindow() {
		this.getContentPane().setLayout(null);
		this.initWindow();
		this.addWindowListener(new WindowListener() {
			public void windowClosed(WindowEvent arg0) {
			}
			public void windowActivated(WindowEvent e) {
			}
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			public void windowDeactivated(WindowEvent e) {
			}
			public void windowDeiconified(WindowEvent e) {
			}
			public void windowIconified(WindowEvent e) {
			}
			public void windowOpened(WindowEvent e) {
			}
		});

	}

	protected void initWindow() {
		textfield_output = new JTextField();
		textfield_output.setEditable(false);
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttons[i].setBounds(10 + i % 3 * 60, i / 3 * 60 + 10, 50, 50);
			this.getContentPane().add(buttons[i]);
			buttons[i].setBackground(Color.WHITE);
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JButton button = ((JButton) arg0.getSource());
					if (button.getBackground() == Color.WHITE) {
						if (player == true) {
							button.setBackground(Color.YELLOW);
							player = false;
						} else {
							button.setBackground(Color.RED);
							player = true;
						}
						textfield_output.setText("");
						// if (Win(buttons) == true) {

						// }
					} else {
						textfield_output.setText("Das darfst du nicht!!!");
					}
				}
			});
		}

		// Positionen festlegen
		textfield_output.setBounds(10, 190, 170, 25);

		// Elemente dem Fenster hinzufügen:
		this.getContentPane().add(textfield_output);
		this.pack();
	}
}

public static boolean Win() {

}

public class TTT {
	public static void main(String[] args) {
		TTTAppWindow theAppWindow = new TTTAppWindow();
		theAppWindow.setBounds(105, 133, 210, 265);
		theAppWindow.setLocationRelativeTo(null);
		theAppWindow.setTitle("TTT");
		theAppWindow.setVisible(true);
	}
}
