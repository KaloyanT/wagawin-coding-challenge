package com.wagawin.wagawincodingtask.model;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "Son")
@DiscriminatorValue("son")
public class Son extends Child {

    @Basic(optional = false)
    @NotNull
    private String bicycleColor;

    public String getBicycleColor() {
        return bicycleColor;
    }

    public void setBicycleColor(String bicycleColor) {
        this.bicycleColor = bicycleColor;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }
        if(! (o instanceof Son) ) {
            return false;
        }

        return (this.getChildId() != 0) && (this.getChildId() == ((Son) o).getChildId());
    }

    @Override
    public String toString() {

        return "Son: {" +
                "childId=" + getChildId() +
                ", name=" + getName() +
                ", age=" + getAge() +
                "}";
    }
}
