package com.example.marketapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.marketapp.MainActivity;
import com.example.marketapp.R;
import com.example.marketapp.adpater.FindStoreAdapter;
import com.example.marketapp.interfaceApp.RecyclerViewClickInterface;
import com.example.marketapp.models.GetStoreF;
import com.example.marketapp.models.StoreDistrict;
import com.example.marketapp.models.StoreDistrictSub;
import com.example.marketapp.models.StoreM;
import com.example.marketapp.models.StoreWard;
import com.example.marketapp.service.CallApi;
import com.example.marketapp.service.Constants;
import com.example.marketapp.service.RequestBase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindStoreActivity extends AppCompatActivity implements RecyclerViewClickInterface {
    RecyclerView rvStore;
    FindStoreAdapter storeAdapter;
    List<StoreM> storeList;

    EditText edStore;
    ImageView ivSearch;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_store);
        rvStore = findViewById(R.id.rvStore);
        edStore = findViewById(R.id.edStore);
        ivSearch = findViewById(R.id.ivSearch);

        storeList = new ArrayList<StoreM>();

        loadDataApi();

        linearLayoutManager = new LinearLayoutManager(FindStoreActivity.this);
        rvStore.setLayoutManager(linearLayoutManager);

        findStore();
    }

    private void loadDataApi() {
        Intent intent = getIntent();
        String token = intent.getStringExtra("TOKEN");
        CallApi.callApi.getAllStore(token).enqueue(new Callback<GetStoreF>() {
            @Override
            public void onResponse(Call<GetStoreF> call, Response<GetStoreF> response) {
                storeList.clear();
                GetStoreF getStoreF = response.body();
                if (getStoreF != null && response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "" + getStoreF.isSuccess(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    List<StoreDistrict> district = getStoreF.getGetStore();
                    for (int i = 0; i < district.size(); i++) {
                        StoreDistrict storeDistrict = district.get(i);
                        storeDistrict.getId();
                        storeDistrict.getName_district();

                        List<StoreDistrictSub> districtSubs = storeDistrict.getDistrict();
                        for (int x = 0; x < districtSubs.size(); x++) {
                            StoreDistrictSub districtSub = districtSubs.get(x);
                            districtSub.getId();
                            districtSub.getName_district();

                            List<StoreWard> storeWards = districtSub.getWards();
                            for (int y = 0; y < storeWards.size(); y++) {
                                StoreWard storeWard = storeWards.get(y);
                                storeWard.getId();
                                storeWard.getDistrict_id();
                                storeWard.getName_ward();

                                List<StoreM> mList = storeWard.getStores();
                                for (int z = 0; z < mList.size(); z++) {
                                    StoreM storeM = mList.get(z);
                                    int id = storeM.getId();
                                    String nameStore = storeM.getName();
                                    String siteCode = storeM.getSite_code();
                                    int wardId = storeM.getWard_id();

                                    storeList.add(new StoreM(id, nameStore, siteCode, wardId));
                                    storeAdapter = new FindStoreAdapter(FindStoreActivity.this, storeList, FindStoreActivity.this);


                                    rvStore.setAdapter(storeAdapter);
//                                storeAdapter.updateList(storeList);
                                    Log.d(RequestBase.TAG, storeList.toString());
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Server not responding!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetStoreF> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Call Api error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeIdStoreInSP(int idStore) {
        SharedPreferences sharedPref = FindStoreActivity.this.getSharedPreferences("SEND_ID_STORE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("ID_STORE", idStore);
        editor.commit();
    }

    private void findStore() {
        edStore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = edStore.getText().toString().trim();
                filter(s);
            }
        });
    }

    private void filter(String text) {
        List<StoreM> temp = new ArrayList();
        for (StoreM d : storeList) {
            //or use .equal(text) with you want equal match (Hoac su dung equal(text) với chuoi muon so sanh)
            //use .toLowerCase() for better matches(Chuyen chuoi thuong se so sanh tot hon)
            if (d.getName().contains(text)) {
                temp.add(d);
            }
//            //filter by id
//            if (d.getId() == Integer.parseInt(text)){
//                temp.add(d);
//            }
//            //filter by get_ward_id
//            if (d.getWard_id() == Integer.parseInt(text)){
//                temp.add(d);
//            }
        }
        //update recyclerview
        storeAdapter = new FindStoreAdapter(FindStoreActivity.this, temp, FindStoreActivity.this);
        rvStore.setAdapter(storeAdapter);
    }

    @Override
    public void onItemClick(int position) {
        String idStoreM = storeList.get(position).getSite_code();
        //writeIdStoreInSP(idStoreM);
        Constants.ID_STORE = idStoreM;
        Log.e("FSA",Constants.ID_STORE);
        Toast.makeText(FindStoreActivity.this, "" + storeList.get(position).getName(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FindStoreActivity.this, MainActivity.class));
        finish();
    }
}