package hexlet.code.schemas;

import java.util.Objects;

public class StringSchema extends BaseSchema {
    public StringSchema() {
    }

    @Override
    public final StringSchema required() {
        addToList(String.class::isInstance);
        addToList(v -> !Objects.equals(v, ""));
        return this;
    }

    public final StringSchema contains(String subString) {
        addToList(v -> ((String) v).contains(subString));
        return this;
    }

    public final StringSchema minLength(int minLength) {
        addToList(v -> ((String) v).length() >= minLength);
        return this;
    }
}
