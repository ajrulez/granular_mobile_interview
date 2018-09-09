package ag.granular.codingassignment.presenter;

import android.util.Log;

import java.util.List;

import ag.granular.codingassignment.data.NumberImagesEntity;
import ag.granular.codingassignment.model.NumberImageFetcher;
import ag.granular.codingassignment.model.NumberImageReceiver;
import ag.granular.codingassignment.view.NumberImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Presenter for NumberImageView.
 *
 */
public class NumberImagePresenterImpl implements NumberImagePresenter,
                                                 NumberImageReceiver {
    // Log Tag
    private final static String TAG = NumberImagePresenterImpl.class.getSimpleName();

    // NumberImageView
    private NumberImageView view;

    // NumberImageFetcher
    private NumberImageFetcher dataFetcher;

    /**
     * Overridden addView for interface implementation.
     * Adds a View to this Presenter.
     *
     * @param view - NumberImageView object
     */
    @Override
    public void addView(@NonNull final NumberImageView view) {
        Log.i(TAG, "addView called");
        this.view = view;
        this.dataFetcher = new NumberImageFetcher();
    }

    /**
     * Overridden removeView for interface implementation.
     * Removes this Presenter's View
     *
     */
    @Override
    public void removeView() {
        Log.i(TAG, "removeView called - dispose response from here on");
        // Tell data fetcher to not send callback for the response
        dataFetcher.disposeResponse();
        view.hideProgress(); // If the Progress bar is showing, hide it
        this.view = null;
    }

    /**
     * Overridden feathNumberImageData to get data
     * for Numbers and Images.
     *
     */
    @Override
    public void fetchNumberImageData() {
        Log.d(TAG, "fetchNumberImageData called");
        if (view != null && !view.isGoingAway()) {
            view.showProgress();
            dataFetcher.retrieveNumberImages(view.getContext(), this);
        }
    }

    /**
     * Method that is called when data is fetched.
     *
     * @param success - Indicates if data was fetched successfully
     * @param numberImageList - List of NumberImageEntity objects
     */
    @Override
    public void onDataFetched(final boolean success,
                              @Nullable final List<NumberImagesEntity> numberImageList) {
        Log.i(TAG, "onDataFetched called with success = " + success);
        if (view != null && !view.isGoingAway()) {
            Log.d(TAG, "onDataFetched - Valid view available, update it");
            view.updateView(numberImageList);
            view.hideProgress();
        }
    }
}
