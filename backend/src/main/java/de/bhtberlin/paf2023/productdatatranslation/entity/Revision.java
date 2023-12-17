package de.bhtberlin.paf2023.productdatatranslation.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@link Revision} is the historical version of a {@link Translation}.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Revision extends Translation{

    /**
     * The version number for this {@link Revision} that will be
     * counted up for every changed {@link Translation}.
     */
    protected int version;

    /**
     * A timestamp when the {@link Revision} was created.
     */
    protected long timestamp;

    /**
     * Information if this {@link Revision} is a correction.
     */
    protected boolean correction;

    /**
     * The {@link Translation} this {@link Revision} is for.
     */
    @ManyToOne
    @JoinColumn(name = "translation_id")
    protected Translation translation;

    /**
     * Basic constructor with all data fields in order to create new {@link Revision Revisions}.
     * The id will be set during creation of the object in the database.
     */
    public Revision(int version, long timestamp, String shortDescription, String longDescription, boolean correction) {
        this.version = version;
        this.timestamp = timestamp;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.correction = correction;
    }

    /**
     * Compare an Object to this {@link Revision} by checking
     * object equality or the same id.
     *
     * @param o The Object to compare.
     * @return true if Object is equal to this {@link Revision}.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Revision obj = (Revision) o;
        return o == this || this.id == obj.id;
    }

}
