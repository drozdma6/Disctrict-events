# District events

- Backend (Rest api) for web application used for sharing upcoming events in local district.

## Technologies

- Java
- Spring-boot
- Spring-data
- Spring-security
- Hibernate
- H2 database

## Complex server query

- Select all events that are organized in some village (from list of villages) and match given date.
- Select all events created by user.

## Client business operation

- Delete all canceled events created by signed user.
  - Get all user's events. (provided by server),
  - Go through them one by one and delete them if they are marked as canceled.

## Data model

![Data Model](dataModel.png)

## Frontend
- [Figma](https://www.figma.com/file/0NcVXePiftwx3kBkyfE1IS/District-Events?node-id=0%3A1)

