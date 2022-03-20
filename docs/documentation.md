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
> #### A: Yes, we have dto's declared for this. You can build your own person.





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
## API

### [Interface](https://github.com/fh-erfurt/ws2021_team_1_service_7_persons/blob/master/person-client/src/main/java/de/fherfurt/persons/client/PersonClient.java#L17)

### [PersonResource](https://github.com/fh-erfurt/ws2021_team_1_service_7_persons/blob/master/person-client/src/main/java/de/fherfurt/persons/client/PersonClient.java#L17) methods:
```bash
Optional<PersonDto> findById(int id);
Optional<PersonDto> findByEmail(String email);
List<PersonDto> findByFaculty(int facultyId);
List<PersonDto> findByName(String name);
void deleteBy(int id);
```

### [Getters](https://github.com/fh-erfurt/ws2021_team_1_service_7_persons/blob/master/person-client/src/main/java/de/fherfurt/persons/client/objects/PersonDto.java#L21)
```bash
int id
String email
Salutation salutation
String firstname
String lastname
String username
String phone
String imageUrl
String fax
int facultyId
List<String> titles
List<String> positions
```
>`PersonDtoInstance.get<attribute>()`

### Example get id of person by mail
```java
class myClass {
   PersonResource PersonServerClient = PersonResource.of();

   // Get Person by Mail
   PersonDto myPerson = PersonServerClient.findByEmail("test@fh-erfurt.de").get();

   // Get ID of this Person
   Int myPersonId = myPerson.getId();
}
```