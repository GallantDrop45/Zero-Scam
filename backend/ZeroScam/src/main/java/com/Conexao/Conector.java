package com.Conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoIterable;

public class Conector {

    public void connectAndPrintDbs() {
        String uri = System.getenv("MONGODB_URI");
        if (uri == null || uri.isEmpty()) {
            uri = "mongodb://localhost:27017/zeroscam";
        }

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoIterable<String> dbs = mongoClient.listDatabaseNames();
            System.out.println("Databases:");
            for (String dbName : dbs) {
                System.out.println(" - " + dbName);
            }
        } catch (Exception e) {
            System.err.println("Erro ao conectar no MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
