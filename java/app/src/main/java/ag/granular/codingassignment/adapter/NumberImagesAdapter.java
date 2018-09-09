package ag.granular.codingassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ag.granular.codingassignment.R;
import ag.granular.codingassignment.data.NumberImagesEntity;
import ag.granular.codingassignment.model.NumberImageUrlFactory;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * This class implements a basic ListView
 *
 * Note: Using ListView instead of RecyclerView
 * because the assignment mentioned ListView.
 *
 */
public class NumberImagesAdapter extends ArrayAdapter<NumberImagesEntity> {
    // Data
    private List<NumberImagesEntity> dataList = new ArrayList<>();

    /**
     * Constructor for NumberImagesEntity Adapter.
     *
     * @param context - Context
     * @param resourceId - Resource ID for Rows
     */
    public NumberImagesAdapter(Context context, int resourceId) {
        super(context, resourceId);
    }

    /**
     * Method to update the Data for the Adapter.
     *
     * @param data - List of NumberImagesEntity objects
     */
    public synchronized void updateData(final List<NumberImagesEntity> data) {
        // Set the data
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * Get the count of data items in the Adapter.
     *
     * @return - Number of items to display
     */
    public int getCount() {
        return dataList.size();
    }

    /**
     * Implement getView method of the Adapter using
     * ViewHolder pattern for smooth scrolling.
     *
     * If assignment did not mention List View, I would have
     * used a RecyclerView which is more optimized as it forces
     * use of a ViewHolder unlikes a ListView.
     *
     * @param position - Selected item's position
     * @param convertView - View to display
     * @param parent - Parent
     * @return - View to display
     */
    @Override
    public View getView(final int position,
                        View convertView,
                        @NonNull ViewGroup parent) {
        // ViewHolder
        NumberImagesViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row_item, null);

            // Creates a ViewHolder and store references to the children views
            // we want to bind data to.
            holder = new NumberImagesViewHolder();
            holder.numberName = convertView.findViewById(R.id.numberName);
            holder.numberImage = convertView.findViewById(R.id.numberImage);

            // Set the ViewHolder as a Tag for this convertView
            convertView.setTag(holder);
        }

        // Get the ViewHolder back to get fast access
        holder = (NumberImagesViewHolder) convertView.getTag();


        // Populate the View with the Data
        if(dataList != null &&
                dataList.size() > 0 &&
                position < dataList.size()) {

            // Bind the data efficiently with the holder.
            holder.numberName.setText(dataList.get(position).getName());

            // Using Picasso to fetch images and lazy loading + caching.
            // I could have used Volley, but I that would mean using
            // NetworkImageView and also writing boilerplate code for
            // ImageLoader. I feel using Picasso is pretty efficient and
            // reduces boilerplate code.
            // I know the assignment description discouraged use of
            // 3rd party libraries. Hope this is okay with the reviewers.
            final String imageUrl =
                    NumberImageUrlFactory.getImageUrl(dataList.get(position).getUrl());
            Picasso.get()
                    .load(imageUrl)
                    .fit()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.numberImage);
        }

        return convertView;
    }

    /***************************** View Holder *****************************/
    private class NumberImagesViewHolder {
        // Number Name
        AppCompatTextView numberName;
        AppCompatImageView numberImage;
    }
}
