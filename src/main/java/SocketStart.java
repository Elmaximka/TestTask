import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketStart {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new TestSocket(5050));
        executorService.shutdown();
    }
}
class ServerStart{
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
