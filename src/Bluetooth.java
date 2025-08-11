import javax.bluetooth.*;
import javax.microedition.io.*;
import java.io.IOException;

public class Bluetooth {

    private final LocalDevice localDevice;
    private final User user;

    private  final UUID SERVICE_UUID = new UUID("1105", true);
    private static final String OBEX_OBJECT_PUSH_URL_FORMAT = "btgoep://%s:6";

    public Bluetooth() throws BluetoothStateException {
        localDevice = LocalDevice.getLocalDevice();
        user = new User(
                localDevice.getFriendlyName(),
                localDevice.getBluetoothAddress()
        );
    }

    public void lookForPeers() {
        try {
            localDevice.setDiscoverable(DiscoveryAgent.GIAC);
            String url = "btspp://localhost:" + SERVICE_UUID + ";name=" + localDevice.getFriendlyName();
            StreamConnectionNotifier notifier = (StreamConnectionNotifier) Connector.open(url);
            System.out.println("Server started, waiting for clients...");
            while (true) {
                StreamConnection connection = notifier.acceptAndOpen();
                System.out.println("Client connected!");
            }
        } catch (BluetoothStateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void connectToPeer(String address) {
        String connectionURL = String.format(OBEX_OBJECT_PUSH_URL_FORMAT, address);
        try {
            StreamConnection connection = (StreamConnection) Connector.open(connectionURL);
            System.out.println("Successfully connected to device: " + address);
            connection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkInput(String address) {
        if (address.length() == 12) {
            connectToPeer(address);
        } else {
            System.out.println("Ne");
        }
    }



    public User getUser() {
        return user;
    }
}