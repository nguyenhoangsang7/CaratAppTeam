package com.example.caratexpense.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caratexpense.R;
import com.example.caratexpense.models.Transaction;
import com.example.caratexpense.utils.IconUtils;
import com.example.caratexpense.models.TransactionWithCategory;
import com.example.caratexpense.models.Category;


import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class  TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    private List<TransactionWithCategory> transactions;
    private OnTransactionClickListener listener;

    public interface OnTransactionClickListener {//Adapter này dùng để hiển thị danh sách các giao dịch (TransactionWithCategory) trong RecyclerView.
        void onTransactionClick(TransactionWithCategory transactionWithCategory);
    }

    //Constructor
    //Khởi tạo adapter với danh sách giao dịch cùng listener sự kiện click.
    public TransactionAdapter(List<TransactionWithCategory> transactions, OnTransactionClickListener listener) {
        this.transactions = transactions;
        this.listener = listener;
    }



    //Tạo ViewHolder
    //Tạo view cho từng item dựa trên layout item_transaction.xml
    //Trả về TransactionViewHolder để quản lý view này.
    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }


    // Gán dữ liệu cho ViewHolder
    //Lấy TransactionWithCategory ở vị trí position.
    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        TransactionWithCategory transaction = transactions.get(position);
        holder.bind(transaction);

    }

    //Trả về số lượng giao dịch để RecyclerView biết hiển thị bao nhiêu item.
    @Override
    public int getItemCount() {
        return transactions.size();
    }
    
    class TransactionViewHolder extends RecyclerView.ViewHolder {//ViewHolder giữ các tham chiếu đến view con trong item:
        private ImageView ivCategoryIcon;
        private TextView tvCategoryName, tvDateTime, tvNote, tvType, tvAmount;


        // Khởi tạo ViewHolder, tìm view con và đăng ký sự kiện click
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategoryIcon = itemView.findViewById(R.id.iv_category_icon);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
            tvDateTime = itemView.findViewById(R.id.tv_date_time);
            tvNote = itemView.findViewById(R.id.tv_note);
            tvType = itemView.findViewById(R.id.tv_type);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onTransactionClick(transactions.get(position));

                }
            });
        }

        public void bind(TransactionWithCategory transactionWithCategory) {
            Transaction t = transactionWithCategory.getTransaction();
            Category c = transactionWithCategory.getCategory();

            ivCategoryIcon.setImageResource(IconUtils.getIconResourceId(itemView.getContext(), c.getIconName()));
            tvCategoryName.setText(c.getName());
            tvDateTime.setText(t.getDateTime());
            tvNote.setText(t.getNote());
            tvType.setText(t.isIncome() ? "Thu" : "Chi tiêu");
            tvType.setTextColor(itemView.getContext().getResources().getColor(t.isIncome() ? R.color.blue : R.color.red));
            NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
            tvAmount.setText(formatter.format(t.getAmount()));
            tvAmount.setTextColor(itemView.getContext().getResources().getColor(t.isIncome() ? R.color.blue : R.color.red));
        }

    }
}
