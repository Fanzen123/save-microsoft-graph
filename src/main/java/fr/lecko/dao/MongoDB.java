package fr.lecko.dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.lecko.contract.save_microsoft_graph.dto.Mail;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoDB implements MailDao {

    public static final String DATABASE = "Lecko";
    public static final String COLLECTION = "Mail";
    public static final String URI =
            "mongodb+srv://lecko:lecko@demo.biqk0.mongodb.net/" +
            "myFirstDatabase?retryWrites=true&w=majority";
    public static final String NAME_FIELD = "_name";
    public static final String DATE_FIELD = "_date";
    public static final String CONTENT_FIELD = "_content";
    public static final String FROM_FIELD = "_from";

    private MongoClient mongoClient;

    public MongoDB() {
        ConnectionString connectionString = new ConnectionString(URI);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        mongoClient = MongoClients.create(settings);
    }

    @Override
    public void insertMails(List<Mail> mails) {
        MongoDatabase sampleTrainingDB = mongoClient.getDatabase(DATABASE);
        MongoCollection<Document> mailCollection = sampleTrainingDB.getCollection(COLLECTION);
        List<Document> documents = new ArrayList<>();
        mails.forEach(mail -> documents.add(createDocument(mail)));
        mailCollection.insertMany(documents);
    }

    @Override
    public List<Mail> getMails() {
        List<Mail> mails = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<Document> mailCollection = database.getCollection(COLLECTION);
        mailCollection.find().forEach(document -> mails.add(map(document)));
        return mails;
    }

    private Document createDocument(Mail mail) {
        Document document = new Document("_id", new ObjectId());
        document.append(NAME_FIELD, mail.getName());
        document.append(DATE_FIELD, mail.getDate());
        document.append(CONTENT_FIELD, mail.getContent());
        document.append(FROM_FIELD, mail.getFrom());
        return document;
    }

    private Mail map(Document document) {
        Mail mail = new Mail();
        mail.setContent(document.getString(CONTENT_FIELD));
        mail.setName(document.getString(NAME_FIELD));
        mail.setFrom(document.getString(FROM_FIELD));
        return mail;
    }

}
