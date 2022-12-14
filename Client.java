import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author seyha.sin Nov 1, 2022/2022 : 9:45:36 AM
 */
public class Client {

	public static void main(String[] args) {

		Socket socket = null;
		if (!checkLength(args)) {
			System.out.println("incorrect command");
			System.exit(0);
		}
		try {
			socket = new Socket("localhost", 8888);
			// Create input and output streams to read from and write to the server
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			send request server
			out.println(args[0] + " " + args[1]);
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

	public static Boolean checkLength(String[] args) {
		Boolean permit = false;
		if (args.length >= 2)
			permit = true;
		return permit;
	}
}
