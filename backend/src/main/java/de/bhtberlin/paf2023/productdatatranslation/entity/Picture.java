package de.bhtberlin.paf2023.productdatatranslation.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@link Picture Pictures} visually describes a {@link Product}.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Picture {

    /**
     * An internal identifier for the {@link Picture}.
     * The id will be automatically incremented by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The filename that the {@link Picture} uses. This will be the
     * normalized original filename from the upload.
     */
    private String filename;

    /**
     * The file format of the {@link Picture}. This will be set to the
     * original file format from the upload.
     */
    private String format;

    /**
     * The original height of the {@link Picture}.
     */
    private double height;

    /**
     * The original width of the {@link Picture}.
     */
    private double width;

    /**
     * The {@link Product} this {@link Picture} belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Basic constructor with all data fields in order to create new {@link Picture Pictures}.
     * The id will be set during creation of the object in the database.
     */
    public Picture(String filename, String format, double height, double width) {
        this.filename = filename;
        this.format = format;
        this.height = height;
        this.width = width;
    }

    /**
     * Compare an Object to this {@link Picture} by checking
     * object equality or the same id.
     *
     * @param o The Object to compare.
     * @return true if Object is equal to this {@link Picture}.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Picture obj = (Picture) o;
        return o == this || this.id == obj.id;
    }

}
