package id.sch.smktelkom_mlg.privateassignment.xirpl313.indocinema.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl313.indocinema.PopulerFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl313.indocinema.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl313.indocinema.model.Results;

/**
 * Created by Icha on 14-May-17.
 */

public class PopulerAdapter extends RecyclerView.Adapter<PopulerAdapter.ViewHolder> {
    public String url = "https://image.tmdb.org/t/p/w500";
    public String image;
    ArrayList<Results> populerList;
    PopulerFragment populerFragment;
    Context context;
    private int lastposition = -1;

    public PopulerAdapter(PopulerFragment populerFragment, ArrayList<Results> movieList, Context context) {
        this.populerList = movieList;
        this.populerFragment = populerFragment;
        this.context = context;

    }

    @Override
    public PopulerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PopulerAdapter.ViewHolder holder, int position) {
        Results results = populerList.get(position);
        holder.tvJudul.setText(results.title);
        holder.tvDeskripsi.setText(results.overview);
        image = url + results.backdrop_path;
        Glide.with(context).load(image)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivFoto);

    }

    @Override
    public int getItemCount() {
        if (populerList != null)
            return populerList.size();
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvJudul;
        TextView tvDeskripsi;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewName);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDesc);
        }
    }
}

