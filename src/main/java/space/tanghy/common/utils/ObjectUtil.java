package space.tanghy.common.utils;

/**
 *
 * 解决对象操作工具
 * @author tanghy
 */
public class ObjectUtil {

    /**
     *  判断对象o是否为空对象
     * @param o 对象
     * @return 如果是空返回true，如果不是返回false
     */
    public static boolean isEmpty(Object o) {

        if (o == null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断对象是否不为空
     * @param o 对象
     * @return 如果为空返回false，如果不为空返回true
     */
    public static boolean isNotEmpty(Object o) {

        return !isEmpty(o);

    }
}
