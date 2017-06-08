# Program painting objek
merupakan program membuat image yang berisi objek persegi, garis, kurva, dan lingkaran.

program ini dijalankan menggunakan Netbeans atau yang lain.
untuk bisa menggunakan library JDBC MYSQL, karena program juga disimpan pada database.

## Pertama di ubah
pada file **src/painting/simpanDB.java** ubahlah pada DB keneksinya
```java
    private static final String DB_USER = <nama user db>;
    private static final String DB_PASS = <password user db>;
    private static final String DB_URL = "jdbc:mysql://<host database>/<nama database>";
```

# NB Error pada inputan yang menggunakan spasi >= 2
