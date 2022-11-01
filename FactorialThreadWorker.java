import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author seyha.sin Nov 1, 2022/2022 : 9:42:36 AM
 */
public class FactorialThreadWorker extends Thread {
	private Socket socket = null;
	private int index = 0;
	private int number = 0;

	public FactorialThreadWorker(Socket socket, int index, int number) {
		this.socket = socket;
		this.index = index;
		this.number = number;
	}

	@Override
	public void run() {
		try {
			System.out.println("----------------------------------------");
			System.out.println("Thread-" + index + " serves connection from " + socket.getInetAddress() + ":" + socket.getPort());
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			System.out.println("Thread-" + index + " is calculating factorial");
			long f = factorial(number);

			System.out.println("Thread-" + index + " is sending result");
			out.println("" + f);
			System.out.println("----------------------------------------");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	int factorial(int n) {
		if (n == 0)
			return 1;
		else
			return (n * factorial(n - 1));
	}
}
