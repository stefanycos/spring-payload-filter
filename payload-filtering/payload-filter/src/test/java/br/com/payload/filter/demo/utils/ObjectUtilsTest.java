package br.com.payload.filter.demo.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.junit.Test;

import br.com.payload.filter.demo.exceptions.UnexptedFieldTypeException;
import br.com.payload.filter.demo.filtering.annotations.Trim;
import br.com.payload.filter.demo.helper.User;

public class ObjectUtilsTest {
	
	
	@Test(expected = UnexptedFieldTypeException.class )
	public void givenDiffentClassType_thenThrowsException() throws Exception {
		
		User user = new User();
		Field field = user.getClass().getDeclaredField("name");
		Annotation annotation = field.getDeclaredAnnotation(Trim.class);
		
		ObjectsUtils.validate(field, Double.class, annotation);
	}

}
