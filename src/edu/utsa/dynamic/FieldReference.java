package edu.utsa.dynamic;

import java.lang.reflect.Field;

import edu.utsa.exceptions.PluginError;

// Simulate a pointer-to-member from C++ by using reflection
public class FieldReference<T>
{
    private Object owner;
    private Class<T> fieldType;
    private Field field;

    public FieldReference(Object owner, Class<T> fieldType, String fieldName) {
        this.owner = owner;
        this.fieldType = fieldType;
        Class<?> clazz = owner.getClass();
        try {
            this.field = clazz.getField(fieldName);
        }
        catch (Exception e) {
            throw new PluginError(e, "Cannot access \"%s\" in type %s.", fieldName,
                    fieldType.getSimpleName());
        }
    }

    public void setValue(T newValue) {
        try {
            field.set(owner, newValue);
        }
        catch (Exception e) {
            throw new PluginError(e);
        }
    }

    public T getValue() {
        try {
            return fieldType.cast(field.get(owner));
        }
        catch (Exception e) {
            throw new PluginError(e);
        }
    }
}
