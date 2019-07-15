package com.sxzhongf.daily.question.july;

import java.util.HashSet;
import java.util.Set;

/**
 * Ask20190710_Object_Equals_HashCode for Ask20190710_Object_Equals_HashCode
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/7/10
 */
public class Ask20190710_Object_Equals_HashCode {

    private String first, last;

    public Ask20190710_Object_Equals_HashCode(String first, String last) {
        if (first == null || last == null) {
            throw new NullPointerException();
        }
        this.first = first;
        this.last = last;
    }

    public boolean equals(Ask20190710_Object_Equals_HashCode o) {
        return first.equals(o.first) && last.equals(o.last);
    }

    public int hashCode() {
        return 31 * first.hashCode() + last.hashCode();
    }

    public static void main(String[] args) {
        Set s = new HashSet<>();
        s.add(new Ask20190710_Object_Equals_HashCode("Isaac", "Zhang"));
        System.out.println(s.contains(new Ask20190710_Object_Equals_HashCode("Isaac", "Zhang")));
    }
}
