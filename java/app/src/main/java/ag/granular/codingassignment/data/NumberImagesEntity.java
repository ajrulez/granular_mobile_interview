package ag.granular.codingassignment.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Entity class to for Number and Image
 * record in the database.
 *
 * Note: Getters and Setters are required is the
 * fields of entity are private. Otherwise all fields
 * should be public.
 *
 */
@Entity
public class NumberImagesEntity implements Parcelable {
    // Record ID - Auto-managed with Id annotation
    @Id
    public long id;

    // Name to display
    private String name;

    // Image Url
    private String url;

    /**
     * Constructor to create a NumberImagesEntity
     * object.
     *
     * @param imageName - Name to display
     * @param imageUrl - Image Url
     */
    public NumberImagesEntity(@NonNull final String imageName,
                              @NonNull final String imageUrl) {
        this.name = imageName;
        this.url = imageUrl;
    }

    /**
     * Getter for Image name.
     *
     * @return - Image name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for Image Url.
     *
     * @return - Image Url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter for Image name.
     *
     * @param imageName - Image name
     */
    public void setName(final String imageName) {
        this.name = imageName;
    }

    /**
     * Setter for Image Url.
     *
     * @param imageUrl - Image Url
     */
    public void setUrl(final String imageUrl) {
        this.url = imageUrl;
    }

    /**************************** Parcelable Implementation *************************/

    /**
     * Constructor for Parcelable implementation - creates
     * NumberImagesEntity object from a Parcel.
     *
     * @param in - Parcel
     */
    public NumberImagesEntity(final Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
        this.id = in.readLong();
    }

    /**
     * Parcel Creator for Parcelable.
     *
     */
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public NumberImagesEntity createFromParcel(Parcel in) {
            return new NumberImagesEntity(in);
        }

        public NumberImagesEntity[] newArray(int size) {
            return new NumberImagesEntity[size];
        }
    };

    /**
     * Method to write Object to Parcel
     *
     * @param dest - Parcel to write to
     * @param flags - Flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeLong(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
