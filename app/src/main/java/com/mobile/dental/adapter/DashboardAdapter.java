package com.mobile.dental.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.dental.R;
import com.mobile.dental.model.Dashboard;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashbordHolder> {

    private final Context mContext;
    private final List<Dashboard> mData;
    private final View mEmptyView;
    private final ClickHandler mHandler;

    public DashboardAdapter(Context mContext, List<Dashboard> mData, View mEmptyView, ClickHandler mHandler) {
        this.mContext = mContext;
        this.mData = mData;
        this.mEmptyView = mEmptyView;
        this.mHandler = mHandler;
    }

    @NonNull
    @Override
    public DashbordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_verify_home, parent, false);
        return new DashbordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashbordHolder holder, int position) {
        Dashboard dashboard = mData.get(position);

        holder.tvNamaPasien.setText(dashboard.getNamaPasient());
        holder.tvJenisKelamin.setText(dashboard.getGender());
        holder.tvUmur.setText(dashboard.getUmur());
        holder.tvGolDar.setText(dashboard.getGolDarah());
        holder.tvKeluhan.setText(dashboard.getKeluhan());
        holder.tvTanggalPendaftaran.setText(dashboard.getTanggalPendaftaran());
        holder.tvWaktuPelayanan.setText(dashboard.getWaktuPelayanan());
        holder.tvTanggalPelayanan.setText(dashboard.getTanggalPelayanan());

        if (dashboard.getIdStatus().equals("0")) {
            holder.tvStatus.setText("Pendaftaran belum terverifikasi");
            holder.tvInformasi.setText("Menunggu diverifikasi olek klinik");
        } else {
            holder.tvStatus.setText("Pendaftaran sudah terverifikasi");
            holder.tvInformasi.setText("Silahkan datang ke klinik dengan tepat waktu untuk melakukan konsultasi dan pembayaran. Hasil dokter pada menu riwayat setelah melakukan pembayaran");
        }

        holder.btnCancle.setOnClickListener(v -> mHandler.onCancleClicked(position, dashboard));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void updateView() {
        if (mData.size() == 0)
            mEmptyView.setVisibility(View.VISIBLE);
        else
            mEmptyView.setVisibility(View.GONE);
    }

    public void addDashboard(List<Dashboard> dashboards) {
        this.mData.addAll(dashboards);
        notifyDataSetChanged();

        updateView();
    }

    public void removeDashboard(int position) {
        this.mData.remove(position);
        notifyDataSetChanged();

        updateView();
    }

    static class DashbordHolder extends RecyclerView.ViewHolder {

        final TextView tvNamaPasien;
        final TextView tvJenisKelamin;
        final TextView tvUmur;
        final TextView tvGolDar;
        final TextView tvKeluhan;
        final TextView tvTanggalPendaftaran;
        final TextView tvWaktuPelayanan;
        final TextView tvTanggalPelayanan;

        final TextView tvStatus;
        final TextView tvInformasi;
        final Button btnCancle;

        public DashbordHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaPasien = itemView.findViewById(R.id.textview_list_item_dashboard_nama);
            tvJenisKelamin = itemView.findViewById(R.id.textview_list_item_dashboard_gender);
            tvUmur = itemView.findViewById(R.id.textview_list_item_dashboard_umur);
            tvGolDar = itemView.findViewById(R.id.textview_list_item_dashboard_goldar);
            tvKeluhan = itemView.findViewById(R.id.textview_list_item_dashboard_keluhan);
            tvTanggalPendaftaran = itemView.findViewById(R.id.textview_list_item_dashboard_tanggal_pendaftaran);
            tvWaktuPelayanan = itemView.findViewById(R.id.textview_list_item_dashboard_waktu_pelayanan);
            tvTanggalPelayanan = itemView.findViewById(R.id.textview_list_item_dashboard_tanggal_pelayanan);

            tvStatus = itemView.findViewById(R.id.textview_list_item_dashboard_status);
            tvInformasi = itemView.findViewById(R.id.textview_list_item_dashboard_information);
            btnCancle = itemView.findViewById(R.id.button_list_item_dashboard_batal);

        }
    }

    public interface ClickHandler {
        void onCancleClicked(int position, Dashboard dashboard);
    }
}
