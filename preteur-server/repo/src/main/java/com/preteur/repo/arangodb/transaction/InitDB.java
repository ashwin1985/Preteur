package com.preteur.repo.arangodb.transaction;

import com.arangodb.ArangoConfigure;
import com.arangodb.ArangoDriver;
import com.arangodb.ArangoException;
import com.arangodb.entity.*;

import java.util.ArrayList;
import java.util.List;

public class InitDB {

    ArangoDriver arangoDriver;

    private final String DATABASE_NAME = "Vendi";
    private final String GRAPH_NAME = "vendiGraph";
    private final String EDGE_COLLECTION_NAME = "relateCollection";
    private final String VERTEXT_COLLECTION_NAME = "personCollection";

    public void _setup() throws ArangoException {
        ArangoConfigure configure = new ArangoConfigure();
        configure.init();

        arangoDriver = new ArangoDriver(configure);

        if(!doesGraphExists()) {
            _createDatabase();
            _createGraph();
        }
    }

    private boolean doesGraphExists() throws ArangoException {
        return arangoDriver.getGraph(DATABASE_NAME) != null;
    }

    private void _createDatabase() throws ArangoException {
        arangoDriver.createDatabase(DATABASE_NAME);
        arangoDriver.setDefaultDatabase(DATABASE_NAME);
    }

    private void _createGraph() throws ArangoException {
        CollectionEntity createCollection = arangoDriver.createCollection(EDGE_COLLECTION_NAME,
                new CollectionOptions().setType(CollectionType.EDGE));

        createCollection = arangoDriver.createCollection(VERTEXT_COLLECTION_NAME,
                new CollectionOptions().setType(CollectionType.DOCUMENT));

        EdgeDefinitionEntity ed = new EdgeDefinitionEntity();
        ed.setCollection(EDGE_COLLECTION_NAME);
        ed.getFrom().add(VERTEXT_COLLECTION_NAME);
        ed.getTo().add(VERTEXT_COLLECTION_NAME);

        List<EdgeDefinitionEntity> edgeDefinitions = new ArrayList<EdgeDefinitionEntity>();
        edgeDefinitions.add(ed);

        GraphEntity createGraph = arangoDriver.createGraph(GRAPH_NAME, edgeDefinitions, null, true);
    }

    public static void main(String args[]) throws ArangoException {
        InitDB db = new InitDB();
        db._setup();
    }
}
