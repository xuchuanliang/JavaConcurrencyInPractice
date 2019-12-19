package JavaCoreThreadPatten.capter06;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ThreadSpecificSecureRandom {
    public static final ThreadLocal<SecureRandom> THREAD_LOCAL = new ThreadLocal<SecureRandom>() {
        @Override
        protected SecureRandom initialValue() {
            try {
                SecureRandom sha1PRNG = SecureRandom.getInstance("SHA1PRNG");
                return sha1PRNG;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }
    };
    public int nextInt(){
        return THREAD_LOCAL.get().nextInt();
    }

}
