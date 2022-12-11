package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Predicate;

public class Validator {

    public final StringSchema string() {
        return new StringSchema();
    }

    public final NumberSchema number() {
        return new NumberSchema();
    }

    public final MapSchema map() {
        return new MapSchema();
    }

    public static boolean validate(Instance value, List<Predicate<Object>> list) {
        List<Field> fields = List.of(value.getClass().getDeclaredFields());
        return fields.stream()
                .filter(field -> field.isAnnotationPresent(ValidData.class))
                .allMatch(field -> list.stream()
                        .allMatch(predicate -> {
                            try {
                                field.setAccessible(true);
                                return predicate.test(field.get(value));
                            } catch (IllegalAccessException e) {
                                System.out.println(e + "Field access error! Sorry, try again");
                            }
                            return false;
                        }));
    }
}
//    то же самое решение, но без Stream Api
//    public static boolean validate(Instance item, List<Predicate<Object>> list) {
//        for (Field field : item.getClass().getDeclaredFields()) {
//            ValidData validData = field.getAnnotation(ValidData.class);
//            if (validData != null) {
//                try {
//                    field.setAccessible(true);
//                    for (Predicate<Object> p : list) {
//                        if (!p.test(field.get(item))) {
//                            return false;
//                        }
//                    }
//                } catch (IllegalAccessException e) {
//                    System.out.println(e + "Field access error! Sorry, try again");
//                }
//            }
//        }
//        return true;
//    }
