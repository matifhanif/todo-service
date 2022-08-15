# Automatically change status to PAST_DUE on expiry of due date

### Status
The current version of API is fulfills the requirement.

### Context
Status of all items with passed due date, should automatically change to PAST_DUE.  

### Decision
Instead of running a background service/ scheduled task, to change the status of expired item, Status is changed on get request. 
All queried item are first check, whether it needs to be altered(change the status of expired items), then send response with updated status. 

### Future Improvements
This implementation should be tested for large data set. Compare the performance with second approach (background service/ scheduled task). Both these approaches along with hybrid approach.

