package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addToList(Integer.class::isInstance);
    }

    public final NumberSchema range(Integer min, Integer max) {
        addToList(v -> !((Integer) v < min || (Integer) v > max));
        return this;
    }

    public final NumberSchema positive() {
        addToList(v -> (Integer) v > 0);
        return this;
    }
}
