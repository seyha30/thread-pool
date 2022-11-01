import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author seyha.sin Nov 1, 2022/2022 : 9:34:47 AM
 */
public class Server {
	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		ExecutorService pool = null;

		int index = 0;
		final int FIBONACCI_THREAD_POOL = 3;
		final int FACTORIAL_THREAD_POOL = 2;
		String command = "Fibo";
		int number = 10;
		System.out.println("Server running" + command);
		try {
			serverSocket = new ServerSocket(8888);
			Socket socket = serverSocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("The server is waiting for client...." + serverSocket.getInetAddress());
			while (true) {
				System.out.println(in.readLine());
				if ("Fibo".equalsIgnoreCase(command)) {
					pool = Executors.newFixedThreadPool(FIBONACCI_THREAD_POOL);
					FibonacciThreadWorker fibonaciThreadWorker = new FibonacciThreadWorker(socket, index, number);
					pool.execute(fibonaciThreadWorker);
				}
				if ("Fact".equalsIgnoreCase(command)) {
					pool = Executors.newFixedThreadPool(FACTORIAL_THREAD_POOL);
					FactorialThreadWorker factorialThreadWorker = new FactorialThreadWorker(socket, index, number);
					pool.execute(factorialThreadWorker);
				}

				index++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
