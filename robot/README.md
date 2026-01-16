



# Robot Simulator – Technical Test (Java 17, Spring Boot, Maven)
## Author: Jeffer Dylan Melgarejo Pineda

This project is a simple **robot movement simulator** built as a technical test.
The goal is to show clean code, basic testing, and a clear project structure using a **hexagonal (ports & adapters) style**.

The application reads robot instructions from **standard input (stdin)**, runs the simulation, and prints the **final position of each robot**.

---

## Problem Summary

* The robot moves on a rectangular grid.
* The bottom-left corner is assumed to be `(0, 0)`.
* The first line of input defines the **upper-right corner of the grid**.
* Each robot has:

  * A starting position: `X Y D`
  * A movement instruction string: a sequence of `L`, `R`, and `M`

### Commands

* `L` → Turn left
* `R` → Turn right
* `M` → Move forward one step in the current direction

The output is the **final position and direction** of each robot, one per line.

---

## Example

### Input

```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

### Output

```
1 3 N
5 1 E
```

---

## How to Run

### Requirements

* Java 17
* Git
* No Docker required (Docker support is disabled by default)

### Run with input file (Windows PowerShell)

1. Create a file called `input.txt` in the project root (same level as `pom.xml`).
2. Paste your input into the file.

Run:

```powershell
type input.txt | .\mvnw.cmd spring-boot:run
```

### Run with manual input

```powershell
.\mvnw.cmd spring-boot:run
```

Then paste the input and finish with:

* `Ctrl + Z`
* Press `Enter`

---

## Project Structure (Hexagonal Style)

The project is organized to separate **core logic** from **external details** like input and frameworks.

```
com.ptech_vw_dighub.robot
 ├─ domain
 │   ├─ Direction
 │   ├─ Position
 │   ├─ Robot
 │   └─ Grid
 │
 ├─ application
 │   ├─ RobotProgram
 │   ├─ ParsedInput
 │   ├─ SimulationResult
 │   └─ RobotSimulator
 │
 └─ adapters
     └─ in
         └─ cli
             ├─ InputParser
             └─ RobotCliRunner
```

### What each part does

### `domain`

This is the **core of the system**.

* Contains the robot, its position, direction, and the grid.
* Has no dependency on Spring, CLI, or input format.

### `application`

This is the **use case layer**.

* Coordinates the simulation logic.
* Takes parsed input and produces simulation results.

### `adapters`

This is how the system talks to the outside world.

* `InputParser` reads text input and converts it into objects.
* `RobotCliRunner` connects the CLI (stdin) with the application logic.

### Dependency Flow

```
adapters → application → domain
```

This means:

* The core logic does not depend on the CLI or Spring
* Only the outer layers know about the inner layers

---

## Assumptions

* The grid starts at `(0, 0)`.
* Robots must start inside the grid.
* If a move would place a robot outside the grid, that move is ignored.
* Input format is assumed to be valid.

---

## Testing

The project includes basic unit tests for:

* Input parsing
* Robot simulation logic

Run tests with:

```powershell
.\mvnw.cmd test
```

---

## Notes

* This project is designed as a **CLI application**, not a REST API.
* Docker support is present in the project but disabled by default for easier local execution.
* The structure focuses on **readability and separation of responsibilities** rather than advanced patterns.

---



---

## Why This Design

This structure makes the project easier to:

* Test
* Maintain
* Extend (for example, adding a REST API later instead of CLI)

The core robot logic would stay the same, and only a new adapter would be added.
