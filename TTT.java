import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


class AppWindow extends JDialog {

	JButton[] buttons  = new JButton[9];
    JTextField 	textfield_output;

	public AppWindow() {
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

	protected void initWindow() 
	{
		for (int i =0; i<10; i++) {
            buttons[i] = new JButton(); 
            buttons[i].setBounds(i*25+10,130-i%3*50,100,30); 
            this.getContentPane().add(buttons[i]);
        } 

		// button.addActionListener(new ActionListener() {
		// 	public void actionPerformed(ActionEvent arg0) {
		// 		neuBerechnen();
        //         this.getContentPane().add(buttons);
		// 	}
		// });

		// Positionen festlegen
		textfield_output.setBounds(5,100,400,25);


		// Elemente dem Fenster hinzufügen:
		this.getContentPane().add(textfield_output);
		

		ActionListener escListener = new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
	
		this.getRootPane().registerKeyboardAction(escListener
		,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		this.pack();
	}


public class TTT {
    public static void main(String[] args) {
		AppWindow theAppWindow = new AppWindow();
		theAppWindow.setBounds(10, 10, 420, 210);
		theAppWindow.setLocationRelativeTo(null);
		theAppWindow.setTitle("TTT");
		theAppWindow.setVisible(true);
	}
}
}