package ag.granular.codingassignment;

import ag.granular.codingassignment.network.INetworking;
import ag.granular.codingassignment.network.Networking;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class MainActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
    }
}
