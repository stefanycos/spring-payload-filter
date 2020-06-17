# Spring Payload Filter
Demostration of how to use advantages of Java Reflection with Spring [RequestBodyAdvice](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/RequestBodyAdvice.html)

### Requirements
* Maven
* JDK 1.8

#### Running payload-filter-usage
* mvn spring-boot:run

#### Projects Description
* **payload-filter:** this library offers an option to filter a request body with the purpose of clean/filter the desired fields, developed making usage only of [Java Reflection](https://www.oracle.com/technical-resources/articles/java/javareflection.html).

* **payload-filter-usage:** demo project showing how to use payload-filter library with Spring Boot.

#### Usage
* In a nutshell making usage of annotations over the fields the request payload will be filtered.

1 - Add library dependency
```xml
<dependency>
  <groupId>br.com.payload.filter</groupId>
  <artifactId>payload-filter</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```
2 - Implements RequestBodyAdvice interface
* A sample can be found at: **br.com.payload.filter.usage.config.RequestBodyAdvice**

3 - Add @Filtered annotation to request body
```java
@PostMapping
public User create(@RequestBody @Filtered User payload) {
  return payload;
}
```
4 - Add desired annotations over the fields
```java
public class User {
  private String id;
	
  @Trim
  private String name;
	
  @Trim
  @Prefix("ROLE_")
  private String role;

}
```
### Results
Request
```json
{
    "id": "15d4s6d4as85dbhfg454545aadsa",
    "name": "Stefany Souza  ",
    "role": "admin "
}
```

Response
```json
{
    "id": "15d4s6d4as85dbhfg454545aadsa",
    "name": "Stefany Souza",
    "role": "ROLE_admin"
}
```

### Creating filters
 Since this is a demostration I've created just three sample filters, but it's possible to create your own filters creating a Annotation and an extending Filter abstract class, like bellow

- Create the annotation, replace @FilteredClass by your Filter.
```java
@FilterClass(FooFilter.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Foo {
	
	// optional
	int value();
	
}
```
- Create the filter class
```java
public class FooFilter extends Filter<Integer> {

	@Override
	public Object filter(Object object, Annotation annotation, Object fieldValue) {
		// YOUR FILTER LOGIC
	}
	
	// getting annotation parameters
	private Integer getAnnotationValue(Annotation annotation) {
		Foo foo = (Foo) (annotation);
		return foo.value();
	}

}
```
