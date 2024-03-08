Hi,

1. Pre-requisite: Install Java and Docker Desktop on you local machine.

2. Run the external Services: 
   Build the images and run the containers of ZooKeeper, Kafka and Redis by running the command:
   docker-compose up

3. Run the facingapi app as spring boot app using you favorite IDE:
   a. From Intellij IDE - navigate to facingapi direcory, find FacingapiApplication class, click on the "play" button.
      Else, build and run by maven or any other tool.

4. Run the manager app as spring boot app using you favorite IDE:
   a. From Intellij IDE - navigate to manager direcory, find ManagerApplication class, click on the "play" button.
      Else, build and run by maven or other tool.

5. In the attached Postman collection, run the requests 
   a. Create purchase - Handle a “buy” request and publish the data object to Kafka.
   b. Get user purchases - Handle a “getAllUserBuys” and send a GET request to Customer Management service
      and present the response
   

For any issues please let me know.

Thanks,
Lior.