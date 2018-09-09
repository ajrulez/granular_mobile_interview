package ag.granular.codingassignment.model;

import androidx.annotation.NonNull;

/**
 * Data class that provides URLs for
 * fetching data and for images.
 */
public class NumberImageUrlFactory {
    private final static String BASE_URL =
            "https://raw.githubusercontent.com/granularag/granular_mobile_mock_response/master/";
    private final static String DATA_LIST_ENDPOINT = "list.json";

    /**
     * Method to get the URL for retrieving Data list.
     *
     * @return - URL for retrieving data.
     */
    public static String getDataListUrl() {
        return BASE_URL + DATA_LIST_ENDPOINT;
    }

    /**
     * Method to return URL for the specified image,
     * for example Icons/1.png.
     *
     * @param imageEndpoint - Image endpoint in format Icons/1.png
     * @return - URL for fetching the image.
     */
    public static String getImageUrl(@NonNull final String imageEndpoint) {
        return BASE_URL + imageEndpoint;
    }
}
