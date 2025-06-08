# Video-Game-Backlog-Sorter
Final Project for my Data Structures Class, demonstrates a video game sorting system which helps a user organize and select a game from their backlog. Uses stacks, queues, and a quicksort algorithm.

## Description

This project provides an interactive way to sort and filter a video game collection using different criteria. It includes:

- A priority queue to display games based on user-selected filters
- A stack to track skipped games so users can move backward if needed
- A "maybe" list (ArrayList) to mark games for later review
- Multiple comparators to sort games by price, length, excitement, purchase date, and release date

All data is handled in-memory (no file I/O required), and the program runs directly in the terminal.

## How to Run
```bash
git clone https://github.com/JWesP23/Video-Game-Backlog-Sorter.git
cd Video-Game-Backlog-Sorter
javac *.java
java BacklogSorter
```

### Requirements

- Java JDK 11 or higher
- A terminal or command line (e.g., Git Bash, Command Prompt, or IntelliJ Terminal)

##Files Overview

BacklogSorter.java – Main class; handles user interaction and logic
VideoGame.java – Represents a game in the backlog
GameCollection.java – Stores and manages the full game collection
QuickSort.java – Sorting algorithm
Comparators:
  - PriceComparator
  - LengthComparator
  - ExcitementComparator
  - ReleaseDateComparator
  - DatePurchasedComparator
  - ReverseComparator

##Dependencies

No external libraries required — this project uses only standard Java libraries.

##Features

  - View games based on various filters
  - Mark games as "maybe" for later
  - Move backward and forward through suggestions
  - Easily extendable with new comparators
