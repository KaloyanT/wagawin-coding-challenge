package com.wagawin.wagawincodingtask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Person")
@Table(name = "person")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {

    @Id
    @GenericGenerator(name = "personIdGenerator", strategy = "increment")
    @GeneratedValue(generator = "personIdGenerator")
    @Column(name = "personId")
    private long personId;

    @Basic(optional = false)
    @NotNull
    private String name;

    @Basic(optional = false)
    @NotNull
    @Min(value = 0)
    private Integer age;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @JsonIgnore
    private Set<Child> children = new HashSet<Child>();

    // We don't need a @OneToOne mapping here as suggested in
    // https://vladmihalcea.com/2016/07/26/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
    // private House house;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
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

    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }

    public void addChild(Child child) {
        this.children.add(child);
        child.setPerson(this);
    }

    public void removeChild(Child child) {
        this.children.remove(child);
        child.setPerson(null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.personId);
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }
        if(! (o instanceof Person) ) {
            return false;
        }

        return (this.personId != 0) && (this.personId == ((Person) o).personId);
    }

    @Override
    public String toString() {

        return "Person: {" +
                "personId=" + getPersonId() +
                ", name=" + getName() +
                ", age=" + getAge() +
                "}";
    }
}
