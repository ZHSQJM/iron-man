import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/7 19:02
 * @package: PACKAGE_NAME
 * @description:
 */
public class Test {

    public static void main(String[] args) {

        System.out.println(new BCryptPasswordEncoder().encode("blog"));
    }
}
