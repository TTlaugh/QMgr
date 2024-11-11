package utils;

public enum DaoErrorCode {
    // Kết nối cơ sở dữ liệu
    FAILED_CONNECTION(1001, "Failed to connect to the database", "Không thể kết nối đến cơ sở dữ liệu."),
    FAILED_CONNECTION_TIMEOUT(1002, "Connection to the database timed out", "Hết thời gian kết nối đến cơ sở dữ liệu."),
    FAILED_CONNECTION_CREDENTIALS(1003, "Invalid database credentials", "Xác thực kết nối không thành công."),

    // Truy vấn cơ sở dữ liệu
    FAILED_INVALID_QUERY(2001, "Invalid query", "Câu truy vấn không hợp lệ."),
    FAILED_QUERY_TIMEOUT(2002, "Query execution timed out", "Quá thời gian thực thi câu truy vấn."),
    FAILED_NO_DATA_RETURNED(2003, "No data returned from query", "Không có dữ liệu nào được trả về từ câu truy vấn."),
    FAILED_SQL_INJECTION(2004, "Potential SQL injection detected", "Phát hiện lỗi bảo mật liên quan đến SQL Injection."),

    // Xử lý dữ liệu
    FAILED_DATA_INTEGRITY(3001, "Data integrity violation", "Lỗi toàn vẹn dữ liệu."),
    FAILED_DATA_TYPE_MISMATCH(3002, "Data type mismatch", "Kiểu dữ liệu không hợp lệ."),
    FAILED_DATA_NOT_FOUND(3003, "Data not found", "Không tìm thấy dữ liệu yêu cầu."),

    // Thao tác với dữ liệu
    FAILED_INSERT(4001, "Insert operation failed", "Không thể chèn dữ liệu vào bảng."),
    FAILED_UPDATE(4002, "Update operation failed", "Không thể cập nhật dữ liệu."),
    FAILED_DELETE(4003, "Delete operation failed", "Không thể xóa dữ liệu."),

    // Giao dịch
    FAILED_TRANSACTION(5001, "Transaction failed", "Lỗi khi thực hiện giao dịch."),
    FAILED_TRANSACTION_ROLLBACK(5002, "Transaction rolled back", "Giao dịch bị rollback."),
    FAILED_TRANSACTION_COMMIT(5003, "Transaction commit failed", "Không thể commit giao dịch."),

    // Lỗi khác
    FAILED_UNKNOWN(6001, "Unknown error occurred", "Lỗi không xác định."),
    FAILED_DUPLICATE_ENTRY(6002, "Duplicate entry detected", "Trùng lặp dữ liệu."),

    // Quyền truy cập
    FAILED_ACCESS_DENIED(7001, "Access denied", "Từ chối quyền truy cập vào cơ sở dữ liệu."),
    FAILED_ACCESS_FORBIDDEN(7002, "Access forbidden", "Quyền bị cấm."),

    // Lỗi về khóa
    FAILED_LOCK_ACQUISITION(8001, "Lock acquisition failed", "Không thể khóa bản ghi hoặc bảng."),
    FAILED_DEADLOCK(8002, "Deadlock detected", "Phát hiện deadlock trong cơ sở dữ liệu."),

    // Tài nguyên
    FAILED_RESOURCE_LIMIT(9001, "Resource limit exceeded", "Vượt quá giới hạn tài nguyên."),
    FAILED_TOO_MANY_CONNECTIONS(9002, "Too many database connections", "Số lượng kết nối tới cơ sở dữ liệu vượt quá giới hạn cho phép.");

    private final int code;
    private final String message;
    private final String desc;  // Thêm thuộc tính desc để lưu mô tả chi tiết

    DaoErrorCode(int code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDesc() {
        return desc;
    }
}
