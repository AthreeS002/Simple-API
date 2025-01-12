package cc.k3521004.barang.controller;

public class StudentRegisterDto extends StudentDto{
    private String password;

    public StudentRegisterDto() {
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
