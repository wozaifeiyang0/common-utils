package site.tanghy.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author tanghy
 * @description 解决double与float类型操作精度问题工具
 * @date 2020-01-17 5:28 下午
 */
public class NumberUtil {

    private static DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
    private static final int DEF_DIV_SCALE = 10;

    private NumberUtil() {

    }

    /**
     * 将double数据转为字符串，并且去掉尾数0
     * @param value 数据
     * @return 返回字符串
     */
    public static String format(Object value) {

        String format;
        format = decimalFormat.format(value);
        return format;

    }

    /**
     * double 类型相加解决解决精度问题
     * @param v1 数据1
     * @param v2 数据2
     * @return double
     */
    public static double add(String v1, String... v2) {

        BigDecimal b1 = new BigDecimal(v1);
        for (String value : v2) {
            BigDecimal temp = new BigDecimal(value);
            b1 = b1.add(temp);
        }

        return b1.doubleValue();

    }


    /**
     * double 类型相加解决解决精度问题
     * @param v1 数据1
     * @param v2 数据列表
     * @return 数据
     */
    public static double add(double v1, double... v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        for (double value : v2) {
            BigDecimal temp = new BigDecimal(Double.toString(value));
            b1 = b1.add(temp);
        }

        return b1.doubleValue();

    }

    /**
     * 两个double相减
     * @param v1 数据1
     * @param v2 数据列表
     * @return 数据
     */
    public static double sub(double v1, double... v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        for (double value : v2) {
            BigDecimal temp = new BigDecimal(Double.toString(value));
            b1 = b1.subtract(temp);
        }

        return b1.doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1
     *            被乘数
     * @param v2
     *            乘数列表
     * @return 两个参数的积
     */

    public static double mul(double v1, double... v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        for (double value : v2) {
            BigDecimal temp = new BigDecimal(Double.toString(value));
            b1 = b1.multiply(temp);
        }

        return b1.doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1
     *            被除数
     * @param v2
     *            除数列表
     * @return 两个参数的商
     */

    public static double div(double v1, double... v2)
    {
        return div(DEF_DIV_SCALE, v1, v2);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1
     *            被除数
     * @param v2
     *            除数列表
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    public static double div(int scale, double v1, double... v2)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        for (double value : v2) {
            BigDecimal temp = new BigDecimal(Double.toString(value));
            b1 = b1.divide(temp, scale, BigDecimal.ROUND_HALF_UP);
        }

        return b1.doubleValue();
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * double 类型相加解决解决精度问题
     * @param v1 数据1
     * @param v2 数据列表
     * @return 数据
     */
    public static float add(float v1, float... v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        for (double value : v2) {
            BigDecimal temp = new BigDecimal(Double.toString(value));
            b1 = b1.add(temp);
        }

        return b1.floatValue();

    }

    /**
     * 两个double相减
     * @param v1 数据1
     * @param v2 数据列表
     * @return 数据
     */
    public static float sub(float v1, float... v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        for (double value : v2) {
            BigDecimal temp = new BigDecimal(Double.toString(value));
            b1 = b1.subtract(temp);
        }

        return b1.floatValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1
     *            被乘数
     * @param v2
     *            乘数列表
     * @return 两个参数的积
     */

    public static float mul(float v1, float... v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        for (double value : v2) {
            BigDecimal temp = new BigDecimal(Double.toString(value));
            b1 = b1.multiply(temp);
        }

        return b1.floatValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1
     *            被除数
     * @param v2
     *            除数列表
     * @return 两个参数的商
     */

    public static float div(float v1, float... v2)
    {
        return div(DEF_DIV_SCALE, v1, v2);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1
     *            被除数
     * @param v2
     *            除数列表
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    public static float div(int scale, float v1, float... v2)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        for (double value : v2) {
            BigDecimal temp = new BigDecimal(Double.toString(value));
            b1 = b1.divide(temp, scale, BigDecimal.ROUND_HALF_UP);
        }

        return b1.floatValue();
    }

}
