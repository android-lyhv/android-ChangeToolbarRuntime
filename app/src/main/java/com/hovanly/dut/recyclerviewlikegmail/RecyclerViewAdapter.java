package com.hovanly.dut.recyclerviewlikegmail;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Copyright@ AsianTech.Inc
 * Created by Ly Ho V. on 19/07/2017
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MessengerViewHolderItem> {
    private Context mContext;
    private List<MessengerGmail> messengerGmails;

    public RecyclerViewAdapter(Context mContext, List<MessengerGmail> messengerGmails) {
        this.mContext = mContext;
        this.messengerGmails = messengerGmails;
    }

    @Override
    public MessengerViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mesenger, parent, false);
        return new MessengerViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(MessengerViewHolderItem holder, int position) {
        holder.onBind(messengerGmails.get(position));
    }

    @Override
    public int getItemCount() {
        return messengerGmails == null ? 0 : messengerGmails.size();
    }

    public interface MessageAdapterListener {
        void onIconClicked(int position);

        void onIconImportantClicked(int position);

        void onMessageRowClicked(int position);

        void onRowLongClicked(int position);
    }

    public class MessengerViewHolderItem extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private TextView from, subject, message, iconText, timestamp;
        private ImageView iconImp, imgProfile;
        private LinearLayout messageContainer;
        private RelativeLayout iconContainer, iconBack, iconFront;
        private MessengerGmail messengerGmail;

        public MessengerViewHolderItem(View view) {
            super(view);
            from = (TextView) view.findViewById(R.id.from);
            subject = (TextView) view.findViewById(R.id.txt_primary);
            message = (TextView) view.findViewById(R.id.txt_secondary);
            iconText = (TextView) view.findViewById(R.id.icon_text);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
            iconBack = (RelativeLayout) view.findViewById(R.id.icon_back);
            iconFront = (RelativeLayout) view.findViewById(R.id.icon_front);
            iconImp = (ImageView) view.findViewById(R.id.icon_star);
            imgProfile = (ImageView) view.findViewById(R.id.icon_profile);
            messageContainer = (LinearLayout) view.findViewById(R.id.message_container);
            iconContainer = (RelativeLayout) view.findViewById(R.id.icon_container);
            view.setOnLongClickListener(this);
        }

        public void onBind(MessengerGmail messengerGmail) {
            // displaying text view data
            from.setText(messengerGmail.getFrom());
            subject.setText(messengerGmail.getSubject());
            message.setText(messengerGmail.getMessage());
            timestamp.setText(messengerGmail.getTimestamp());
            displayImage(messengerGmail.getPicture());
        }

        private void displayImage(String pathImage) {
            if (!TextUtils.isEmpty(pathImage)) {
                Glide.with(mContext).load(pathImage)
                        .thumbnail(0.5f)
                        .crossFade()
                        .transform(new CircleTransform(mContext))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgProfile);
                imgProfile.setColorFilter(null);
                iconText.setVisibility(View.GONE);
            } else {
                imgProfile.setImageResource(R.drawable.bg_circle);
                iconText.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
