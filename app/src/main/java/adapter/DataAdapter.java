package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ronanp.pokeapiapp.R;
import java.util.ArrayList;
import model.Pokemon;
import model.PokemonResponce;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<PokemonResponce> pokemonResponces;


    public DataAdapter(ArrayList<PokemonResponce> pokemonResponces) {
        this.pokemonResponces = pokemonResponces;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.poke_name.setText(pokemonResponces.get(position).getNext());
        holder.poke_url.setText(pokemonResponces.get(position).getNext());
    }

    @Override
    public int getItemCount() {
        return pokemonResponces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView poke_name;
        private TextView poke_url;

        public ViewHolder(View itemView) {
            super(itemView);

            poke_name = (TextView)itemView.findViewById(R.id.poke_name);
            poke_url = (TextView)itemView.findViewById(R.id.poke_url);
        }
    }
}