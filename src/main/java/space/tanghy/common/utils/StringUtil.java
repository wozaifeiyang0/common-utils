package space.tanghy.common.utils;

/**
 *
 *  字符串工具类
 * @author tanghy
 */
public class StringUtil {

    private StringUtil() {

    }

    /**
     * 判断字符串是否为空
     * 如果字符串中有空格时为空
     * @param value 字符串
     * @return 返回true或者false
     */
    public static boolean isBlank(String value){
        return value == null || value.length() == 0 || value.trim().length() == 0;
    }

    /**
     * 判断字符串不为空
     *
     * @param value 字符串
     * @return 返回true或者false
     */
    public static boolean isNotBlank(String value){
        return !isBlank(value);
    }


    /**
     * 判断字符串为空
     * 如果字符串中有空格时不为空
     * @param value 字符串
     * @return 返回true或者false
     */
    public static boolean isEmpty(String value){
        return value == null || value.length() == 0;
    }

    /**
     * 判断字符串不为空
     * @param value 字符串
     * @return 返回true或者false
     */
    public static boolean isNotEmpty(String value){
        return !isEmpty(value);
    }

}
