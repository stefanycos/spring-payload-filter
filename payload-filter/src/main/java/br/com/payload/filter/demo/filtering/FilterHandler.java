package br.com.payload.filter.demo.filtering;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import br.com.payload.filter.demo.exceptions.FilterHandlerException;
import br.com.payload.filter.demo.filtering.annotations.FilterClass;
import br.com.payload.filter.demo.filtering.impl.Filter;


public class FilterHandler {

	private static final String MESSAGE_ERROR_INVOKE_FILTER = "Error on trying to filter request body. ";
	private static final String DEFAULT_METHOD = "execute";

	public void doFilter(Object object) {
		Field[] declaredFields = object.getClass().getDeclaredFields(); // @formatter:off

		Stream.of(declaredFields).forEach(field -> 
			this.filterAnnotations(field, object)
		);
	}

	private void filterAnnotations(Field field, Object object) {
		Annotation[] annotations = field.getAnnotations();

		Stream.of(annotations).forEach(annotation -> 
			this.invokeFilter(annotation, field, object)
		);
	} // @formatter:on

	@SuppressWarnings("rawtypes")
	public void invokeFilter(Annotation annotation, Field field, Object object) {
		if (!this.isFiltered(annotation)) {
			return;
		}

		try {
			FilterClass filter = annotation.annotationType().getAnnotation(FilterClass.class);

			Class<? extends Filter> clazz = filter.value();
			Method method = clazz.getMethod(DEFAULT_METHOD, Field.class, Object.class, Annotation.class);

			Filter instance = this.buildInstance(clazz);
			method.invoke(instance, field, object, annotation);

		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			throw new FilterHandlerException(MESSAGE_ERROR_INVOKE_FILTER + e.getMessage());
		} catch (InvocationTargetException e) {
			throw new FilterHandlerException(
					MESSAGE_ERROR_INVOKE_FILTER + e.getTargetException().getMessage());
		}
	}

	private boolean isFiltered(Annotation annotation) {
		return annotation.annotationType().isAnnotationPresent(FilterClass.class);
	}

	@SuppressWarnings("rawtypes")
	private Filter buildInstance(Class<? extends Filter> clazz)
			throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

		Constructor<? extends Filter> constructor = clazz.getConstructor();
		return constructor.newInstance();
	}

}
