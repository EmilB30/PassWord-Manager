import javax.swing.*;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });

        /*String pass = "abc1";
        String encryptedPass;

        encryptedPass = MD5Encryption.md5Encrypt(pass);
        System.out.println("pass->" + pass);
        System.out.println("EncryptPass->" + encryptedPass);

        /// AES cyphertext

        String data = "Test 123 1231 123123";
        String key = "AESkey";
        String encrypted = EncryptionDecryption.encrypt(key, data);
        System.out.println(encrypted);
        System.out.println(EncryptionDecryption.decrypt(key, encrypted));*/

    }
}