package com.example.gmit_sdp.beinghuman;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Precisa on 01-Apr-18.
 */

public class setterAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public setterAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(setter object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        SetHolder setHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            setHolder = new SetHolder();
            //setHolder.image= (ImageView)row.findViewById(R.id.imageView5);
            setHolder.tvName = (TextView)row.findViewById(R.id.tname);
            setHolder.tvMobile =(TextView)row.findViewById(R.id.tmobile);
            setHolder.tvEmail=(TextView)row.findViewById(R.id.temail);
            setHolder.tvGender=(TextView)row.findViewById(R.id.tgender);

            row.setTag(setHolder);
        }
        else
        {
            setHolder = (SetHolder)row.getTag();
        }
        setter st = (setter)this.getItem(position);
        //setHolder.image.setImageDrawable(Drawable.createFromPath(st.getImage()));
        setHolder.tvName.setText(st.getName());
        setHolder.tvMobile.setText(st.getMobile());
        setHolder.tvEmail.setText(st.getEmail());
        setHolder.tvGender.setText(st.getGender());

        return row;
    }
    static class SetHolder
    {
        TextView tvName,tvMobile,tvEmail,tvGender;

    }

}
