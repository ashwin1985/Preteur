package com.preteur.repo.orientdb.transaction;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public interface WorkUnit<T> {

    public T doOperation(OrientGraph graph) throws Exception;

}
