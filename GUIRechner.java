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

	JTextField 	textfield_input1;
	JTextField 	textfield_input2;
	JTextField 	textfield_output;

	JRadioButton rbPlus;
	JRadioButton rbMinus;
	JRadioButton rbMal;
	JRadioButton rbGeteilt;
	JRadioButton rbHoch;

	JButton		button;

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
		textfield_input1 = new JTextField();
		textfield_input2 = new JTextField();
		textfield_output = new JTextField();
		textfield_output.setEditable(false);

		rbPlus = new JRadioButton("+");
		rbMinus = new JRadioButton("-");
		rbMal = new JRadioButton("*");
		rbGeteilt = new JRadioButton("/");
		rbHoch = new JRadioButton("^");
		rbPlus.setSelected(true);

		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(rbPlus);
		rbGroup.add(rbMinus);
		rbGroup.add(rbMal);
		rbGroup.add(rbGeteilt);
		rbGroup.add(rbHoch);

		button = new JButton("Berechnen");

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				neuBerechnen();
			}
		});

		// Positionen festlegen
		textfield_input1.setBounds(5,10,400,25);
		textfield_input2.setBounds(5,40,400,25);
		textfield_output.setBounds(5,100,400,25);

		rbPlus.setBounds(5,70,40,25);
		rbMinus.setBounds(55,70,40,25);
		rbMal.setBounds(105,70,40,25);
		rbGeteilt.setBounds(155,70,40,25);
		rbHoch.setBounds(205,70,40,25);

		button.setBounds(300,130,100,30);

		// Elemente dem Fenster hinzufügen:
		this.getContentPane().add(textfield_input1);
		this.getContentPane().add(textfield_input2);
		this.getContentPane().add(textfield_output);
		this.getContentPane().add(button);
		this.getContentPane().add(rbPlus);
		this.getContentPane().add(rbMinus);
		this.getContentPane().add(rbMal);
		this.getContentPane().add(rbGeteilt);
		this.getContentPane().add(rbHoch);

		ActionListener escListener = new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		ActionListener enterListener = new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				neuBerechnen();
			}
		};
	
		this.getRootPane().registerKeyboardAction(escListener
		,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		this.getRootPane().registerKeyboardAction(enterListener,
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		this.pack();
	}

	public void neuBerechnen()
	{
		// Hole Zahl aus Textfeld:
		boolean ok = true;
		double input1 = 0;
		try {
			input1 = Double.parseDouble(textfield_input1.getText());
		} catch (NumberFormatException e) {
			ok = false;
		}

		double input2 = 0;
		try {
			input2 = Double.parseDouble(textfield_input2.getText());
		} catch (NumberFormatException e) {
			ok = false;
		}

		// Falls das n Okay ist:

		if (ok) {
			double result = 0;
			if (rbPlus.isSelected()) {
				result = input1 + input2;
			} else if (rbMinus.isSelected()) {
				result = input1 - input2;
			} else if (rbMal.isSelected()) {
				result = input1 * input2;
			} else if (rbGeteilt.isSelected()) {
				result = input1 / input2;
			}else if (rbHoch.isSelected()){
				double loesung=1;
				while(input2>=1){
					loesung = loesung*input1;
					input2= input2-1;}
				result=loesung;

			}

			// Packe a in Ausgabefeld:
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			String ausgabe = nf.format(result);

			textfield_output.setText(ausgabe);
		} else {
			textfield_output.setText("Eingabe ist nicht in Ordnung.");
		}
	}

}

public class GUIRechner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AppWindow theAppWindow = new AppWindow();
		theAppWindow.setBounds(10, 10, 420, 180);
		theAppWindow.setLocationRelativeTo(null);
		theAppWindow.setVisible(true);
	}
}
