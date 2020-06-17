package br.com.payload.filter.demo.filtering.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import br.com.payload.filter.demo.exceptions.FieldFilterException;
import br.com.payload.filter.demo.exceptions.FilterException;
import br.com.payload.filter.demo.utils.ObjectsUtils;

public abstract class Filter<Type> {

	private Class<Type> acceptedType;

	@SuppressWarnings("unchecked")
	public Filter() {
		this.acceptedType = (Class<Type>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public abstract Object filter(Object object, Annotation annotation, Object fieldValue);

	public void execute(Field field, Object object, Annotation annotation) throws FilterException {
		ObjectsUtils.validate(field, acceptedType, annotation);
		Object fieldValue = this.getFieldValue(field, object);
		Object filteredValue = this.filter(object, annotation, fieldValue);
		this.setFieldValue(field, object, filteredValue);
	}

	private Object getFieldValue(Field field, Object object) throws FilterException {
		try {
			field.setAccessible(true);
			return field.get(object);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new FieldFilterException("Error when obtaining value from field: " + field.getName());
		}
	}

	private void setFieldValue(Field field, Object object, Object value) throws FilterException {
		try {
			field.set(object, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new FieldFilterException("Error when setting value on field: " + field.getName());
		}
	}

}
