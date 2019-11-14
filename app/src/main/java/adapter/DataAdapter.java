package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ronanp.pokeapiapp.R;
import java.util.ArrayList;

import model.Pokemon;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private java.util.List<Pokemon> pokemonResponces = new ArrayList<Pokemon>();
    private onItemClickListener listener;

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    public DataAdapter() {
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.poke_name.setText(pokemonResponces.get(position).getName());
        holder.poke_url.setText(pokemonResponces.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return pokemonResponces.size();
    }

    public void refreshData(java.util.List<model.Pokemon> data) {
        pokemonResponces.clear();
        pokemonResponces.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView poke_name;
        private TextView poke_url;

        public ViewHolder(View itemView) {
            super(itemView);

            poke_name = (TextView)itemView.findViewById(R.id.poke_name);
            poke_url = (TextView)itemView.findViewById(R.id.poke_url);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}