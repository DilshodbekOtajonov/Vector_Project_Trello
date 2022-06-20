import ui.AuthUI;
import uz.jl.BaseUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author "Otajonov Dilshodbek
 * @since 6/16/22 5:16 PM (Thursday)
 * VectorGroupProject/IntelliJ IDEA
 */
public class Application {

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        AuthUI.main(args);


    }
}
