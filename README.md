# Address Book System - Java IO

A console-based Address Book application built incrementally using **Git Flow**.

## Project Overview

This project demonstrates managing contacts in an Address Book using Java, progressing through 13 Use Cases (UC1–UC13), each building on the previous.

## Features (Use Cases)

| UC | Feature |
|----|---------|
| UC1 | Add a new Contact |
| UC2 | Edit existing Contact by name |
| UC3 | Delete a Contact by name |
| UC4 | Add multiple Contacts using Collections |
| UC5 | Multiple Address Books with unique names |
| UC6 | Duplicate Entry check using Streams |
| UC7 | Search Person by City or State across books |
| UC8 | View Persons by City or State (Dictionary) |
| UC9 | Count contacts by City or State |
| UC10 | Sort contacts alphabetically by Name |
| UC11 | Sort contacts by City, State, or Zip |
| UC12 | Alphabetical sort with toString override |
| UC13 | Read/Write Address Book to File using Java IO |

## Git Flow Strategy

- **`master`** – README only (stable release point)
- **`develop`** – Latest integrated code
- **`feature/uc1` … `feature/uc13`** – Individual UC branches (kept alive)

## Branch Workflow

```bash
git flow feature start uc<N>
# implement UC code
git add .
git commit -m "[Manas]: UC<N> - <description>"
git flow feature finish -k uc<N>
git push origin develop
git push origin feature/uc<N>
```

## Commit Convention

All commits follow: `[Manas]: <message>`

## How to Run

```bash
# Compile
javac -d out src/main/java/com/addressbook/*.java

# Run
java -cp out com.addressbook.AddressBookMain
```

## Technology

- Java (Console-based)
- Maven build
- Java Collections Framework
- Java IO (BufferedReader/BufferedWriter)
- Java Streams API
- Git Flow branching strategy
