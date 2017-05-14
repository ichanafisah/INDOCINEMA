package id.sch.smktelkom_mlg.privateassignment.xirpl313.indocinema;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl313.indocinema.adapter.NowPlayingAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl313.indocinema.model.Results;
import id.sch.smktelkom_mlg.privateassignment.xirpl313.indocinema.model.ResultsRespone;
import id.sch.smktelkom_mlg.privateassignment.xirpl313.indocinema.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl313.indocinema.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowFragment extends Fragment {

    ArrayList<Results> mlist = new ArrayList<>();
    NowPlayingAdapter myNowPlaying;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.fragment_now, container, false);

        RecyclerView rv = (RecyclerView) rootview.findViewById(R.id.recycler);
        rv.setHasFixedSize(true);
        myNowPlaying = new NowPlayingAdapter(this, mlist, getContext());
        rv.setAdapter(myNowPlaying);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        downloadDataResource();
        return rootview;
    }

    private void downloadDataResource() {
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=5c1907a4d80e43ad36c9141b6e69a8d6&language=en-US&page=1";

        GsonGetRequest<ResultsRespone> myRequest = new GsonGetRequest<ResultsRespone>
                (url, ResultsRespone.class, null, new Response.Listener<ResultsRespone>() {

                    @Override
                    public void onResponse(ResultsRespone response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        mlist.addAll(response.results);
                        myNowPlaying.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(myRequest);
    }

}

