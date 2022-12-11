package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
    }

    @Override
    public final NumberSchema required() {
        addToList(Integer.class::isInstance);
        return this;
    }

    public final NumberSchema range(Integer min, Integer max) {
        addToList(v -> !((Integer) v < min || (Integer) v > max));
        return this;
    }

    public final NumberSchema positive() {
        addToList(v -> v == null || (v instanceof Integer && ((Integer) v) > 0));
        return this;
    }
}
