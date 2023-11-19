# object-finder [![CI](https://github.com/MrTimeey/object-finder/actions/workflows/mvn-build.yml/badge.svg?branch=main)](https://github.com/MrTimeey/object-finder/actions/workflows/mvn-build.yml?query=branch%3Amain) [![Maven Central](https://img.shields.io/maven-central/v/io.github.mrtimeey/object-finder.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.mrtimeey%22%20AND%20a:%22object-finder%22) [![Javadocs](http://www.javadoc.io/badge/io.github.mrtimeey/object-finder.svg)](http://www.javadoc.io/doc/io.github.mrtimeey/object-finder)
The object-finder provides utility functions to identify specific objects in complex data structures.
* [Object-Finder's goals](#goals)
* [TL;DR](#tldr)
* [Longer example](./exampleUseCase.md)
* [Quickstart](#quickstart)

## <a name="goals"/>Object-Finder's goal</a>

Object-Finder's ambition is to provide simple utility methods to find specific objects in complex data structures.
The idea is that you don't want to navigate through the object over and over again. 
Finding various objects in a complex object often leads to multiple same looking methods and duplicated code.

The Object-Finder wants to simply your code. 

## <a name="tldr"/>TL;DR</a>

### Get object by field
```java
      Pair<String, Object> location = Pair.of("id", "02f26e1b-e548-440d-8bfc-559d7c9fb1bd");
      Optional<CalculationPart> result = FindObjectUtils.find(baseObject, location, CalculationPart.class);
      assertThat(result).isPresent();
```

### Get object by nested object value
```java 
      Pair<String, Object> location = Pair.of("information/key", "SPECIAL_DISCOUNT");
      Optional<CalculationPart> result = FindObjectUtils.find(baseObject, location, CalculationPart.class);
      assertThat(result).isPresent();
```

### Get object by string in list
 ```java 
      Pair<String, Object> location = Pair.of("names[]", "specialDiscount");
      Optional<CalculationPart> result = FindObjectUtils.find(baseObject, location, CalculationPart.class);
      assertThat(result).isPresent();
 ```

### Get object by nested object value in list
 ```java 
      Pair<String, Object> location = Pair.of("properties[id]", "d9c40d29-e828-4c15-9519-29891496ec8e");
      Optional<CalculationPart> result = FindObjectUtils.find(baseObject, location, CalculationPart.class);
      assertThat(result).isPresent();
 ```

### Get all objects by nested object value

```java
      Pair<String, Object> location = Pair.of("information/key", "SPECIAL_DISCOUNT");
      List<CalculationPart> result = FindObjectUtils.findAll(baseObject, location, CalculationPart.class);
      assertThat(result).hasSize(2);
```

## <a name="quickstart"/>Quickstart</a>

Add Maven Dependency:
```xml
<dependency>
  <groupId>io.github.mrtimeey</groupId>
  <artifactId>object-finder</artifactId>
  <version>1.0.1</version>
</dependency>
```


