package ag.granular.codingassignment.view;

import android.content.Context;

import java.util.List;

import ag.granular.codingassignment.data.NumberImagesEntity;

/**
 * Interface for View that is used to
 * show Number text and Number image.
 *
 */
public interface NumberImageView {
    void updateView(List<NumberImagesEntity> numberImageList);
    Context getContext();
    boolean isAvailable();
    void showProgress();
    void hideProgress();
}
