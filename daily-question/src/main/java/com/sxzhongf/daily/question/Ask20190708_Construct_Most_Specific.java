package com.sxzhongf.daily.question;

/**
 * Ask20190708_Construct_Most_Specific for 「小马哥每日一问」2019.07.08 期
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/7/9
 */
public class Ask20190708_Construct_Most_Specific {

    public Ask20190708_Construct_Most_Specific(Object o) {
        System.out.println("Object");
    }

    public Ask20190708_Construct_Most_Specific(double[] dArray) {
        System.out.println("double array.");
    }

    public static void main(String[] args) {
        new Ask20190708_Construct_Most_Specific(null);
    }
}
