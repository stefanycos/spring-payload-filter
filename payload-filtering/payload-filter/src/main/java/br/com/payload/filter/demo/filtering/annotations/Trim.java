package br.com.payload.filter.demo.filtering.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.payload.filter.demo.filtering.impl.TrimFilter;


@FilterClass(TrimFilter.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Trim {

}
