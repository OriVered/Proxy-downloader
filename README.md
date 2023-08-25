# Exercise 2 – I/O Streams, HTTP

This project is a console-based program to test some proxy-like functions that control the downloading of URL resources from the web.

## Authors

- *Ori Vered*
- *Damian Tissembaum*

## Documentation
<a href="doc/index.html">API documentation</a>

## Program Features

The program implements the following options:
- Denying access to URLs based on a list of blocked sites that can be added/removed dynamically
- Denying access to resources of type image
- Denying access to resources of type html
- Denying access to resources containing cookies

## Console Commands

1. `b <url>`: Block a URL, adds the URL to the list of blocked URL saves to the file.
2. `u <url>`: Unblock the URL and updates the file.
3. `p`: Prints the current list of blocked sites, alphabetically ordered.
4. `q`: Exit the program.
5. `d <-options> <url> <out>`: Download the given URL contents to a file out according to denying options defined by one character code.

### Denying Options
- `i`: Block images (HTTP header Content-Type starts with “image/”)
- `c`: Block HTTP responses containing cookies (in HTTP headers response)
- `h`: Block HTML document (HTTP header Content-Type == “text/html”)
- `b`: Deny access to blocked sites unless any I/O error occurs with the blocked.txt file

## Design Patterns

The program has been designed using the following design patterns:
- **Decorator** - denying options command tool, which uses the decorator pattern to apply different blockers dynamically
- **Singleton's** -  CommandManager, IOManager

The program follows the Single Responsibility Principle and the principles of encapsulation. It is divided into different classes, each with a single responsibility.

## Error Handling

Custom exceptions have been defined and used when no suitable Java exceptio
- **QuitException:** When the user enters the "q" command to quit the program, the loop terminates.
- **InvalidCommandException:** When the user enters an unknown command, the program prints "invalid command".
- **InvalidURLException:** When the user enters an invalid URL, the program prints "invalid URL".
- **InvalidOptionException:** When the user enters an invalid option for the download command, the program prints "invalid option".
- **IOException:** When there is a problem reading or writing files, the program prints "cannot read/write blocked.txt" or "cannot write output file".

## Adding a New Blocker

To add a new blocker, such as blocking JavaScript files, follow these steps:

1. Create a new class that extends the `BlockerDecorator` class and implements the `BlockerCommand` interface.
2. In the new class, override the `execute` method to add the blocking logic specific to the new blocker (e.g., checking the content type for JavaScript files).
3. In the `BlockedOption` class, create a new KEY (e.g: "j"), at the buildCommands function instance of the new blocker class for add it as a decorator to the chain of blockers.
4. Add a new option character for the new blocker in the `CommandLineParser` class.
5. Update the list of denying options in the README file to include the new blocker and its option character.

## How to Compile and Run the Program
- Compile the program by running the command `javac *.java`.
- Run the program by running the command `java Main`.