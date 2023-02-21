package com.example.ripetizioni;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class professor_settingsFragment extends Fragment implements View.OnClickListener {

    private TextInputLayout textField_costoOra, textField_name, textField_bio;

    private TextView tv_hours, tv_materie;
    private ImageView img;

    private Button bt_salva;

    public professor_settingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_professor_settings, container, false);

//        img = view.findViewById(R.id.profilePicture_ImageView);

        textField_name = view.findViewById(R.id.textField_name);
        textField_bio = view.findViewById(R.id.textField_bio);

        tv_hours = view.findViewById(R.id.tv_hours);
        tv_hours.setOnClickListener(this);
        tv_materie = view.findViewById(R.id.tv_materie);
        tv_materie.setOnClickListener(this);

        bt_salva = view.findViewById(R.id.bt_salva);
        bt_salva.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_hours:
                DialogGiorniOrariProfessore dialogGiorniOrariProfessore = new DialogGiorniOrariProfessore();
                dialogGiorniOrariProfessore.show(getActivity().getSupportFragmentManager(), "Seleziona i giorni e gli orari");

            case R.id.tv_materie:
                DialogMaterieProfessore dialogMaterieProfessore = new DialogMaterieProfessore();
                dialogMaterieProfessore.show(getActivity().getSupportFragmentManager(), "Seleziona le materie insegnate");

            case R.id.bt_salva:
                updateProfessorData();
        }




    }

    private void updateProfessorData() {

    }
//
//
//
//    String name;
//    String[] orari;
//
//    boolean expandend = false;
//    String today;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View view = inflater.inflate(R.layout.fragment_place, container, false);
//
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
//
//        ImageView img = view.findViewById(R.id.imageView2);
//
//
//        Bundle bundle = getArguments();
//        String url = bundle.getString("url");
//        name = bundle.getString("name");
//        address = bundle.getString("address");
//        String reviewsNumber = "" + bundle.getInt("reviews_number");
//        phone = bundle.getString("phone");
//        website = bundle.getString("website");
//        //String businessStatus = bundle.getString("business_status");
//        float rating = bundle.getFloat("rating");
//        orari = bundle.getStringArray("opening_hours");
//
//        ratingbar = view.findViewById(R.id.ratingbar);
//        tvName = view.findViewById(R.id.tv_name);
//        tvReviewsNumber = view.findViewById(R.id.tv_reviewsnumber);
//        tvAddress = view.findViewById(R.id.tv_address);
//        tvHours = view.findViewById(R.id.tv_hours);
//        tvPhone = view.findViewById(R.id.tv_phone);
//        tvWebsite = view.findViewById(R.id.tv_website);
//
//        tvName.setText(name);
//        tvAddress.setText(address);
//        tvReviewsNumber.setText(reviewsNumber);
//        tvPhone.setText(phone);
//        tvWebsite.setText(website);
//        ratingbar.setRating(rating);
//
//
//        Calendar calendar = Calendar.getInstance();
//        Date date = calendar.getTime();
//        today = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
//
//        tvHours.setLineSpacing(12,1);
//
//        if(orari == null){
//            tvHours.setText("null");
//        }
//        else{
//            for(int i=0; i<orari.length;i++){
//                if(orari[i].contains(today)){
//                    today = orari[i].replaceAll("\\[|\\]|\\,", "").trim();
//                    tvHours.setText(today);
//                    break;
//                }
//            }
//        }
//
//
//        ll_address = view.findViewById(R.id.ll_address);
//        ll_address.setOnClickListener(this);
//
//        ll_phone = view.findViewById(R.id.ll_phone);
//        ll_phone.setOnClickListener(this);
//
//        ll_website = view.findViewById(R.id.ll_website);
//        ll_website.setOnClickListener(this);
//
//        bt_share = view.findViewById(R.id.bt_share);
//        bt_share.setOnClickListener(this);
//
//        ll_hours = view.findViewById(R.id.ll_hours);
//        ll_hours.setOnClickListener(this);
//
//        Glide.with(view.getContext()).load(url).into(img);
//
//        return view;
//    }
//
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.ll_address:
//                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+address); //mostra sulla mappa dove si trova
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
//                break;
//
//            case R.id.ll_phone:
//                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
//                phoneIntent.setData(Uri.parse("tel:" + phone));
//                startActivity(phoneIntent);
//                break;
//
//            case R.id.ll_website:
//                Uri webpage = Uri.parse(website);
//                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
//                startActivity(webIntent);
//                break;
//
//            case R.id.bt_share:
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, website+"\n\n"+name);
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);
//                break;
//            case R.id.ll_hours:
//                if(expandend){
//                    tvHours.setText(today);
//                    expandend = false;
//                }
//                else{
//                    tvHours.setText(" " + Arrays.toString(orari).replaceAll("\\[|\\]|\\,", "").trim());
//                    expandend = true;
//                }
//                break;
//
//        }
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
//    }
}