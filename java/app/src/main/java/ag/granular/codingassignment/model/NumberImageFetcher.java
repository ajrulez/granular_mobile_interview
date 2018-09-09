package ag.granular.codingassignment.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import ag.granular.codingassignment.data.NumberImagesBox;
import ag.granular.codingassignment.data.NumberImagesEntity;
import ag.granular.codingassignment.network.INetworking;
import ag.granular.codingassignment.network.Networking;
import androidx.annotation.NonNull;

/**
 * This class is used to retrieve list
 * of NumberImageEntity objects.
 *
 * Note: This class uses the provided Networking
 * class which uses Volley. Volley provides
 * data on the main thread, so and runs the operation
 * in background (RequestQueue). Because of this, there
 * is no need for AsyncTask and worry about Lifecycle
 * changes.
 *
 * Once network call is finished, the data will be populated
 * in Box (DB) to prevent future network calls due to
 * Lifecycle changes.
 *
 * Source: https://developer.android.com/training/volley/simple
 */
public class NumberImageFetcher implements Response.Listener<String>,
                                           Response.ErrorListener {
    // Log Tag
    private final static String TAG = NumberImageFetcher.class.getSimpleName();

    //https://raw.githubusercontent.com/granularag/granular_mobile_mock_response/master/Icons/1.png

    // NumberImageReceiver
    private NumberImageReceiver receiver;

    // Dispose Response
    private boolean shouldDisposeResponse = false;

    // Response Data
    private List<NumberImagesEntity> dataList = new ArrayList<>();

    // Networking
    private final INetworking networking = new Networking();

    // Box
    private final NumberImagesBox box = new NumberImagesBox();

    /**
     * Method to retrieve Number-Images data.
     *
     * If the data exists in Box (DB), then it is returned
     * right away. Otherwise a network call is made to get the
     * data.
     *
     * @param context - Context needed for Networking
     * @param receiver -
     */
    public synchronized void retrieveNumberImages(@NonNull final Context context,
                                     @NonNull final NumberImageReceiver receiver) {
        Log.d(TAG, "retrieveNumberImages called");
        this.receiver = receiver;

        // Check if we have data in data base already
        final List<NumberImagesEntity> numberImageList =
                box.getNumberImageList();

        if (numberImageList != null &&
                numberImageList.size() > 0) {
            Log.i(TAG, "retrieveNumberImages - Database has number-image data.");
            receiver.onDataFetched(true, numberImageList);
        }
        else {
            Log.i(TAG, "retrieveNumberImages - Database is empty, fire a network call to " +
                    NumberImageUrlFactory.getDataListUrl());
            networking.requestStringDataWithURL(context, NumberImageUrlFactory.getDataListUrl(),
                    this, this);
        }
    }

    /**
     * Method to indicate that we want to dispose the response
     * from network call.
     *
     */
    public synchronized void disposeResponse() {
        Log.i(TAG, "disposeResponse called");
        shouldDisposeResponse = true;
    }

    /**
     * Method that is called (on MainThread by Volley)
     * when there is an error.
     *
     * @param error - Error
     */
    @Override
    public void onErrorResponse(final VolleyError error) {
        Log.w(TAG, "onErrorResponse - " + error);
        if (receiver != null &&
                !shouldDisposeResponse) {
            Log.d(TAG, "onErrorResponse - Receiver is valid and not disposed - call onDataFetched with error");
            receiver.onDataFetched(false, dataList);
        }
    }

    /**
     * Method that is called (on MainThread by Volley)
     * when network request is successul.
     *
     * @param response - JSON formatted String
     */
    @Override
    public void onResponse(final String response) {
        Log.d(TAG, "onResponse called");
        final NumberImageParser numberImageParser = new NumberImageParser();
        dataList = numberImageParser.parseNumberImageData(response);
        boolean success = false;
        if (dataList != null && dataList.size() > 0) {
            // Database should not be populated if we
            // end up here because network call is only made
            // if database is empty. However, it might be okay
            // to clear the database and repopulate it here if
            // we want *new* data. In such a case, the app should
            // clear the database before calling NumberImageFetcher.
            // Just a thought :-)
            box.clear();
            box.addNumberImageList(dataList);
            Log.d(TAG, "onResponse - success");
            success = true;
        }

        if (response != null &&
                !shouldDisposeResponse) {
            Log.d(TAG, "onResponse - Receiver is valid and not disposed - call onDataFetched with status = " + success);
            receiver.onDataFetched(success, dataList);
        }
    }
}
