package ag.granular.codingassignment;

import ag.granular.codingassignment.adapter.NumberImagesAdapter;
import ag.granular.codingassignment.data.NumberImagesEntity;
import ag.granular.codingassignment.presenter.NumberImagePresenter;
import ag.granular.codingassignment.presenter.NumberImagePresenterImpl;
import ag.granular.codingassignment.view.NumberImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NumberImageView {
    // Log Tag
    private final static String TAG = MainActivity.class.getSimpleName();

    // Presenter
    private NumberImagePresenter presenter;

    // NumberImagesEntity List
    private List<NumberImagesEntity> numberImagesList;

    // Bundle Key for NumberImageEntity List
    private final static String KEY_NUMBER_IMAGE_LIST = "number_image_list";

    // ListView - Note: Using ListView instead of RecyclerView
    // because the assignment description mentioned list view. Otherwise
    // I would have used a RecyclerView.
    private ListView listView;

    // ListView Adapter
    private NumberImagesAdapter listAdapter;

    /**
     * Overridden onCreate to setup the Activity's View.
     *
     * @param savedInstanceState - Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.numberListView);
        listAdapter = new NumberImagesAdapter(this, R.layout.list_row_item);
        listView.setAdapter(listAdapter);

        // Setup the Presenter and populate data for this view
        Log.i(TAG, "onCreate called with Bundle = " + (savedInstanceState != null));
        presenter = new NumberImagePresenterImpl();
        presenter.addView(this);
        populateViewData(savedInstanceState);
    }

    /**
     * Overridden onDestroy.
     *
     */
    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy called - Remove the view from presenter");
        // Remove View from Presenter
        presenter.removeView();
        super.onDestroy();
    }

    /**
     * Overridden onSaveInstanceState to save View's data
     * before View is recreated.
     *
     * @param out - Bundle
     */
    @Override
    protected void onSaveInstanceState(Bundle out) {
        Log.i(TAG, "onSaveInstanceState called - Save the data in Bundle");
        if (numberImagesList != null && numberImagesList.size() > 0) {
            Log.d(TAG, "onSaveInstanceState - numberImagesList size = " + numberImagesList.size());
            final ArrayList<NumberImagesEntity> arrayList =
                    new ArrayList<>(numberImagesList);
            numberImagesList.clear();
            out.putParcelableArrayList(KEY_NUMBER_IMAGE_LIST, arrayList);
        }
        super.onSaveInstanceState(out);
    }

    /**
     * Implementation of NumberImageView.updateView
     * to update the data in the NumberImageView.
     *
     * @param numberImageList - List of NumberImageEntity objects
     */
    @Override
    public void updateView(@NonNull final List<NumberImagesEntity> numberImageList) {
        Log.d(TAG, "updateView called");
        this.numberImagesList = numberImageList;
        listAdapter.updateData(this.numberImagesList);
    }

    /**
     * Implementation of NumberImageView.getContext
     *
     * @return - Context
     */
    @Override
    public Context getContext() {
        return this;
    }

    /**
     * Implementation of NumberImageView.isGoingAway
     *
     * @return true if View is finishing, false otherwise
     */
    @Override
    public boolean isGoingAway() {
        return isFinishing();
    }

    /**
     * Method to populate data for this View.
     *
     * @param bundle - Bundle
     */
    private void populateViewData(final Bundle bundle) {
        Log.d(TAG, "populateViewData called with Bundle = " + (bundle != null));
        if (bundle != null &&
                bundle.containsKey(KEY_NUMBER_IMAGE_LIST)) {
            ArrayList<NumberImagesEntity> list =
                    bundle.getParcelableArrayList(KEY_NUMBER_IMAGE_LIST);
            if (list != null && list.size() > 0) {
                Log.i(TAG, "populateViewData - Bundle has NumberImagesEntity list, reuse existing data");
                updateView(list);
            }
            else {
                Log.i(TAG, "populateViewData - Bundle does not have NumberImagesEntity list, fetch data");
                presenter.fetchNumberImageData();
            }
        }
        else {
            Log.i(TAG, "populateViewData - Bundle is null or does not have data, fetch data");
            presenter.fetchNumberImageData();
        }
    }
}
