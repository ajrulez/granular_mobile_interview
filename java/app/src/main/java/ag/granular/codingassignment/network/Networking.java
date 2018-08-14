package ag.granular.codingassignment.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Networking implements INetworking {

    @Override
    public void requestStringDataWithURL(Context context, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        queue.add(request);
    }

}
