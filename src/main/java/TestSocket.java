import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TestSocket implements Runnable {
    private Socket socket;

    public TestSocket(int port) throws IOException {
        socket = new Socket("localhost", port);
    }

    @Override
    public void run() {
        try {
            OutputStream out = socket.getOutputStream();

            for (int i = 1; i <= 5001; i++) {
                Thread.sleep(1);
                String line = "Hello! " + "It is message at number: " + i + "!";

                if (i == 5001) {
                    line = "Bue.";
                }
                out.write(line.getBytes());
                out.flush();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
