# SampleFundTransferService
This is a prototype for SampleFundTransferService that provides RESTful api related to customer's fund transfer.

## Running the application

Go to the project root directory and run following commands:  
```
./mvnw clean install
./mvnw spring-boot:run
```

Go to Swagger UI at http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

![](./src/main/resources/static/swagger_overview.png)

### Sample Responses per api
### 1. Create a Transfer
- Request body
```json
{
  "fromAccount": "123456789",
  "toAccount": "987654321",
  "amount": 100.50,
  "description": "Payment for invoice #123"
}
```
#### Sample Save Req/Res instance -   
Request:   
![](./src/main/resources/static/fundtransfer_save_request.png)    
Response:  
![](./src/main/resources/static/fundtransfer_save_response_success.png)    

- Sample response for Validation criteria
    - `amount` must be greater than zero and less than 20000   
  #### Sample req/res for amount less than zero:   
  ![](./src/main/resources/static/fundtransfer_save_request_negative_amt.png)
  ![](./src/main/resources/static/fundtransfer_save_negative_amt_response.png)
  
  #### Sample req/res for amount greater than 20000:  
  ![](./src/main/resources/static/fundtransfer_save_large_amt_req.png)
  ![](./src/main/resources/static/fundtransfer_save_large_amt_res.png)
  
  - A user can't create more than 10 pending transactions at a time    
  #### Sample request for user creating fund transfer over limit:
  ![](./src/main/resources/static/fundtransfer_save_over_limit.png)
  ![](./src/main/resources/static/fundtransfer_save_request_oveer_limit_response.png)
  
### 2. Get Transfer Details
#### Sample req/res for get fund transfer by id:
![](./src/main/resources/static/fundtransfer_get_by_id.png)

### 3. Get All Transfers for a User
#### Sample req/res for get fund transfer by user(customerId):
![](./src/main/resources/static/fundtransfer_get_by_customer.png)  
![](./src/main/resources/static/fundtransfer_get_by_customer_2.png)  

### 4. Cancel a Transfer
#### Sample req/res for fund transfer cancel request:
Currently we are not persisting cancelled transfers and they are hard deleted.

![](./src/main/resources/static/fundtansfer_cancel.png)

Customers active fund transfers after deletion:
![](./src/main/resources/static/fundtransfer_get_by_customer_after_cancel.png)

