package com.wagawin.wagawincodingtask.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "ParentSummary")
@Table(name = "parentSummary")
public class ParentSummary {

    @Id
    @GenericGenerator(name = "parentSummaryIdGenerator", strategy = "increment")
    @GeneratedValue(generator = "parentSummaryIdGenerator")
    @Column(name = "parentSummaryId")
    private long parentSummaryId;

    @Basic(optional = false)
    @NotNull
    @Min(value = 0)
    private Long amountOfPersons;

    @Basic(optional = false)
    @NotNull
    @Min(value = 0)
    private Long amountOfChildren;

    public long getParentSummaryId() {
        return parentSummaryId;
    }

    public void setParentSummaryId(long parentSummaryId) {
        this.parentSummaryId = parentSummaryId;
    }

    public Long getAmountOfPersons() {
        return amountOfPersons;
    }

    public void setAmountOfPersons(Long amountOfPersons) {
        this.amountOfPersons = amountOfPersons;
    }

    public Long getAmountOfChildren() {
        return amountOfChildren;
    }

    public void setAmountOfChildren(Long amountOfChildren) {
        this.amountOfChildren = amountOfChildren;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.parentSummaryId);
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }
        if(! (o instanceof ParentSummary) ) {
            return false;
        }

        return (this.parentSummaryId != 0) && (this.parentSummaryId == ((ParentSummary) o).parentSummaryId);
    }

    @Override
    public String toString() {

        return "ParentSummary: {" +
                "parentSummaryId=" + getParentSummaryId() +
                ", amountOfPersons=" + getAmountOfPersons() +
                ", amountOfChildren" + getAmountOfChildren() +
                "}";
    }
}
