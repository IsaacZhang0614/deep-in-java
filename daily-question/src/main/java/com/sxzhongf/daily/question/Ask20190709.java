package com.sxzhongf.daily.question;

/**
 * Ask20190709 for 「小马哥每日一问」2019.07.09 期
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/7/9
 */
public class Ask20190709 {

    public static void main(String[] args) {
        final int start = Integer.MAX_VALUE - 100;
        final int end = Integer.MAX_VALUE;
        int count = 0;
        for (int i = start; i <= end; i++) {
            count++;
            //当i == Integer.MAX_VALUE开始，就会永远为true, 会内存溢出
        }
        System.out.println(count);
    }
}
