import javax.bluetooth.*;

public class Main {
    public static void main(String[] args) {
        User user;
        try {
            Bluetooth blue = new Bluetooth();
            user = blue.getUser();
        } catch (BluetoothStateException e) {
            throw new RuntimeException(e);
        }

        App app = new App(user);
        app.initializeUI();
        app.handleInput();
    }
}