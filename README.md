# pointschallenge
#health 
I have implemented a custom health check class . The implementation returns a boolean for the time being . this can be modified to test if database or messageing service is available
check below url to see application health 
http://localhost:8080/actuator/health

#testing
test case is present in the code to test the api available at /api/points
the case was passed with the sample data provided in the test class. 
use "mvn clean install" to run test and build code

#dataset
Data set is hardcoded in the test cases  . 

Here is the example data set used for testing

  List<Transactions> transactionList=Arrays.asList(
            new Transactions("Adam", 120, "08/2023"),
            new Transactions("Rakesh", 120, "08/2023"),
            new Transactions("Adam", 90, "07/2023"),
            new Transactions("Adam", 120, "07/2023")
        );


# request body for postman testing

[
	{
	"name":"Adam",
	"amount":120,
	"date":"07/2023"
	},
	{
	"name":"Adam",
	"amount":90,
	"date":"07/2023"
	}
]