package com.example.caratexpense.models;
//Class này dùng với thư viện Room (thư viện ORM cho SQLite trên Android)
// để ánh xạ đối tượng Java thành bảng dữ liệu trong cơ sở dữ liệu SQLite.
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Index;
import java.io.Serializable;
//Là các lớp dữ liệu (data class, entity) đại diện cho cấu trúc thông tin trong ứng dụng.
@Entity(tableName = "transactions",
        foreignKeys = @ForeignKey(entity = Category.class,
                parentColumns = "id",
                childColumns = "categoryId",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "categoryId")})
public class Transaction implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private double amount;
    private String note;
    private String dateTime;
    private long categoryId;
    private boolean isIncome;

    @Ignore
    private Category category; // Not saved in DB

    // ✅ Constructor Room sử dụng
    // Constructor mặc định Room sử dụng để khởi tạo object khi đọc dữ liệu từ DB.
    public Transaction(long id, double amount, String note, String dateTime, long categoryId, boolean isIncome) {
        this.id = id;
        this.amount = amount;
        this.note = note;
        this.dateTime = dateTime;
        this.categoryId = categoryId;
        this.isIncome = isIncome;
    }

    // ✅ Constructor phụ dùng cho UI (Room sẽ bỏ qua) vì có @Ignore.
    @Ignore
    public Transaction(long id, double amount, String note, String dateTime, long categoryId, Category category, boolean isIncome) {
        this.id = id;
        this.amount = amount;
        this.note = note;
        this.dateTime = dateTime;
        this.categoryId = categoryId;
        this.category = category;
        this.isIncome = isIncome;
    }

    // --- Getter/Setter ---
    //Các phương thức này dùng để đọc và ghi giá trị cho từng thuộc tính (field) của Transaction.
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
