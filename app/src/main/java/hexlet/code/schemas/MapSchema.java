package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema() {
    }

    @Override
    public final MapSchema required() {
        addToList(Map.class::isInstance);
        return this;
    }

    public final MapSchema sizeof(int size) {
        addToList(v -> ((Map<?, ?>) v).size() == size);
        return this;
    }
    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        addToList(v -> ((Map<?, ?>) v).keySet().stream()
                .allMatch(key -> schemas.get(key).isValid(((Map<?, ?>) v).get(key))));
        return this;
    }
}
