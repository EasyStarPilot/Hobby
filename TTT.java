import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

class TTTAppWindow extends JDialog {

	JButton[][] buttons = new JButton[3][3];
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
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setBounds(10 + i * 60, j * 60 + 10, 50, 50);
				this.getContentPane().add(buttons[i][j]);
				buttons[i][j].setBackground(Color.WHITE);
				buttons[i][j].addActionListener(new ActionListener() {
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
							int h = 0;
							for (int g = 0; g < 2; g++) {
								if ((buttons[g][h].getBackground() != Color.WHITE)&& (buttons[g][h].getBackground()) == (buttons[g][h + 1].getBackground())&& (buttons[g][h + 1].getBackground()) == (buttons[g][h + 2].getBackground())		//Zeile abchecken
									|| (buttons[h][g].getBackground() != Color.WHITE)&& (buttons[h][g].getBackground()) == (buttons[h + 1][g].getBackground())&& (buttons[h + 1][g].getBackground()) == (buttons[h + 2][g].getBackground())		//Spalte abchecken
									|| (buttons[1][1].getBackground() != Color.WHITE)&& (buttons[0][0].getBackground()) == (buttons[1][1].getBackground())&& (buttons[1][1].getBackground()) == (buttons[2][2].getBackground())					//Diagonale1 abchecken
									|| (buttons[1][1].getBackground() != Color.WHITE)&& (buttons[0][2].getBackground()) == (buttons[1][1].getBackground())&& (buttons[1][1].getBackground()) == (buttons[2][0].getBackground())) {				//Diagonale2 abchecken
									if (player == true) {
										textfield_output.setText("Spieler 1 hat gewonen!!!");
										for (int x = 0; x < 3; x++) {
											for (int y = 0; y < 3; y++) {
												buttons[x][y].setEnabled(false);
											}
										}
									} else if (player == false) {
										textfield_output.setText("Spieler 2 hat gewonen!!!");
										for (int x = 0; x < 3; x++) {
											for (int y = 0; y < 3; y++) {
												buttons[x][y].setEnabled(false);
											}
										}
									}
								}
							}
						} else {
							textfield_output.setText("Das darfst du nicht!!!");
						}
					}
				});
			}
		}

		// Positionen festlegen
		textfield_output.setBounds(10, 190, 170, 25);

		// Elemente dem Fenster hinzufügen:
		this.getContentPane().add(textfield_output);
		this.pack();

	}
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
