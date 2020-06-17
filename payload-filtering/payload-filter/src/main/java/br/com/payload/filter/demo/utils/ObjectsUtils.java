package br.com.payload.filter.demo.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import br.com.payload.filter.demo.exceptions.FilterException;
import br.com.payload.filter.demo.exceptions.UnexptedFieldTypeException;

public class ObjectsUtils {

	private ObjectsUtils() {
	}

	public static void validate(Field field, Class<?> clazz, Annotation annotation) throws FilterException {
		if (!field.getType().equals(clazz)) {
			String filterPath = annotation.annotationType().toString().substring(10);

			String errorMessage = String.format("Filter '%s' is not valid for field type '%s'", filterPath,
					field.getType());

			throw new UnexptedFieldTypeException(errorMessage);
		}
	}

}
