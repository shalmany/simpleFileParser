# SimpleFileParser

Simple File Parser is an application that support the processing multithreading of file and  provide support for calculating the number of words, number of dots and most used word in the file content.
The application uses some patterns such as :

- The Producer Consumer Pattern to processing multithreading.You can control the capacity of the file queue and number of the file consumers.
- The Composite pattern to support new  statistics in the future.To implement a new functionality, we need implement the interface AnalyticsService.
- The Strategy and Factory pattern  for supporting multiple file types in the future. To add a new functionality, we need implement the interface FileParserService and update FileParserFactory.


## Requirements

- Java 17
- Maven 3.8.+

## Installation


```bash
mvn clean install
mvn exec:java -Dexec.mainClass=com.hicx.simplefileparser.SimpleFileParserApplication
```

## Usage
After start an application will ask to enter directory path to scan like below:
```bash
Enter directory:
```
Enter directory path like example :
```bash
/home/dev/hicx/files
```
After that The application will start to scan directory :
```bash
start scan : /home/dev/hicx/files
```
For the file processed the application will print information and move to subdirectory "processed":
```bash
file result: /home/dev/hicx/files/demo.txt Thread:Thread-1
MOST_USED_WORDS:house
NUMBER_OF_DOTS:4
NUMBER_OF_WORDS:14
```





