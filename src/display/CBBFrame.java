package display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CBBFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static CBBFrame one_frame;
	
	private static final int DIM = 640;
	
	private CBBFrame(String name) {
		super(name);
	}
	
	public void build() {
		// SysBox
		JTextArea text = sysBox();
		
		// Buttons
		JPanel bP = Buttons.getButtonPanel(DIM);
		
		// System panel on the west, buttons on the east.
		add(text,BorderLayout.WEST);
		add(bP,BorderLayout.EAST);	
	    
	    // Display logistics.
		style();
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(DIM+(int)(DIM*.25),DIM));
	    pack();
	}
	
	private JTextArea sysBox() {
		
		// Main Text box
		JTextArea text = new JTextArea();

		OutputStream os = new OutputStream() {
			private JTextArea textArea = text;

			@Override
			public void write(int b) throws IOException {
				// redirects data to the text area
				textArea.append(String.valueOf((char)b));
				// scrolls the text area to the end of data
				textArea.setCaretPosition(textArea.getDocument().getLength());
				// keeps the textArea up to date
				textArea.update(textArea.getGraphics());
			}
		};

		System.setOut(new PrintStream(os));
		
		return text;
	}
	
	public static CBBFrame init(String s) {
		assert (one_frame == null);
		
		one_frame = new CBBFrame(s);
		one_frame.build();
		
		return one_frame;
	}
	
	public static CBBFrame getInstance() {
		if (one_frame == null)
			one_frame = new CBBFrame("NOT INITIALIZED PROPERLY");
		return one_frame;
	}
	
	private void style() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch (ClassNotFoundException | InstantiationException | 
				IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
