package ag.granular.codingassignment.presenter;

import java.util.List;

import ag.granular.codingassignment.data.NumberImagesEntity;
import ag.granular.codingassignment.view.NumberImageView;

/**
 * Interface for Presenter for Number
 * Image.
 *
 */
public interface NumberImagePresenter {
    void addView(NumberImageView view);
    void removeView();
    void fetchNumberImageData();
}
