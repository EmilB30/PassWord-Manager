import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import javax.imageio.ImageIO;
import static java.lang.Thread.sleep;

public class LoginForm extends JFrame {

    private Image backgroundImage;

    public LoginForm() {
        setTitle("PassWord Manager Login");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);

        try {
            backgroundImage = ImageIO.read(new File("src/images/desert-beige.jpg")); // Replace with actual path
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentPane(new BackgroundPane());
        getContentPane().setLayout(null);

        LoginPanel loginPanel = new LoginPanel();
        loginPanel.setBounds(250, 150, 300, 300);
        getContentPane().add(loginPanel);
    }

    private class BackgroundPane extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    private class LoginPanel extends JPanel {
        public LoginPanel() {
            setOpaque(false);
            setLayout(null);

            JLabel titleLabel = new JLabel("Welcome Back!");
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setBounds(80, 20, 200, 30);
            add(titleLabel);

            JTextField userField = new JTextField();
            userField.setBounds(50, 70, 200, 30);
            add(userField);

            JPasswordField passField = new JPasswordField();
            passField.setBounds(50, 120, 200, 30);
            add(passField);

            JButton loginButton = new JButton("Login");
            loginButton.setBounds(100, 180, 100, 30);
            loginButton.setFocusPainted(false);
            add(loginButton);

            JButton closeButton = new JButton("X") {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(204, 244, 228, 40)); // Turquoise
                    g2.fill(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
                    g2.setColor(new Color(255, 45, 45, 229));
                    FontMetrics fm = g2.getFontMetrics();
                    String text = getText();
                    int x = (getWidth() - fm.stringWidth(text)) / 2;
                    int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                    g2.drawString(text, x, y);
                    g2.dispose();
                }
            };
            closeButton.setBounds(260, 10, 20, 20);
            closeButton.setMargin(new Insets(0, 0, 0, 0));
            closeButton.setContentAreaFilled(false);
            closeButton.setBorderPainted(false);
            closeButton.setFocusPainted(false);
            closeButton.setForeground(Color.WHITE);
            closeButton.addActionListener(e -> System.exit(0));
            add(closeButton);


            SwingUtilities.invokeLater(() -> {
          System.setProperty("awt.useSystemAAFontSettings", "on");
          System.setProperty("swing.aatext", "true");
      });

            File usersFile = new File("users.csv");
        FileReader fr;
        try {
            fr = new FileReader(usersFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(fr);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = userField.getText();
                String passwordFieldText = passField.getText();

                String line = "user+passwordFieldText";
                Boolean loginProces=true;

                while (line != null && loginProces) {
                    try {
                        line = br.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    String[] lineSplit = new String[]{""};
                    if (line != null) {
                        lineSplit = line.split(",");

                        String md5Pass = MD5Encryption.md5Encrypt(passwordFieldText);

                        if (username.equals(lineSplit[0]) && md5Pass.equals(lineSplit[1])) {

                            loginProces=false;
                            JOptionPane.showMessageDialog(LoginForm.this, "Te-ai logat cu succes, acum ai acces la conturile tale!","Verificare...",JOptionPane.INFORMATION_MESSAGE);
                            /// new window with table
                            SwingUtilities.invokeLater(new AccountsTable());

                        } else{ if(!username.equals(lineSplit[0])){
                            JOptionPane.showMessageDialog(LoginForm.this, "User inexistent!","Verificare...",JOptionPane.INFORMATION_MESSAGE);
                            try {
                                sleep(200);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                            System.exit(1);

                         }else if(!md5Pass.equals(lineSplit[1])) {
                            JOptionPane.showMessageDialog(LoginForm.this, "Parola incorecta!", "Verificare...", JOptionPane.INFORMATION_MESSAGE);
                            try {
                                sleep(200);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                           }
                          System.exit(1);
                        }
                       }

                 }
              }

            }
        });

        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
            g2.setColor(Color.BLACK);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
            g2.dispose();
            super.paintComponent(g);
        }
    }

}