package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest {
    public static final int NUMBER_FOR_TESTS_5 = 5;
    public static final int NUMBER_FOR_TESTS_10 = 10;
    public static final int NUMBER_FOR_TESTS_NEGATIVE_5 = -5;

    @Test
    public void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(NUMBER_FOR_TESTS_5));
        assertFalse(schema.isValid(""));

        assertTrue(schema.minLength(1).isValid("five"));
        assertFalse(schema.minLength(NUMBER_FOR_TESTS_10).isValid("five"));

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    public void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(NUMBER_FOR_TESTS_10));
        assertFalse(schema.isValid("5"));
        assertFalse(schema.isValid(NUMBER_FOR_TESTS_NEGATIVE_5));

        schema.range(NUMBER_FOR_TESTS_5, NUMBER_FOR_TESTS_10);

        assertTrue(schema.isValid(NUMBER_FOR_TESTS_5));
        assertTrue(schema.isValid(NUMBER_FOR_TESTS_10));
        assertFalse(schema.isValid(NUMBER_FOR_TESTS_5 - 1));
        assertFalse(schema.isValid(NUMBER_FOR_TESTS_10 + 1));
    }

    @Test
    public void testMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testShape() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", NUMBER_FOR_TESTS_10);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", NUMBER_FOR_TESTS_NEGATIVE_5);
        assertFalse(schema.isValid(human4));
    }
}
