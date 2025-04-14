/*import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AccountsTable implements Runnable {

    private final JTableModel tableModel;
    private JFrame frame;
    private JTextField usernameField, passwordField, platformField;

    public AccountsTable() {
        this.tableModel = new JTableModel();
    }

    @Override
    public void run() {
        frame = new JFrame("Accounts");
        frame.setIconImage(new ImageIcon(getClass().getResource("/images/icon3.png")).getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.decode("#E7F6FF"));

        frame.add(createTablePanel(), BorderLayout.CENTER);
        frame.add(createAddPanel(), BorderLayout.EAST);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.decode("#E7F6FF"));

        JTable table = new JTable(tableModel.getTableModel());
        table.setBackground(Color.decode("#E7F6FF"));
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBackground(Color.decode("#E7F6FF"));

        return panel;
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.decode("#E7F6FF"));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 5, 5);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel label = new JLabel("Username:");
        label.setBackground(Color.decode("#E7F6FF"));
        panel.add(label, gbc);
        gbc.gridx++;
        usernameField = new JTextField(20);
        usernameField.setBackground(Color.decode("#E7F6FF"));
        panel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        label = new JLabel("Password:");
        label.setBackground(Color.decode("#E7F6FF"));
        panel.add(label, gbc);
        gbc.gridx++;
        passwordField = new JTextField(20);
        passwordField.setBackground(Color.decode("#E7F6FF"));
        panel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        label = new JLabel("Platform:");
        label.setBackground(Color.decode("#E7F6FF"));
        panel.add(label, gbc);
        gbc.gridx++;
        platformField = new JTextField(20);
        platformField.setBackground(Color.decode("#E7F6FF"));
        panel.add(platformField, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;

        JButton button = new JButton("Add Account or Reload");
        button.setBackground(Color.decode("#496B6F"));
        button.setToolTipText("Add accounts or leave empty to load accounts!");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                String platform = platformField.getText().trim();

                File fileAcc = new File("emi_Accounts.csv");

                if( !(username.isEmpty() && password.isEmpty() && platform.isEmpty())) {
                    FileWriter fw;
                    try {
                        fw = new FileWriter(fileAcc, true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    BufferedWriter bw = new BufferedWriter(fw);

                    try {
                        String key="key";

                        bw.write(EncryptionDecryption.encrypt(key,username) + ',');
                        bw.write(EncryptionDecryption.encrypt(key,password) + ',');
                        bw.write(EncryptionDecryption.encrypt(key,platform)+'\n');
                        bw.close();
                        tableModel.addAccount(new Account(username, password, platform));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (username.isEmpty() || password.isEmpty() || platform.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Reloading data...");

                    FileReader fr;
                    try {
                        fr = new FileReader(fileAcc);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    BufferedReader br = new BufferedReader(fr);

                    String line = "Accounts";

                    while (line != null) {
                        try {
                            line = br.readLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        String[] lineSplit;
                        if (line != null) {

                            lineSplit = line.split(",");
                            if(lineSplit.length==3) {
                                String key="key";
                                username=EncryptionDecryption.decrypt(key,lineSplit[0]);
                                password=EncryptionDecryption.decrypt(key,lineSplit[1]);
                                platform=EncryptionDecryption.decrypt(key,lineSplit[2]);
                                tableModel.addAccount(new Account(username,password,platform));
                            }

                        }
                    }
                }

                clearInputFields();

            }

            private void clearInputFields() {
                usernameField.setText("");
                passwordField.setText("");
                platformField.setText("");

                usernameField.requestFocus();
            }
        });

        panel.add(button, gbc);
        frame.getRootPane().setDefaultButton(button);
        return panel;
    }

    public class JTableModel {

        private final DefaultTableModel tableModel;

        public JTableModel() {
            this.tableModel = new DefaultTableModel();
            tableModel.addColumn("Username");
            tableModel.addColumn("Password");
            tableModel.addColumn("Platform");
        }

        public void addAccount(Account account) {
            Object[] rowData = new Object[3];
            rowData[0] = account.getPlatform();
            rowData[1] = account.getUsername();
            rowData[2] = account.getPassword();
            tableModel.addRow(rowData);
        }

        public DefaultTableModel getTableModel() {
            return tableModel;
        }

    }

    public class Account {

        private final String platform, username, password;

        public Account(String platform, String username, String password) {
            this.platform = platform;
            this.username = username;
            this.password = password;
        }

        public String getPlatform() {
            return platform;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

} */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AccountsTable implements Runnable {

    private final JTableModel tableModel;
    private JFrame frame;
    private JTextField usernameField, passwordField, platformField;
    private Image backgroundImage;

    public AccountsTable() {
        this.tableModel = new JTableModel();
    }

    @Override
    public void run() {
        frame = new JFrame("Accounts");
        frame.setIconImage(new ImageIcon(getClass().getResource("/images/icon3.png")).getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        try {
            backgroundImage = ImageIO.read(new File("src/images/desert-beige.jpg")); // same image as login UI
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        backgroundPanel.add(createTablePanel(), BorderLayout.CENTER);
        backgroundPanel.add(createAddPanel(), BorderLayout.EAST);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTable table = new JTable(tableModel.getTableModel());
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setShowGrid(false);
        table.setFillsViewportHeight(true);
        table.setSelectionBackground(new Color(70, 130, 180));
        table.setSelectionForeground(Color.WHITE);
        table.setBackground(new Color(255, 255, 255, 180));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(new Color(64, 224, 208));
        header.setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);

        JLabel label;

        gbc.gridx = 0;
        gbc.gridy = 0;
        label = new JLabel("Username:");
        label.setFont(labelFont);
        label.setForeground(Color.WHITE);
        panel.add(label, gbc);
        gbc.gridy++;
        usernameField = new JTextField(20);
        panel.add(usernameField, gbc);

        gbc.gridy++;
        label = new JLabel("Password:");
        label.setFont(labelFont);
        label.setForeground(Color.WHITE);
        panel.add(label, gbc);
        gbc.gridy++;
        passwordField = new JTextField(20);
        panel.add(passwordField, gbc);

        gbc.gridy++;
        label = new JLabel("Platform:");
        label.setFont(labelFont);
        label.setForeground(Color.WHITE);
        panel.add(label, gbc);
        gbc.gridy++;
        platformField = new JTextField(20);
        panel.add(platformField, gbc);

        gbc.gridy++;
        JButton button = new JButton("Add Account or Reload");
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(64, 224, 208));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                String platform = platformField.getText().trim();

                File fileAcc = new File("emi_Accounts.csv");

                if (!(username.isEmpty() && password.isEmpty() && platform.isEmpty())) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileAcc, true))) {
                        String key = "key";
                        bw.write(EncryptionDecryption.encrypt(key, username) + ',');
                        bw.write(EncryptionDecryption.encrypt(key, password) + ',');
                        bw.write(EncryptionDecryption.encrypt(key, platform) + '\n');
                        tableModel.addAccount(new Account(username, password, platform));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (username.isEmpty() || password.isEmpty() || platform.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Reloading data...");
                    try (BufferedReader br = new BufferedReader(new FileReader(fileAcc))) {
                        String line;
                        String key = "key";
                        while ((line = br.readLine()) != null) {
                            String[] lineSplit = line.split(",");
                            if (lineSplit.length == 3) {
                                username = EncryptionDecryption.decrypt(key, lineSplit[0]);
                                password = EncryptionDecryption.decrypt(key, lineSplit[1]);
                                platform = EncryptionDecryption.decrypt(key, lineSplit[2]);
                                tableModel.addAccount(new Account(username, password, platform));
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                clearInputFields();
            }

            private void clearInputFields() {
                usernameField.setText("");
                passwordField.setText("");
                platformField.setText("");
                usernameField.requestFocus();
            }
        });

        panel.add(button, gbc);
        frame.getRootPane().setDefaultButton(button);
        return panel;
    }

    public class JTableModel {
        private final DefaultTableModel tableModel;

        public JTableModel() {
            this.tableModel = new DefaultTableModel();
            tableModel.addColumn("Username");
            tableModel.addColumn("Password");
            tableModel.addColumn("Platform");
        }

        public void addAccount(Account account) {
            Object[] rowData = new Object[3];
            rowData[0] = account.getPlatform();
            rowData[1] = account.getUsername();
            rowData[2] = account.getPassword();
            tableModel.addRow(rowData);
        }

        public DefaultTableModel getTableModel() {
            return tableModel;
        }
    }

    public class Account {
        private final String platform, username, password;

        public Account(String platform, String username, String password) {
            this.platform = platform;
            this.username = username;
            this.password = password;
        }

        public String getPlatform() {
            return platform;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}