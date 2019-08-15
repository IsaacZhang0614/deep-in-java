package com.sxzhongf.deep.in.jdk;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CustomEnumMemberGet for : TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/15
 */
public class CustomEnumMemberGet {

    public static void main(String[] args) {
        Stream.of(Count.values()).forEach(System.out::println);
    }
}

enum CouuntEnum {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private int data;

}

/**
 * 这个传统的枚举类 ，javap -v className,之后
 * 生成的字节码和上面的枚举是一样的。
 */
final class Count {

    public static final Count ONE = new Count(1);
    public static final Count TWO = new Count(2);
    public static final Count THREE = new Count(3);
    public static final Count FOUR = new Count(4);
    public static final Count FIVE = new Count(5);

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private int data;

    public Count(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Count{" +
                "data=" + data +
                '}';
    }

    /**
     * 自定义实现 JVM 对枚举的提升（枚举会自动生成values[]）
     *
     * @return
     */
    public static Count[] values() {
        //获取当前枚举对象
        Field[] declaredFields = Count.class.getDeclaredFields();

//        return Stream.of(declaredFields)
        return Arrays.stream(declaredFields)
                .filter(
                        field -> {
                            // 过滤枚举中的非对外属性
                            int modifiers = field.getModifiers();
                            return Modifier.isPublic(modifiers)
                                    && Modifier.isStatic(modifiers)
                                    && Modifier.isFinal(modifiers);
                        }
                ).map(
                        field -> {
                            try {
                                // 需要强转一次
                                return (Count) field.get(null);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).collect(Collectors.toList()).toArray(new Count[0]);
    }
}