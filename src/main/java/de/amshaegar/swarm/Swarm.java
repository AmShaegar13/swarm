package de.amshaegar.swarm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Swarm extends JFrame implements Runnable {

	private static final long DELAY = 17; // 17ms ~ 60 FPS

	private final Board board;
	private final JLabel fpsLabel;

	public Swarm() {
		super();

		board = new Board();
		fpsLabel = new JLabel();
		initGUI();

		new Thread(this).start();
	}

	private void initGUI() {
		setTitle("Swarm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		board.setBounds(0, 0, 1280, 720);
		board.setLayout(null);

		fpsLabel.setLocation(2, 2);
		board.add(fpsLabel);

		add(board);

		setVisible(true);
		setSize(1280 + getInsets().left + getInsets().right, 720 + getInsets().top + getInsets().bottom);
		setLocationRelativeTo(null);
	}

	public void run() {
		int frames = 0;
		long firstFrameTime = System.currentTimeMillis();

		while (true) {
			final long startTime = System.currentTimeMillis();

			if (++frames == 10) {
				fpsLabel.setText(Long.toString(1000 * frames / (startTime - firstFrameTime)) + " FPS");
				fpsLabel.setSize(fpsLabel.getPreferredSize());
				frames = 0;
				firstFrameTime = startTime;
			}

			board.tick();

			try {
				long sleep = DELAY - System.currentTimeMillis() + startTime;
				if (sleep < 0) {
					sleep = 2;
				}
				Thread.sleep(sleep);
			} catch (final InterruptedException e) {
				System.out.println("Interrupted: " + e.getMessage());
			}
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Swarm();
			}
		});
	}

}
