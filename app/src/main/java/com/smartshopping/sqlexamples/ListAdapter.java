package com.smartshopping.sqlexamples;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 20/10/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ProductViewHolder>
{
    private Context mCtx;
    private List<Cources> productList;

    public ListAdapter(Context mCtx, List<Cources> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_adapter_view, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Cources cources = productList.get(position);
        //loading the image
        holder.textViewTitle.setText(cources.getBankname());
       // holder.textViewShortDesc.setText();
        //holder.textViewRating.setText(String.valueOf(cources.getRating()));
        holder.textViewPrice.setText(String.valueOf(cources.getAmunt()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, /*textViewShortDesc, textViewRating,*/ textViewPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            //textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            //textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
    }
}
}
        /*
        ViewItem viewItem;
        if(convertView == null)
        {
            viewItem = new ViewItem();
            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            //LayoutInflater layoutInfiater = LayoutInflater.from(context);
            convertView = layoutInfiater.inflate(R.layout.list_adapter_view,null,true);

            viewItem.txtTitle = (TextView) convertView.findViewById(R.id.adapter_text_title);
            viewItem.txtDescription = (TextView) convertView.findViewById(R.id.adapter_text_description);
            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.txtTitle.setText(valueList.get(position).bank_name);
        viewItem.txtDescription.setText(valueList.get(position).amount);
        return convertView;
    }
}

class ViewItem
{
    TextView txtTitle;
    TextView txtDescription;
}
*/
