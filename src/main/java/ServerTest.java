
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTest {
    public static void main(String[] args) throws IOException {
        Server server = Server.getServer(5050);
        boolean isFinished = false;
        long startTime = System.currentTimeMillis();
        while (!isFinished) {
            isFinished = server.start();
        }
        server.close();

        System.out.println(System.currentTimeMillis() - startTime);
    }
}

class SocketTest {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new MySocket(5050));
        executorService.shutdown();
    }
}

