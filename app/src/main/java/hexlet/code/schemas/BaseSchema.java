package hexlet.code.schemas;

import hexlet.code.requirements.NotRequired;
import hexlet.code.requirements.Required;
import hexlet.code.requirements.Requirements;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static hexlet.code.Validator.validate;

public abstract class BaseSchema {

    private Requirements requirement; // State
    private final List<Predicate<Object>> predicates = new ArrayList<>();

    public BaseSchema() {
        requirement = new NotRequired();
    }

    public final BaseSchema required() {
        requirement = new Required();
        return this;
    }

    public final String getCurrentState() {
        return requirement.getCurrentState();
    }

    public final void addToList(Predicate<Object> predicate) {
        predicates.add(predicate);
    }

    public final boolean isValid(Object value) {
        return validate(value, /*this.*/getCurrentState()) || predicates.stream()
                .allMatch(predicate -> predicate.test(value));
    }
}
