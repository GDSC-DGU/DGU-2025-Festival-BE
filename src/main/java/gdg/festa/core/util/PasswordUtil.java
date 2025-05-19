package gdg.festa.core.util;

public class PasswordUtil {
    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        int passwordLength = (int) (Math.random() * 11) + 10;

        for (int i = 0; i < passwordLength; i++) {
            int type = (int) (Math.random() * 3);
            switch (type) {
                case 0:
                    password.append((char) ((int) (Math.random() * 26) + 65));
                    break;
                case 1:
                    password.append((char) ((int) (Math.random() * 26) + 97));
                    break;
                case 2:
                    password.append((char) ((int) (Math.random() * 10) + 48));
                    break;
            }
        }
        return password.toString();
    }
}
