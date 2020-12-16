# Typeform processor

Typeform webhook implementation and business logic to process a new survey answer. This solution can
be tailored to support specific business needs. It can process answers from any survey once configured.

**Core concepts**

TestConfiguration: domain object to define a specific test configuration. In typeform, every form has a unique id. 
Furthermore, every question inside a form has a unique identifier as well. In order to save a new answer to the database
every specific question should be processed according to its data-type and named based on mappings specified as part of the configuration.

Example of a configuration to process a typeform form identified by irEdZweA:

```
TestConfiguration testConfiguration = new TestConfiguration();
        testConfiguration.setFormCode("irEdZweA");
        testConfiguration.setFormFields("entryid,name,email,phone,landed_at,submitted_at,7i8WIXSGpPkA,MCltzxozEDKF,pQ9fp8iIn8E0,YnlY5nXCLGRi,w2PnnW8QtxaS,9o8QyNjjdYNn,80kU7reebHwN,6JP9lCAbqZ0J,ejCqs98sjPUx,uk2ubbKe4X7z,F5JWXq8OL5DA");
        testConfiguration.setOutputFields("entry,name,email,phone,datecreated,lastupdated,pqua_4,pqua_3,pqua_2,pqua_17,pqua_16,pqua_15,pqua_1,pqua_9,pqua_8,pqua_7,pqua_78");

```

**HL Architecture**

* com.processor.IProcessorService: defines the contract of the webhook operation.
* com.processor.ProcessorController: rest controller to implement IProcessService
* com.processor.aop.ErrorsHandler: aspect to track errors during executions.
* com.processor.core.typeform.*: classes in charge of processing typeform datapoints based on TestConfiguration.
* com.processor.AnswerProcessor: business logic to process new answers. It has dependency to TypeformProvider component through IQuestionnaireHelper. 
* com.processor.domain: domain classes involved (POJO)
* com.processor.repositories: mongodb repositories (data access)

All dependencies managed by Spring boot.

**Stack**

* Spring Boot (https://spring.io/projects/spring-boot)
* Java
* Maven
* MongoDB
* Docker
* AWS Code Pipeline

**Global Settings**

Database connection and other settings can be set up in the following .properties:
* resources/application-default.properties (development) 

* Typeform webshooks should be set up. Check their dev documentation here: https://developer.typeform.com/webhooks/

It's highly recommended to use ENV variables to set up database connection strings and other sentitive information.

**Unit tests**

* src/test/java/com/processor/test
* More about embedded mongodb (In use here to mock our database). https://www.baeldung.com/spring-boot-embedded-mongodb  

**Deployment**

The service can be deployed on any container-based service with Docker support. A **Docker file** and **build spec file** are included as part of this distribution to easily deploy it on AWS ECS through CodeDeploy or CodePipeline

* AWS ECS https://aws.amazon.com/ecs/

MongoDB database can be set up using MongoDB Atlas:
https://www.mongodb.com/cloud/atlas