package com.android.delivery.view.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.delivery.api.UserAPI;
import com.android.delivery.databinding.ActivityAddressDetailBinding;
import com.android.delivery.utils.PreferenceManger;
import com.android.delivery.view.main.MainActivity;

public class AddressDetailActivity extends AppCompatActivity {

    private ActivityAddressDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddressDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String roadNameAddress = intent.getExtras().getString("roadNameAddress");
        String buildingManagementNum = intent.getExtras().getString("buildingManagementNum");

        binding.addressDetailTitle.setText(title);
        binding.addressDetailRoadNameAddress.setText(roadNameAddress);

        binding.addressDetailFinishButton.setOnClickListener(view -> {
            String detailAddress = binding.addressDetailDetailAddress.getText().toString();
            Log.i("ADDRESS_DETAIL", buildingManagementNum+" "+detailAddress);

            PreferenceManger.setString(this, "addressCode", buildingManagementNum.substring(0, 10));
            PreferenceManger.setString(this,"detailAddress", detailAddress);
            PreferenceManger.setString(this, "roadNameAddress", roadNameAddress);
            PreferenceManger.setString(this, "addressTitle", title);

            Toast.makeText(this, "주소 설정 완료", Toast.LENGTH_LONG).show();

            Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(homeIntent);
        });

    }
}