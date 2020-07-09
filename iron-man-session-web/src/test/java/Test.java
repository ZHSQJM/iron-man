import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLOutput;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/7 17:09
 * @package: PACKAGE_NAME
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("secret"));
    }
}
