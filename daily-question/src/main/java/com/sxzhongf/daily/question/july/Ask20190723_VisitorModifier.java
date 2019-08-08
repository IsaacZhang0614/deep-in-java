package com.sxzhongf.daily.question.july;

/**
 * Ask20190723_VisitorModifier for TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/7/23
 */
public enum Ask20190723_VisitorModifier {
    ONE(1), TEN(10),
    HUNDRED(100) {
        @Override
        public String toString() {
            return Integer.toString(val);
        }
    };
    private final int val; //访问修饰符的限定条件

    Ask20190723_VisitorModifier(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(ONE + " " + TEN + " " + HUNDRED);
    }
}
