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
		
		setSize(250, 200);
		setTitle("Swarm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Swarm app = new Swarm();
				app.setVisible(true);
			}
		});
	}

}
