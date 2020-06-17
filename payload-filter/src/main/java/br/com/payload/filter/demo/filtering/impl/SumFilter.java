package br.com.payload.filter.demo.filtering.impl;

import java.lang.annotation.Annotation;

import br.com.payload.filter.demo.filtering.annotations.Sum;

public class SumFilter extends Filter<Integer> {

	@Override
	public Object filter(Object object, Annotation annotation, Object fieldValue) {
		Integer value = this.getAnnotationValue(annotation);
		return ((Integer) fieldValue) + value;
	}
	
	private Integer getAnnotationValue(Annotation annotation) {
		Sum sum = (Sum) (annotation);
		return sum.value();
	}

}
