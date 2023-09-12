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

# sample data set
	
![Screenshot 2023-09-11 9 21 31 PM](https://github.com/rocky07/pointschallenge/assets/1245181/332e1bcf-092d-428f-bf7e-bdf9882f8c35)



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

# postman collection

{
	"variables": [],
	"info": {
		"name": "a",
		"_postman_id": "fc2e6dfb-3681-e12d-2bd5-a4700714b23d",
		"description": "",
		"schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json"
	},
	"item": [
		{
			"name": "api/points",
			"request": {
				"url": "https://refactored-space-palm-tree-7vw6g4rwg4frwjp-8080.app.github.dev/api/points",
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"description": ""
					}
				],
				"body": {
					"mode": "raw",
					"raw": "[\n\t{\n\t\"name\":\"Adam\",\n\t\"amount\":120,\n\t\"date\":\"07/2023\"\n\t},\n\t{\n\t\"name\":\"Adam\",\n\t\"amount\":90,\n\t\"date\":\"07/2023\"\n\t}\n]"
				},
				"description": ""
			},
			"response": []
		}
	]
}


#unit test code added below . the test class is also checked in 

@SpringBootTest
@AutoConfigureMockMvc
public class PointsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCalculateRewards() throws Exception {

        List<Transactions> transactionList=Arrays.asList(
            new Transactions("Adam", 120, "08/2023"),
            new Transactions("Rakesh", 120, "08/2023"),
            new Transactions("Adam", 90, "07/2023"),
            new Transactions("Adam", 120, "07/2023")
        );

          mockMvc.perform(MockMvcRequestBuilders
                .post("/api/points")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(transactionList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.Adam.08/2023").value(90))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Adam.07/2023").value(130));

              
    }

    private String asJson(List<Transactions> transactionList) {
        try{
        return new ObjectMapper().writeValueAsString(transactionList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
