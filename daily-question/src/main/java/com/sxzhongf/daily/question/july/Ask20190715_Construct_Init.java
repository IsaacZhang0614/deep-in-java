package com.sxzhongf.daily.question.july;

import java.util.Calendar;

/**
 * Ask20190715_Construct_Init for TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/7/15
 */
public class Ask20190715_Construct_Init {

    private static final Ask20190715_Construct_Init INSTANCE = new Ask20190715_Construct_Init();
    private final int beltSize;
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    private Ask20190715_Construct_Init() {
        beltSize = CURRENT_YEAR - 1930;
    }

    public int getBeltSize() {
        return this.beltSize;
    }

    public static void main(String[] args) {
        System.out.println("Ask20190715_Construct_Init wears size " + INSTANCE.getBeltSize() + " belt");
    }
}
