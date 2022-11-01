import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

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
		System.out.println("Server running");
		try {
			serverSocket = new ServerSocket(9999);
			while (true) {
				System.out.println("Thread Pool Fibonaci is waiting for client....");
				Socket socket = serverSocket.accept();
				FibonacciThreadWorker FibonaciThreadWorker = new FibonacciThreadWorker(socket, index, 10);
				pool.execute(FibonaciThreadWorker);
				index++;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
