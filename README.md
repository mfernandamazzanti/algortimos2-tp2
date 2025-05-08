# SIU Enrollment System – TP2 (UBA)

This project was developed for the course "Algorithms and Data Structures" at the University of Buenos Aires (FCEN, UBA).  
It simulates a simplified university enrollment system similar to SIU Guaraní, with support for:

- Degree programs
- Courses (shared across programs under different names)
- Teaching staff and quotas
- Student registration

## 🧱 Key Features

- Implemented in Java with custom data structures (no standard Java collections used except `ArrayList`, `String` and `StringBuffer`).
- All classes include **representation invariants** in natural language.
- Time complexity constraints strictly followed as per the problem specification.
- Use of a **Trie structure** (`Arbolito`) for efficient storage and search of courses.

## 🧩 Main Components

- `SistemaSIU`: Core system logic, manages students and programs.
- `Carrera`: Represents a degree program and its set of courses.
- `Materia`: Represents a course, including enrolled students and teaching staff.
- `Estudiante`: Keeps track of enrolled courses per student.
- `Arbolito` and `Nodo`: Trie used for storing course names.
- `InfoMateria` and `ParCarreraMateria`: Helpers for initializing the system.

## 📎 Specification Compliance

All required operations were implemented with the time complexity bounds defined in the assignment:
- Registering a student: `O(|carrera| + |materia|)`
- Checking overbooked courses: `O(|carrera| + |materia|)`
- Listing student enrollments: `O(1)`
- And more...


