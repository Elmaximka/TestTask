
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {

    private static Server server;
    private ServerSocket serverSocket;
    private Socket socket;
    private static final Logger LOGGER = Logger.getLogger("src/main/java/Server.java");

    /**
    Конструктор для создания сервера, котоый будет слушать передаваемые ему сообщения
       @param port порт для на котором будет располагаться сервер
     */
    private Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        LOGGER.info("Server started");
        this.socket = serverSocket.accept();
    }

    /**
     * Инстанцирование сервера.
     * @param port порт на котором будет располагаться сервер
     * @return singleton объект
     * @throws IOException возможно соединение не создастся или по данному порту уже создано соединение
     */
    public static Server getServer(int port) throws IOException {
        if (server == null) {
            server = new Server(port);
        }
        return server;
    }

    /**
     * Метод закрывает соединение
     * @throws IOException соединение может не закрыться
     */
    public void close() throws IOException {
        serverSocket.close();
        LOGGER.info("Socket closed.");
    }

    /**
     * Запуск считывания сервером данных из потока. Считывает до тех пор, пока не получит сообщение "Bue."
     * @return Для запуска работы в цикле возвращает значение, по которому можно понять как завершилась работа метода.
     * true - искомая строка получена и поток закрыт, false - работает дальше
     */
    public boolean start() {

        byte[] buf = new byte[32 * 1024];

        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            int readBytes = in.read(buf);
            String line = new String(buf, 0, readBytes);
            System.out.println("Server got message: " + line);

            out.write(line.getBytes());
            out.flush();
            if (line.equals("Bue.")) {
                socket.close();
                in.close();
                out.close();
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
