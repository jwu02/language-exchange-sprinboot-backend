# Language Exchange App Backend with Spring Boot

- `git update-index --assume-unchanged src/main/resources/application.properties`
  - stop tracking `application.properties` to hide password
  - assign `spring.datasource.password` property with MySQL database password

## TODOS
- registration
  - obtain list of languages and their codes
    - scrape from https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
    - data/objects stored in a JSON file, for further sanitisation/update
    - then saved to language repository on app start up if haven't done so
  - save user registration data to database