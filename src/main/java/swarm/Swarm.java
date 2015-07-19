package swarm;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Swarm extends JFrame {

	public Swarm() {
		super();
		initGUI();
	}

	private void initGUI() {
		add(new Board());

		setTitle("Swarm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		pack();
		setLocationRelativeTo(null);

		setVisible(true);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Swarm();
			}
		});
	}

}
