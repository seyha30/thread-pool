import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
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
		int fibonacciThreadPool = 3;
		int factorialThreadPool = 2;
		String command = null;
		int number = 0;
		System.out.println("Server running");
		try {
			serverSocket = new ServerSocket(9999);
			while (true) {
				System.out.println("The server is waiting for client....");
				Socket socket = serverSocket.accept();
				if ("Fibo".equalsIgnoreCase(command)) {
					pool = Executors.newFixedThreadPool(fibonacciThreadPool);
					FibonacciThreadWorker fibonaciThreadWorker = new FibonacciThreadWorker(socket, index, 10);
					pool.execute(fibonaciThreadWorker);
				}
				if ("Fact".equalsIgnoreCase(command)) {
					pool = Executors.newFixedThreadPool(factorialThreadPool);
					FactorialThreadWorker factorialThreadWorker = new FactorialThreadWorker(socket, index, 20);
					pool.execute(factorialThreadWorker);
				}

				index++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
