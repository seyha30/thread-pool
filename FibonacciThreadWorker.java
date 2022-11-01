import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author seyha.sin Nov 1, 2022/2022 : 9:38:57 AM
 */
public class FibonacciThreadWorker extends Thread {

	private Socket socket = null;
	private int index = 0;
	private int number = 0;

	public FibonacciThreadWorker(Socket socket, int inde, int number) {
		this.socket = socket;
		this.index = inde;
		this.number = number;
	}

	@Override
	public void run() {
		try {
			System.out.println("----------------------------------------");
			System.out.println("Thread-" + index + " serves connection from " + socket.getInetAddress() + ":" + socket.getPort());
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("Thread-" + index + " is calculating fibonaci");
			long f = fib(number);
			System.out.println("Thread-" + index + " is sending result");
			out.println("Fibonacci ==>>> " + f);
			System.out.println("----------------------------------------");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private long fib(long n) {
		if (n == 0)
			return 0L;
		if (n == 1)
			return 1L;
		return (fib(n - 1) + fib(n - 2));
	}

}
