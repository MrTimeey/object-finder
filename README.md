# object-finder [![CI](https://github.com/MrTimeey/object-finder/actions/workflows/mvn-build.yml/badge.svg?branch=main)](https://github.com/MrTimeey/object-finder/actions/workflows/mvn-build.yml?query=branch%3Amain) [![Maven Central](https://img.shields.io/maven-central/v/io.github.mrtimeey/object-finder.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.mrtimeey%22%20AND%20a:%22object-finder%22) [![Javadocs](http://www.javadoc.io/badge/io.github.mrtimeey/object-finder.svg)](http://www.javadoc.io/doc/io.github.mrtimeey/object-finder)
The object-finder provides utility functions to identify specific objects in complex data structures.
* [Object-Finder's goals](#goals)
* [TL;DR](#tldr)
* [Example UseCase](#example)
* [Quickstart](#quickstart)

## <a name="goals"/>Object-Finder's goal</a>

Object-Finder's ambition is to provide simple utility methods to find specific objects in complex data structures.
The idea is that you don't want to navigate through the object over and over again. 
Finding various objects in a complex object often leads to multiple same looking methods and duplicated code.

The Object-Finder wants to simply your code. 

## <a name="tldr"/>TL;DR</a>

*You have an object with a nested structure, and you are looking for:*

type `CoverageBundle.class` with a field `id` which contains `fancyId`:
```java 
 Pair location = Pair.of("id", "fancyId");
 Optional<CoverageBundle> result = FindObjectUtils.find(baseObject, location, CoverageBundle.class);
```

type `CoverageBundle.class` with some other value which is nested even more:
 ```java 
 Pair location = Pair.of("metadata/reference/key", "nestedKey");
 Optional<CoverageBundle> result = FindObjectUtils.find(baseObject, location, CoverageBundle.class);
 ```

all type `CoverageBundle.class` instances with a value which is nested even more:
 ```java 
 Pair location = Pair.of("metadata/reference/key", "nestedKey");
 List<CoverageBundle> result = FindObjectUtils.findAll(baseObject, location, CoverageBundle.class);
 ```

For longer example scroll to [Example UseCase](#example).

## <a name="example"/>Example UseCase</a>
Given is a complex data structure:

```json
{
  "id": "e97f6d09-ff23-41e8-94a2-f35f6a99eb9c",
  "productBundle": {
    "id": "886644d1-97b5-4698-b6ee-3ed18d352d95",
    "products": [
      {
        "id": "bdce24b8-d1ae-4fca-b993-e7d509129ada",
        "calculationParts": [
          {
            "id": "02f26e1b-e548-440d-8bfc-559d7c9fb1bd",
            "amount": -5,
            "information": {
              "key": "SPECIAL_DISCOUNT"
            }
          }, 
          {
            "id": "9f1f0368-29b8-40d7-a223-dfb672cc2077",
            "amount": 11,
            "information": {
              "key": "PRICE"
            }
          }
        ]        
      }
    ]
  }
}
```

If you want to get the CalculationPrice object with the id `02f26e1b-e548-440d-8bfc-559d7c9fb1bd` you have to do something like:
```java 
private Optional<CalculationPart> findCalculationPart(Offer offer) {
      return Optional.ofNullable(offer)
          .map(Offer::productBundle)
          .map(ProductBundle::products)
          .orElse(List.of())
          .stream()
          .flatMap(product -> product.calculationParts().stream())
          .filter(calculationPart -> "02f26e1b-e548-440d-8bfc-559d7c9fb1bd".equals(calculationPart.id()))
          .findFirst();   
}
```

You have to go through the whole object structure to find the specific object. 
If you want to find other nodes or identify the object with another predicate you have to build a new method.

The object-helper library can help simplifying your code:
```java
private Optional<CalculationPart> findCalculationPart(Offer offer) {
       Pair<String, String> location = Pair.of("id", "02f26e1b-e548-440d-8bfc-559d7c9fb1bd");
       return FindObjectUtils.find(offer, location, CalculationPart.class);   
}
```

To identify the object you have to define a `io.github.mrtimeey.objectfinder.type.Pair` which defines the matcher.
The object-finder converts the object into a tree and traverses it. On each node the left side of the Pair will be checked if the path exists.
If matched it checks if the node is equal with the right side of the `Pair`.

If you want to identify the object based on a nested structure you can change the `Pair`:
```java
private Optional<CalculationPart> findCalculationPart(Offer offer) {
       Pair<String, String> location = Pair.of("information/key", "SPECIAL_DISCOUNT");
       return FindObjectUtils.find(offer, location, CalculationPart.class);   
}
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


