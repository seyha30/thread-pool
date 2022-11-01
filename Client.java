import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author seyha.sin Nov 1, 2022/2022 : 9:45:36 AM
 */
public class Client {

	public static void main(String[] args) {

		Socket socket = null;
		if (args.length < 2) {

			System.out.println("client" + args.length);
			System.exit(0);
		}
		try {
			socket = new Socket("localhost", 9999);
			// Create input and output streams to read from and write to the server
			PrintStream out = new PrintStream(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(args[0]);
			out.println(args[1]);
			String line = in.readLine();
			while (line != null) {
				System.out.println(line);
				line = in.readLine();
			}
			// Close our streams
			in.close();
			out.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
