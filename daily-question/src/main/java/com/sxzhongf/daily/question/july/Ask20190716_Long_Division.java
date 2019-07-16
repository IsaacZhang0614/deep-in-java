package com.sxzhongf.daily.question.july;

/**
 * Ask20190716_Long_Division for TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/7/16
 */
public class Ask20190716_Long_Division {

    private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
    private static final Long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000L;

    public static void main(String[] args) {
        System.out.println(
                MICROS_PER_DAY / MILLIS_PER_DAY
        );
    }
}
