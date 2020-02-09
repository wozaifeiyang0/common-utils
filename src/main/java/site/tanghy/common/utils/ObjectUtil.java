package site.tanghy.common.utils;

/**
 *
 * 解决对象操作工具
 * @author tanghy
 */
public class ObjectUtil {

    public static boolean isEmpty(Object o) {

        if (o == null) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isNotEmpty(Object o) {

        return !isEmpty(o);

    }
}
