package com.wagawin.wagawincodingtask.model;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "Daugther")
@DiscriminatorValue("daughter")
public class Daughter extends Child {

    @Basic(optional = false)
    @NotNull
    private String hairColor;

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }
        if(! (o instanceof Daughter) ) {
            return false;
        }

        return (this.getChildId() != 0) && (this.getChildId() == ((Son) o).getChildId());
    }

    @Override
    public String toString() {

        return "Daughter: {" +
                "childId=" + getChildId() +
                ", name=" + getName() +
                ", age=" + getAge() +
                "}";
    }
}
