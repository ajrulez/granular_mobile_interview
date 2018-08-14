package ag.granular.codingassignment.network;

import android.content.Context;

import com.android.volley.Response;

public interface INetworking {

    void requestStringDataWithURL(Context context, String url, Response.Listener<String> listener, Response.ErrorListener errorListener);
}
