package ag.granular.codingassignment.model;

import java.util.List;

import ag.granular.codingassignment.data.NumberImagesEntity;

/**
 * This interface is used to receive result
 * of NetworkImageEntity object list.
 *
 */
public interface NumberImageReceiver {
    void onDataFetched(boolean success, List<NumberImagesEntity> numberImageList);
}
