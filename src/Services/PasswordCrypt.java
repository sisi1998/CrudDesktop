
package Services;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Ousse
 */
public class PasswordCrypt {
    private static final int COST = 10; // le coût du hachage, ici 10

    public static String cryptage(String password) {
        String salt = BCrypt.gensalt(COST);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean décryptage(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
}
