package ag.granular.codingassignment.data;

import android.util.Log;

import java.util.List;

import ag.granular.codingassignment.GranularApplication;
import androidx.annotation.NonNull;
import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Box (Table in terms of SQL) for number
 * and images record (NumberImagesEntity). This
 * class has all CRUD operations for the Box.
 *
 */
public class NumberImagesBox {
    // Log Tag
    private final static String TAG = NumberImagesBox.class.getSimpleName();

    // BoxStore (DB)
    private final BoxStore boxStore = GranularApplication.getBoxStore();

    // Box (Table)
    private final Box<NumberImagesEntity> box =
            boxStore.boxFor(NumberImagesEntity.class);

    /**
     * Method to bulk add NumberImageEntity objects
     * in Box.
     *
     * @param numberImageList - List of NumberImageEntity objects.
     */
    public void addNumberImageList(@NonNull final
                                   List<NumberImagesEntity> numberImageList) {
        Log.d(TAG, "addNumberImageList called");
        box.put(numberImageList);
    }

    /**
     * Method to retrieve all NumberImageEntity objects
     * from Box.
     *
     * @return - List of NumberImageEntity objects.
     */
    public List<NumberImagesEntity> getNumberImageList() {
        Log.d(TAG, "getNumberImageList called");
        return box.getAll();
    }

    /**
     * Method to clear the Box.
     */
    public void clear() {
        Log.d(TAG, "clear called");
        box.removeAll();
    }
}
