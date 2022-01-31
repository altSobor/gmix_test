package com.company.test4.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "LEXGRAPH_INPUT")
@Entity
public class LexgraphInput {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "INPUT_")
    @Lob
    protected String input;
    @JoinColumn(name = "LEXGRAPH_DATA_CLASS_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private LexgraphDataClass lexgraphDataClass;

    public LexgraphDataClass getLexgraphDataClass() {
        return lexgraphDataClass;
    }

    public void setLexgraphDataClass(LexgraphDataClass lexgraphDataClass) {
        this.lexgraphDataClass = lexgraphDataClass;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}