import java.io.*;

public class OrderItemIDServer implements Serializable {
    private int idCounter;
    private static OrderItemIDServer server;

    private OrderItemIDServer() {
        idCounter = 1;
    }

    public static OrderItemIDServer instance() {
        if (server == null) {
            return (server = new OrderItemIDServer());
        }
        else {
            return server;
        }
    }

    public int getID() {
        return idCounter++;
    }

    public String toString() {
        return ("IdServer" + idCounter);
    }

    public static void retrieve(ObjectInputStream input) {
        try {
            server = (OrderItemIDServer) input.readObject();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

    private void writeObject(java.io.ObjectOutputStream output) throws IOException {
        try {
            output.defaultWriteObject();
            output.writeObject(server);
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
        try {
            input.defaultReadObject();
            if (server == null) {
                server = (OrderItemIDServer) input.readObject();
            }
            else {
                input.readObject();
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
