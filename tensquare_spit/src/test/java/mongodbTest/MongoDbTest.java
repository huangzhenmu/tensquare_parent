package mongodbTest;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BasicBSONObject;
import org.bson.Document;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MongoDbTest {
    public void testMongoDb(){
        //连接mongodb服务器
        MongoClient client = new MongoClient("192.168.63.1");
        //得到要操作的数据库
        MongoDatabase database = client.getDatabase("db");
        //得到要操作的集合（相当于关系型数据库的表)
        MongoCollection<Document> spit = database.getCollection("spit");
        /**
         *  复杂查询
         *  由于mongodb中的数据形式是bson形式（与json差不多）所以用bson对象封装查询条件
         */
        BasicBSONObject bsonObject = new BasicBSONObject("userid","1013");//{"userid":"1013"}
        //{"visits":{$ge:1000}}意思是visits>=1000
        BasicBSONObject bsonObject1 = new BasicBSONObject("visits",new BasicBSONObject("$ge",1000));
        //得到集合中的文档
        FindIterable<Document> documents = spit.find();
        //遍历文档
        for (Document document : documents){
            System.out.println("内容："+document.getString("content"));
            System.out.println("访问量："+document.getInteger("visits"));
        }

        //添加记录,Document可以有多种数据格式，map比较方便
        Map<String,Object> map = new HashMap<>();
        map.put("content","haha");
        map.put("userid","1");
        map.put("visits",1000);
        Document document = new Document(map);
        spit.insertOne(document);
        //关闭数据库
        client.close();
    }
}
