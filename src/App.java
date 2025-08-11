import javax.bluetooth.BluetoothStateException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class App {
    private JFrame window;
    private final Dimension screenSize;
    private final User user;
    private JTextField input;
    private final ColorPallete color;

    public App(User user) {
        this.user = user;
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.color = new ColorPallete();
    }

    public void initializeUI() {
        createWindow();
        setupUI();
        window.revalidate();
        window.repaint();
    }

    public void createWindow() {
        window = new JFrame("BlueLink");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setAlwaysOnTop(true);
        window.setSize(screenSize);
        window.setLocation(0, 0);
        window.setFocusTraversalKeysEnabled(true);
        window.setVisible(true);
    }

    private void setupUI() {
        window.getContentPane().setBackground(color.darkest());
        window.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(color.darkest());
        mainPanel.setBorder(new EmptyBorder(80, 80, 80, 80));

        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainGbc.insets = new Insets(30, 0, 60, 0);

        JPanel addressPanel = createUserInfoPanel();
        mainPanel.add(addressPanel, mainGbc);

        mainGbc.gridy = 1;
        mainGbc.insets = new Insets(40, 0, 20, 0);
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, mainGbc);
        mainGbc.gridy = 2;
        mainGbc.insets = new Insets(60, 0, 0, 0);
        JPanel instructionsPanel = createInstructionsPanel();
        mainPanel.add(instructionsPanel, mainGbc);

        window.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createUserInfoPanel() {
        JPanel addressPanel = new JPanel(new GridBagLayout());
        addressPanel.setBackground(color.darkest());
        addressPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.dark(), 2),
                new EmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(8, 0, 8, 0);

        JLabel titleLabel = new JLabel("Your Bluetooth Device");
        titleLabel.setForeground(color.lightest());
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 25, 0);
        addressPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(8, 0, 4, 0);
        JLabel nameDesc = new JLabel("Device Name:");
        nameDesc.setForeground(color.light());
        nameDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        gbc.gridy = 1;
        addressPanel.add(nameDesc, gbc);

        JLabel nameLabel = new JLabel(user.name);
        nameLabel.setForeground(color.lightest());
        nameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        addressPanel.add(nameLabel, gbc);

        JLabel addressDesc = new JLabel("Bluetooth Address:");
        addressDesc.setForeground(color.light());
        addressDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        gbc.gridy = 3;
        gbc.insets = new Insets(8, 0, 4, 0);
        addressPanel.add(addressDesc, gbc);

        JLabel addressLabel = new JLabel(user.address);
        addressLabel.setForeground(color.lightest());
        addressLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 8, 0);
        addressPanel.add(addressLabel, gbc);

        return addressPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(color.darkest());
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.dark(), 2),
                new EmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 20, 0);

        JLabel inputTitle = new JLabel("Connect to Device");
        inputTitle.setForeground(color.lightest());
        inputTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        inputPanel.add(inputTitle, gbc);

        JLabel inputDesc = new JLabel("Enter the target Bluetooth address:");
        inputDesc.setForeground(color.light());
        inputDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 15, 0);
        inputPanel.add(inputDesc, gbc);

        input = new JTextField(20);
        input.setForeground(color.darkest());
        input.setBackground(color.lightest());
        input.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.light(), 2),
                new EmptyBorder(8, 12, 8, 12)
        ));
        input.setHorizontalAlignment(JTextField.CENTER);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        inputPanel.add(input, gbc);

        return inputPanel;
    }

    private JPanel createInstructionsPanel() {
        JPanel instructionsPanel = new JPanel(new GridBagLayout());
        instructionsPanel.setBackground(color.darkest());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel instructionLabel = new JLabel("Press ESC to exit");
        instructionLabel.setForeground(color.dark());
        instructionLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
        instructionsPanel.add(instructionLabel, gbc);

        return instructionsPanel;
    }

    public void handleInput() {
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });

        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (input.getText().length() == 12) {
                    try {
                        Bluetooth bluetooth = new Bluetooth();
                        bluetooth.checkInput(input.getText());
                    } catch (BluetoothStateException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        SwingUtilities.invokeLater(() -> input.requestFocusInWindow());
    }
}