package hexlet.code;

public class Instance {
    @ValidData // проверка валидности добавляемых значений в поле класса Instance
    private final Object value;

    public Instance(Object pValue) {
        this.value = pValue;
    }
}
