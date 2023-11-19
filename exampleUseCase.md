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

The object-finder library can help simplifying your code:
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