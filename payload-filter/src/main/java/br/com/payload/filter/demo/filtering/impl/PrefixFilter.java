package br.com.payload.filter.demo.filtering.impl;

import java.lang.annotation.Annotation;

import br.com.payload.filter.demo.filtering.annotations.Prefix;

public class PrefixFilter extends Filter<String> {

	@Override
	public Object filter(Object object, Annotation annotation, Object fieldValue) {
		String prefix = getAnnotationValue(annotation);

		return prefix + fieldValue;
	}

	private String getAnnotationValue(Annotation annotation) {
		Prefix prefix = (Prefix) (annotation);
		return prefix.value();
	}

}
