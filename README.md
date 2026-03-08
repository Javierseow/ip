# Jackson User Guide

Jackson is a CLI (Command Line Interface) based task management assistant that helps you keep track of your everyday
tasks. Whether it's a simple to-do or a multi-day event, Jackson has your back.

### Quick Start
1. Ensure you have Java 17 or above installed.
2. Download the latest jackson.jar from our Releases page.
3. Open a terminal, navigate to the folder containing the file, and run:
   `java -jar ip.jar` to run the application
4. You should see the greeting message
```
Hello, I'm Jackson
What can I do for you?
```
5. Type list to see your tasks or follow the instructions below to add new ones!

## Features
### Command Format notes
- Parameters in UPPER_CASE are user inputs (e.g. DESCRIPTION)
- Extra words in commands that don't take parameters (e.g. list or bye) will be ignored
- Commands are case-sensitive


### 1. Adding a Todo: todo
- Adds a basic task without any specific date or time.

Format: `todo DESCRIPTION`

Example: `todo fix my gym routine`

Expected Output:
```
Aight. I've added this task:
  [T][ ] fix my gym routine
Now you have 1 task in the list
```

### 2. Adding a Deadline: deadline
- Adds a task that needs to be done by a specific time.

Format: `deadline DESCRIPTION /by TIME`

Example: `deadline submit assignment /by Monday 2pm`

Expected Output:
```
Aight. I've added this task:
  [D][ ] submit assignment (by: Monday 2pm)
Now you have 2 tasks in the list
```

### 3. Adding an Event: event
- Adds a task that has a start and end time.

Format: `event DESCRIPTION /from START /to END`

Example: `event career fair /from 10am /to 4pm`

Expected Output:
```
Aight. I've added this task:
  [E][ ] career fair (from: 10am to: 4pm)
Now you have 3 tasks in the list
```

### 4. Listing Tasks: list
- Shows all the tasks currently in your list.

Format: `list`

Expected Output:
```
Here's your list bro
1.[T][ ] fix my gym routine
2.[D][ ] submit assignment (by: Monday 2pm)
3.[E][ ] career fair (from: 10am to: 4pm)
```

### 5. Marking Tasks: mark / unmark
- Updates the status of a task to completed or incomplete.

Format: `mark INDEX` or `unmark INDEX` (where INDEX is the number in the list)

Example: `mark 1`

Expected Output:
```
Okay, I've marked task 1 as done
  [T][X] fix my gym routine
```

Example: `unmark 1`

Expected Output:
```
Okay, I've marked task 1 as not done yet
  [T][ ] fix my gym routine
```

### 6. Finding Tasks: find
- Search for tasks that contain a specific keyword in their description.

Format: `find KEYWORD`

Example: `find gym`

Expected Output:
```
Here are the matching tasks in your list:
1.[T][ ] fix my gym routine
```

### 7. Deleting Tasks: delete
- Removes a task from your list permanently.

Format: `delete INDEX` (where INDEX is the number in the list)

Example: `delete 2`

Expected Output:
```
Aight. I've removed this task:
  [D][ ] submit assignment (by: Monday 2pm)
Now you have 2 tasks in the list
```

### 8. Exiting: bye
- Saves your data and closes the application.

Format: `bye`

Expected Output:
```
Bye. Hope to see you again soon!
```

## Data Archiving
- Jackson automatically saves your data in a text file located at `./data/jackson.txt`, and loads it back up
  when u start it up again
- You don't need to manually save or load; Jackson handles it every time you say bye.
