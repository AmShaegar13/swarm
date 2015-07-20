package swarm;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Swarm extends JFrame implements Runnable {

    private static final long DELAY = 17; // 17ms ~ 60 FPS

    private final Board board;

	public Swarm() {
		super();
        board = new Board();
		initGUI();

        new Thread(this).start();
	}

	private void initGUI() {
		add(board);

		setTitle("Swarm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		pack();
		setLocationRelativeTo(null);

		setVisible(true);
	}

    public void run() {
        while (true) {
            final long startTime = System.currentTimeMillis();

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
