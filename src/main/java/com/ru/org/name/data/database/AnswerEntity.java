package com.ru.org.name.data.database;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "answers")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    @Column(name = "Result", nullable = false)
    private double phase_value;
    private double module_value;


    public AnswerEntity() {
    }

    public AnswerEntity(Integer answerId, double phase_value, double module_value) {
        this.answerId = answerId;
        this.phase_value = phase_value;
        this.module_value = module_value;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public double getPhase_value() {
        return phase_value;
    }

    public void setPhase_value(double phase_value) {
        this.phase_value = phase_value;
    }

    public double getModule_value() {
        return module_value;
    }

    public void setModule_value(double module_value) {
        this.module_value = module_value;
    }
}
