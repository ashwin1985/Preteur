package com.preteur.repo.orientdb.model;

public interface Relations {
    
    public static enum BondTypes {
        BORROWER,
        LENDER
    }
    
    public static enum NetworkTypes {
        INNER_CIRCLE,
        OUTER_CIRCLE,
    }
}
