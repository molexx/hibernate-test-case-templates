package org.hibernate.bugs.model;

import javax.persistence.*;

@Entity
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    public String childProperty;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public Parent parent;
    
}
