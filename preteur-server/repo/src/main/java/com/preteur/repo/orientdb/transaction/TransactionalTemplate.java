package com.preteur.repo.orientdb.transaction;

import com.preteur.repo.result.Result;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

public class TransactionalTemplate<T> {

    private static OrientGraphFactory factory = new OrientGraphFactory("plocal:/data/orientdb");

    public Result<T> doWork(WorkUnit<T> workUnit) {
        OrientGraph graph = factory.getTx();
        Result<T> result = null;

        try {
            T r = workUnit.doOperation(graph);
            result = Result.success(r);
        } catch (Exception e) {
            e.printStackTrace();
            graph.rollback();
            result = Result.failure(e);
        } finally {
            if (graph != null) {
                graph.shutdown();
            }
        }

        return result;
    }

}
