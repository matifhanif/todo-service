# Automated Test

### Status
The current version of API is covered with limited number of automated test cases.

### Context
for reliability detail automated test should be added.

### Decision
* For repository, Unit test is performed.
* For Service, Mock test is performed, by mocking the repository api, since the custom repository methods are already tested.

### Future Improvements
* Detailed unit and integration test coverage will be added in the next version.
* Add Rest Assured for black-box testing

