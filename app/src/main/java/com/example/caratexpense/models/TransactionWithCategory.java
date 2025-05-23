package com.example.caratexpense.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;

public class TransactionWithCategory implements Serializable {

    @Embedded //tất cả các trường trong đối tượng Transaction sẽ được nhúng trực tiếp vào class
    public Transaction transaction;//dữ liệu các cột của bảng transactions sẽ được ánh xạ vào trường transaction

    @Relation( //thiết lập mối quan hệ giữa bảng transactions và categories:
            parentColumn = "categoryId",
            entityColumn = "id"
    )
    private Category category;

    // getter và setter
    //giúp truy cập và cập nhật trường transaction và category.
    public Transaction getTransaction() {
        return transaction;
    }

    public Category getCategory() {
        return category;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
