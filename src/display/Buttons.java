package display;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Buttons {
	
	private static int bCount = 6;
	
	public static JPanel getButtonPanel(int DIM) {
		JPanel bP = new JPanel();
		bP.setLayout(new GridLayout(bCount,1));
		bP.setPreferredSize(new Dimension(DIM/bCount,DIM));
		JButton[] bs = new JButton[bCount];
		
		String[] scopes = {"All","Annual","3-Month","Monthly","Weekly"};
		bs[0] = new StateButton("Scope",scopes);
		bs[1] = new JButton("2");
		bs[2] = new JButton("3");
		bs[3] = new JButton("4");
		bs[4] = new JButton("5");
		bs[5] = new JButton("6");
		for (JButton b : bs)
			if (b != null)
				bP.add(b);
		
		return bP;
	}
	
	public static class StateButton extends JButton {
		private static final long serialVersionUID = 1L;
		
		private String[] states;
		private int current = 0;
		
		public StateButton(String title,String[] states) {
			this.states = states;
			addActionListener(e -> {
				current++;
				if (current >= states.length)
					current = 0;
				this.setText(title+":\n"+state());
				System.out.println(state());
			});
		}
		
		public String state() {
			return states[current];
		}
	}
}
