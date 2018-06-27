package fishblog.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*sha加密*/
public class SecurityUtil {

    public static String sha1(String str) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest digest = MessageDigest.getInstance("sha1");
            // 放入加密字符串
            digest.update(str.getBytes());
            // 进行加密
            byte[] digestMsg = digest.digest();
            // byte转换16进制
            for (byte b : digestMsg) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

   /* public static void main(String[] args) {
        BigDecimal aa = new BigDecimal("4839.97").multiply(new BigDecimal("0.00051419169"));
    }*/

}