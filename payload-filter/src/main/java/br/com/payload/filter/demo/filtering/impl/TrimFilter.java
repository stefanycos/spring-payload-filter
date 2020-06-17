package br.com.payload.filter.demo.filtering.impl;

import java.lang.annotation.Annotation;

public class TrimFilter extends Filter<String> {

	@Override
	public Object filter(Object object, Annotation annotation, Object fieldValue) {
		return ((String) fieldValue).trim();
	}

}
