package cc.k3521004.barang.controller;

public class OutputDto<T> {
    private T data;
    private String message;

    public OutputDto() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
