package com.processor.test;


import com.mongodb.client.MongoClients;
import com.processor.core.IAnswerProcessor;
import com.processor.domain.RawAnswer;
import com.processor.domain.TestConfiguration;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.File;
import java.util.Scanner;

@SpringJUnitConfig(TestServiceConfig.class)
@TestPropertySource("/application-unitTest.properties")
@SpringBootApplication
public class ProcessorTest {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;


    @Autowired
    private IAnswerProcessor answerProcessor;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception  {

        String ip = "localhost";
        int port = 27017;

        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() {
    }

    @org.junit.jupiter.api.Test()
    public void testTypeFormProcessorNoTestConfiguration() throws Exception {

        String body = readJsonObject(); // represents typeform request
        JSONObject recordToSave = new JSONObject(body);
        String result = answerProcessor.processNewRecord(recordToSave);
        //trying to process the record without configuration in db
        Assert.assertNull(result);
    }

    @org.junit.jupiter.api.Test()
    public void testTypeFormProcessorEmptyRecord() throws Exception {

        String result = answerProcessor.processNewRecord(null);
        Assert.assertNull(result);

    }

    @org.junit.jupiter.api.Test()
    public void testTypeFormProcessorSuccess() throws Exception {

        String body = readJsonObject(); // represents typeform request
        JSONObject recordToSave = new JSONObject(body);
        TestConfiguration testConfiguration = new TestConfiguration();
        testConfiguration.setFormCode("irEdZweA");
        testConfiguration.setFormFields("entryid,name,email,phone,landed_at,submitted_at,7i8WIXSGpPkA,MCltzxozEDKF,pQ9fp8iIn8E0,YnlY5nXCLGRi,w2PnnW8QtxaS,9o8QyNjjdYNn,80kU7reebHwN,6JP9lCAbqZ0J,ejCqs98sjPUx,uk2ubbKe4X7z,F5JWXq8OL5DA");
        testConfiguration.setOutputFields("entry,name,email,phone,datecreated,lastupdated,pqua_4,pqua_3,pqua_2,pqua_17,pqua_16,pqua_15,pqua_1,pqua_9,pqua_8,pqua_7,pqua_78");
        mongoTemplate.save(testConfiguration,"testConfiguration");
        String result = answerProcessor.processNewRecord(recordToSave);
        Assert.assertNotNull(result);
        RawAnswer rawAnswer =mongoTemplate.findById(result, RawAnswer.class);
        Assert.assertNotNull(rawAnswer);
    }


    private String readJsonObject()throws Exception{

            File myObj = new File(System.getProperty("user.dir") + "\\data\\typeformDataPoint.json");
            Scanner myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
            System.out.println(data);
            return data;
    }

}
