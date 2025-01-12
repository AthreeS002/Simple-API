package cc.k3521004.barang.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "students"
)
public class StudentEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "nama"
    )
    private String nama;
    @Column(
            name = "nim",
            unique = true
    )
    private String nim;
    @Column(
            name = "username"
    )
    private String username;
    @Column(
            name = "email"
    )
    private String email;
    @Column(
            name = "password"
    )
    private String password;
    @Column(
            name = "alamat"
    )
    private String alamat;

    public StudentEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return this.nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
