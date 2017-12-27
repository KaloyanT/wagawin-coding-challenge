package com.wagawin.wagawincodingtask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Meal")
@Table(name = "meal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Meal {

    @Id
    @GenericGenerator(name = "mealIdGenerator", strategy = "increment")
    @GeneratedValue(generator = "mealIdGenerator")
    @Column(name = "mealId")
    private long mealId;

    // declare this attribute as public as required in the UML Diagram
    @Basic(optional = false)
    @Column(name = "name")
    @NotNull
    public String name;

    // declare this attribute as public as required in the UML Diagram
    @Basic(optional = false)
    @Column(name = "invented")
    @Temporal(TemporalType.DATE)
    public Date invented;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_childId")
    @JsonIgnore
    private Child child;

    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInvented() {
        return invented;
    }

    public void setInvented(Date invented) {
        this.invented = invented;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mealId);
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }
        if(! (o instanceof Meal) ) {
            return false;
        }

        return (this.mealId != 0) && (this.mealId == ((Meal) o).mealId);
    }

    @Override
    public String toString() {

        return "Meal: {" +
                "mealId=" + getMealId() +
                ", name=" + getName() +
                ", invented=" + getInvented() +
                "}";
    }
}
