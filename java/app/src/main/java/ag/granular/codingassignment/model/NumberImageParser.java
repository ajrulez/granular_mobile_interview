package ag.granular.codingassignment.model;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ag.granular.codingassignment.data.NumberImagesEntity;
import androidx.annotation.NonNull;

/**
 * Class that implements JSON Parser
 * for Number-Image JSON data. ideally
 * I would have used Gson, but I am limiting
 * use of 3rd party libraries.
 *
 */
class NumberImageParser {
    // Log Tag
    private final static String TAG = NumberImageParser.class.getSimpleName();

    /**
     * Method to parse JSON-formatted String to create
     * List of NumberImagesEntity that is returned.
     *
     * @param jsonData - JSON Formatted data.
     * @return List of NumberImagesEntity objects
     */
    List<NumberImagesEntity> parseNumberImageData(@NonNull final String jsonData) {
        Log.d(TAG, "parseNumberImageData called");
        List<NumberImagesEntity> numberImageList = new ArrayList<>();
        if (TextUtils.isEmpty(jsonData)) {
            Log.w(TAG, "parseNumberImageData - Invalid jsonData");
            return numberImageList;
        }

        // Parse
        try {
            final JSONArray dataArray = new JSONArray(jsonData);
            for (int count = 0; count < dataArray.length(); count++) {
                JSONObject jObj = dataArray.getJSONObject(count);
                NumberImagesEntity numberImagesEntity = getNumberImageEntityFromJson(jObj);
                if (numberImagesEntity != null) {
                    numberImageList.add(numberImagesEntity);
                }
            }
        }
        catch (Exception e) {
            Log.e(TAG, "parseNumberImageData - Exception when parsing data - " + e);
            numberImageList.clear();
        }

        return numberImageList;
    }

    /**
     * Method that creats a NumberImagesEntity object from
     * JSONObject and returns it.
     *
     * @param jObj - JSONObject
     * @return NumberImagesEntity
     */
    private NumberImagesEntity getNumberImageEntityFromJson(@NonNull final JSONObject jObj) {
        NumberImagesEntity numberImagesEntity = null;

        try {
            // Using get is fine instead of opt because exception
            // is being caught. With opt, an empty String
            // that is returned will not be useful anyway.
            final String name = jObj.getString("name");
            final String url = jObj.getString("url");

            numberImagesEntity = new NumberImagesEntity(name, url);
        }
        catch (Exception e) {
            // Log it
            Log.e(TAG, "getNumberImageEntityFromJson - Exception: " + e);
        }

        return numberImagesEntity;
    }
}
