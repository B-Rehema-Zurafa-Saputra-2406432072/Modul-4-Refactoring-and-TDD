<details>
<summary>Reflection Modul 4</summary>

### Alur TDD
Saya merasa alur Test-Driven Development (TDD) yang diterapkan dalam tutorial ini berguna.

Menulis test terlebih dahulu (fase Red) membuat saya memikirkan bagaimana kode akan bekerja memikirkan detail implementasinya. Selain itu, TDD memudahkan pemikiran alur  melakukan proses refactoring, seperti saat membuat OrderStatus. 

Untuk hal yang perlu diperbaiki atau dijagoin adalah terkadang proses TDD terasa lambat di awal karena harus bolak-balik antara test dan kode asli serta memikirkan seluruh *edge case* yang mungkin ada.

### Prinsip F.I.R.S.T
Secara keseluruhan, saya merasa unit test yang telah dibuat dalam tutorial ini sudah cukup baik dalam mengikuti prinsip F.I.R.S.T.:

* **Fast:** Tes berjalan cepat karena hanya berupa unit test sederhana.
* **Independent:** Penggunaan @BeforeEach untuk menginisialisasi ulang list produk dan mock objects membuat setiap metode tes berjalan secara independen dan tidak ada sisa data dari tes sebelumnya yang akan memengaruhi hasil tes saat ini.
* **Repeatable:** Tes ini dapat dijalankan di *environment* apapun dan akan selalu memberikan hasil yang sama. 
* **Self-Validating:** Setiap tes menggunakan assertions sehingga sistem secara otomatis mengembalikan nilai pass atau fail tanpa perlu inspeksi manual.
* **Timely (Tepat Waktu):** Mengikuti alur TDD, tes ditulis sebelum kode asli ditulis.

Dalam hal prinsip F.I.R.S.T, hal yang dapat di-improve kedepannya adalah pembuatan unit test yang lebih simple lagi sehingga aspek F.I.R.S.T lebih pasti diikuti. 
</details>

<details>
<summary>Reflection Modul 3</summary>

### SOLID yang diterapkan (Code yang diubah)

* Single Responsibility Principle (SRP): Memisahkan class CarController dari ProductController.java dan membuatnya file sendiri. Hal ini agar 1 file memiliki 1 pekerjaan.
* Open/Closed Principle (OCP): Memodifikasi metode update pada ProductRepository dan CarRepository. Sekarang update langsung meng-set objek di repo dengan objek baru.
* Liskov Substitution Principle (LSP): Menghilangkan "extends ProductController" di CarController.java. Hal ini karena keduanya tidak memiliki hubungan satu sama lain.
* Dependency Inversion Principle (DIP): Pada CarController, mengubah injeksi @Autowired dari ke concrete class menjadi inteface.

### SOLID Sudah Terterapkan
* Interface Segregation Principle (ISP): interface ProductService dan CarSerevice berisi metode-metode yang benar-benar dibutuhkan.

### Keuntungan menerapkan SOLID:
* Keuntungan SRP: Karena CarController dan ProductController sudah terpisah, untuk mengubah controller untuk suatu model akan mudah.
* Keuntungan OCP: Memudahkan jika menambah field baru pada model.
* Keuntungan DIP: Karena CarController bergantung interface, jika membuat unit test untuk CarController tidak perlu koneksi asli ke CarServiceImpl (bisa di mock).

### Kerugian tidak menerapkan SOLID:
* Kerugian mengabaikan LSP: Kalau CarController masih meng-extends ProductController, maka kita memaksa CarController untuk mewarisi metode-metode Produk.
* Kerugian mengabaikan DIP: Kalau CarController bergantung langsung pada CarServiceImpl, komponen tersebut terikat kuat, sehingga jika ingin membuat impl baru, CarController harus diubah
</details>

<details>
<summary>Reflection Modul 2</summary>
Terdapat beberapa perbaikan isu code yang saya lakukan setelah dideteksi SonarCloud. Pertama, adalah menambah assertions 
untuk test yang belum ada assertionnya. Selain itu, mengganti _floating tag_ pada workflow deploy_azure.yml dengan commit 
hash. Menurut saya implementasi workflow sekarang sudah memenuhi CI dan CD. Untuk bagian CI, setiap ada perubahan kode yang di-push 
atau di-merge ke, workflow sudah memakai Github Actions yang secara otomatis menjalankan rangkaian jobs seperti test suite 
dan sonar. Untuk bagian CD, saya sudah membuat workflow untuk melakukan deployment secara otomatis ke Azure Cloud. Selain itu, saya
juga sudah mengonfigurasi bahwa workflow deploy hanya berjalan jika CI pass semua.
</details>