# Date Format

### Status
The current version of API is uses the following format.
("yyyy-MM-dd'T'HH:mm:ss")
The defined format is sufficient for desired accuracy.

### Context
for consistency same format should be throughout the application (example: test, database, application logic). 
Without specifying the format explicitly different operating system can give different results(microsecond, nanosecond).


### Future Improvements
* Move the time format from java class file to application.properties, so can it be defined in one place and changed easily without need to recompile the classes. 


