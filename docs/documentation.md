# Documentation Service 7 Persons Team 1

___
## Contents
1. [Introduction](#introduction)
2. [FAQ](#faq)
3. [Diagrams](#diagrams)
   1. [Main Diagrams](#main-diagrams)
   2. [Use Case Diagram](#use-case-diagram)
   3. [Relations Diagram](#relations-diagram)
4. [API](#api)





___
## Introduction

This documentation is for service persons which is part of the backbone for the FH-Erfurt mobile app and others.
It contains diagrams and code to access and manage persons.

#### Authors
* Jonas Liehmann(DevLead)
* Tobias Kärst(PO)
* Niklas Schumann
* Justin Noske





___
## FAQ
> ### Q: Can I select certain return attributes of a person?
> #### A: Yes, you can we have dto's declared for this. You can build your own person.





___
## Diagrams

### Main Diagrams
![img_2.png](https://i.imgur.com/jM3omn8.png)
![img_2.png](https://i.imgur.com/omYJqyj.png)



### Use Case Diagram
![img.png](https://i.imgur.com/8xeFPEZ.png)

### Relations Diagram
![img_1.png](https://i.imgur.com/toW7cCs.png)


___
## Api
_* add available Methods and Attributes_

#### Example
````bash
String json = """ {
    "firstname": "Max",
    "lastname": "Mustermann",
    "faculty": "Angewandte Informatik"
}
"""

Optional<Person> person = builder
        .addValidator(new PersonFilledValidator())
        .fromJSON(json)
        .build();
````
#### Example
````bash
new ValidationErrorBuilder<String>()
  .withCondition(new ValidationCondition<String>)
        .withValue(person.getFirstname())
        .isRequired("firstname")
  .withCondition(new ValidationCondition<String>)
        .withValue(person.getFirstname())
        .isBetween(2,30,"firstname")
  .addTo(errors);
````
#### Example
````bash
Optional<Person> person = builder
  .addValidator(new PersonFilledValidator())
  .withName("Max", "Mustermann")
  .build();
````

#### Example
````bash
Optional<Person> person = builder
  .addValidator(new PersonFilledValidator())
  .fromPerson(oldPerson)
  .withName("Paul", "Mustermann")
  .build();
````