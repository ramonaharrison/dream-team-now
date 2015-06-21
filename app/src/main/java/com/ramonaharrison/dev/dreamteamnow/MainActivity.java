package com.ramonaharrison.dev.dreamteamnow;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        List<ContactInfo> contacts = new ArrayList<>();
        for(int i = 1; i <= 20; i++){
            ContactInfo contact = new ContactInfo();
            contact.name = "First" + i;
            contact.surname = "Last" + i;
            contact.email = "Contact_" + i + " @email.com";
            contacts.add(contact);
        }

        CardAdapter cAdapter = new CardAdapter(contacts);
        recList.setAdapter(cAdapter);
    }

    public class ContactInfo {
        protected String name;
        protected String surname;
        protected String email;
        protected static final String NAME_PREFIX = "Name_";
        protected static final String SURNAME_PREFIX = "Surname_";
        protected static final String EMAIL_PREFIX = "email_";
    }




    public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ContactViewHolder> {

        private List<ContactInfo> contactList;
        private int lastPosition = -1;

        public CardAdapter(List<ContactInfo> contactList) {
            this.contactList = contactList;
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }

        @Override
        public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
            ContactInfo ci = contactList.get(i);
            contactViewHolder.vName.setText(ci.name);
            contactViewHolder.vSurname.setText(ci.surname);
            contactViewHolder.vEmail.setText(ci.email);
            contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);

            setAnimation(contactViewHolder.vCardView, i);

        }

        private void setAnimation(View viewToAnimate, int position)
        {
            // If the bound view wasn't previously displayed on screen, it's animated
            if (position > lastPosition)
            {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_bottom);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }

        @Override
        public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.card_view, viewGroup, false);

            return new ContactViewHolder(itemView);
        }

        public class ContactViewHolder extends RecyclerView.ViewHolder {
            protected TextView vName;
            protected TextView vSurname;
            protected TextView vEmail;
            protected TextView vTitle;
            protected CardView vCardView;

            public ContactViewHolder(View v) {
                super(v);
                vName =  (TextView) v.findViewById(R.id.txtName);
                vSurname = (TextView)  v.findViewById(R.id.txtSurname);
                vEmail = (TextView)  v.findViewById(R.id.txtEmail);
                vTitle = (TextView) v.findViewById(R.id.title);
                vCardView = (CardView) v.findViewById(R.id.card);
            }
        }


    }

}
