package com.mobile.dental;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.mobile.dental.adapter.DashboardAdapter;
import com.mobile.dental.base.BaseActivity;
import com.mobile.dental.model.Dashboard;
import com.mobile.dental.model.DeletePendaftaran;
import com.mobile.dental.model.Profile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ActionBarDrawerToggle mToggle;
    private Button mComplaintsDissaseButton;
    private RecyclerView mDashboardRecycleview;
    private View mDashboardView;
    private DashboardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initial component
        init();

        //setData
        setData();

        //configure drawer menu
        setupDrawerMenu();
    }

    private void setData() {
        //action dari component
        mComplaintsDissaseButton.setOnClickListener(this);

        //setData
        setDashboardData();
    }

    private void init() {
        //initial component
        mComplaintsDissaseButton =  findViewById(R.id.button_main_dissase_complaint);
        mDashboardRecycleview = findViewById(R.id.recycle_dashboard);
        mDashboardView = findViewById(R.id.emptyview_dashboard);
    }

    private void setDashboardData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL,
                false);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                getApplicationContext(),
                layoutManager.getOrientation()
        );

        mDashboardRecycleview.setLayoutManager(layoutManager);
        mDashboardRecycleview.addItemDecoration(itemDecoration);

        List<Dashboard> dashboardList = new ArrayList<>();
        mAdapter = new DashboardAdapter(getApplicationContext(), dashboardList, mDashboardView, this::askDelete);
        mDashboardRecycleview.setAdapter(mAdapter);
        Call<List<Dashboard>> dashboardCall = mApiService.getDashboard(mSession.getAuthSession().getId());
        dashboardCall.enqueue(new Callback<List<Dashboard>>() {
            @Override
            public void onResponse(Call<List<Dashboard>> call, Response<List<Dashboard>> response) {
                if (response.code()!=200 || response.body()==null){
                    return;
                }

                List<Dashboard> dashboardsResponse = new ArrayList<>();

                for (Dashboard dashboard : response.body()){
                    if (dashboard.getIdStatus().equals("0") || dashboard.getIdStatus().equals("1")){
                        dashboardsResponse.add(dashboard);
                    }
                }
                Collections.reverse(dashboardsResponse);

                mAdapter.addDashboard(dashboardsResponse);
            }

            @Override
            public void onFailure(Call<List<Dashboard>> call, Throwable t) {

            }
        });



    }
    private void  askDelete(int position, Dashboard dashboard){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Batalkan Pesanan");
        builder.setMessage("Apakah anda ingin membatalkan pesanan ini");
        builder.setNegativeButton("Batal",((dialog, which) -> dialog.dismiss()));
        builder.setPositiveButton("Ya",((dialog, which) -> {
            dialog.dismiss();
            deleDashboard(position, dashboard);
        }));

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void deleDashboard(int position, Dashboard dashboard){
        showLoading(true);
        Call<DeletePendaftaran> deleteCall = mApiService.cancleDashboard(dashboard.getIdPendaftaran());
        deleteCall.enqueue(new Callback<DeletePendaftaran>() {
            @Override
            public void onResponse(Call<DeletePendaftaran> call, Response<DeletePendaftaran> response) {
                if (response.code()!=200 || response.body()==null){
                    showLoading(false);
                    toast("Gagal menghapus data");
                    return;
                }
                showLoading(false);
                toast(response.body().getMessage());

                mAdapter.removeDashboard(position);
            }

            @Override
            public void onFailure(Call<DeletePendaftaran> call, Throwable t) {
                showLoading(false);
            }
        });
    }
    private void setupDrawerMenu() {
        //initialize component from xml
        DrawerLayout drawer = findViewById(R.id.drawer_main);
        NavigationView navigationView = findViewById(R.id.nav_main);

        //toogle for arrow
        mToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(mToggle);
        mToggle.syncState();

        //show burger menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //drawer menu clicked
        navigationView.setNavigationItemSelectedListener(item -> {
            selectItemDrawer(item, drawer);
            return true;
        });
        //inisialisasi nama dan email
        TextView tvName = navigationView.getHeaderView(0).findViewById(R.id.textview_header_nama);
        TextView tvEmail = navigationView.getHeaderView(0).findViewById(R.id.textview_header_email);

        showName(tvName, tvEmail);
    }
    private void showName(TextView tvName, TextView tvEmail){
        Call<List<Profile>> profileCall = mApiService.getUser(mSession.getAuthSession().getId());
        profileCall.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if (response.code()!=200 || response.body()==null || response.body().size()==0){
                    return;
                }

                tvName.setText(response.body().get(0).getFullname());
                tvEmail.setText(response.body().get(0).getEmail());
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {

            }
        });
    }

    private void selectItemDrawer(MenuItem menuItem, DrawerLayout drawerLayout) {
        //item clicked in drawer menu
        //kenapa pkai post delay, biar ngga ngelag ketika item di klik
        switch (menuItem.getItemId()) {
            case R.id.action_home:
                drawerLayout.closeDrawers();
                break;
            case R.id.action_daftar:
                drawerLayout.closeDrawers();
                new Handler().postDelayed(() -> startActivity(new Intent(this, RegisterActivity.class)), 250);
                break;
            case R.id.action_history:
                drawerLayout.closeDrawers();
                new Handler().postDelayed(() -> {startActivity(new Intent(this, HistoryActivity.class));
                }, 250);
                break;
            case R.id.action_profile:
                drawerLayout.closeDrawers();
                new Handler().postDelayed(() -> {startActivity(new Intent(this, ProfileActivity.class));
                }, 250);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_main_dissase_complaint:
                startActivity(new Intent(getApplicationContext(),ChatBotActivity.class));
                break;
        }
    }
}