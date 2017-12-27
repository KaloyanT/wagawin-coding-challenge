package com.wagawin.wagawincodingtask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Child")
@Table(name = "child")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "gender")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class Child {

    @Id
    @GenericGenerator(name = "childIdGenerator", strategy = "increment")
    @GeneratedValue(generator = "childIdGenerator")
    @Column(name = "childId")
    private long childId;

    @Basic(optional = false)
    @NotNull
    private String name;

    @Basic(optional = false)
    @NotNull
    @Min(value = 0)
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_personId")
    @JsonIgnore
    private Person person;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Meal> meals = new ArrayList<Meal>();

    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public void addMeal(Meal meal) {
        this.meals.add(meal);
        meal.setChild(this);
    }

    public void removeMeal(Meal meal) {
        this.meals.remove(meal);
        meal.setChild(null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.childId);
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }
        if(! (o instanceof Child) ) {
            return false;
        }

        return (this.childId != 0) && (this.childId == ((Child) o).childId);
    }

    @Override
    public String toString() {

        return "Child: {" +
                "childId=" + getChildId() +
                ", name=" + getName() +
                ", age=" + getAge() +
                "}";
    }
}
