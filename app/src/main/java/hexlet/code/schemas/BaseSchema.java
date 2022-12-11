package hexlet.code.schemas;

import hexlet.code.Instance;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static hexlet.code.Validator.validate;

public abstract class BaseSchema {

    private final List<Predicate<Object>> predicates = new ArrayList<>();

    public final void addToList(Predicate<Object> predicate) {
        predicates.add(predicate);
    }

    public final boolean isValid(Object value) {
        return validate(new Instance(value), predicates);
    }

//    вариант решения без создания объекта класса Instance и проверки заполнения его поля value
//    в этом случае метод Validator.validate не требуется
//    public final boolean isValid(Object value) {
//        return predicates.stream()
//                .allMatch(predicate -> predicate.test(value));
//    }

    public abstract BaseSchema required();
}
