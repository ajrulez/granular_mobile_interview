package ag.granular.codingassignment;

import android.app.Application;
import android.util.Log;

import ag.granular.codingassignment.data.MyObjectBox;
import io.objectbox.BoxStore;

/**
 * Extending the Application class for custom
 * initialization (such as ObjectBx for DB).
 *
 */
public class GranularApplication extends Application {
    // Log Tag
    private final static String TAG = GranularApplication.class.getSimpleName();

    // BoxStore (DB) - One application should have only
    // instance of BoxStore, so it should be setup in the
    // Application class
    private static BoxStore boxStore;

    /**
     * Overridden onCreate to init resources
     * like ObjectBox DB.
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize ObjectBox
        Log.d(TAG, "onCreate called - Setup BoxStore");
        boxStore = MyObjectBox.builder()
                .androidContext(GranularApplication.this)
                .build();
    }

    /**
     * Method to get BoxStore single instance.
     *
     * @return - BoxStore single instance.
     */
    public static BoxStore getBoxStore() {
        return boxStore;
    }
}
