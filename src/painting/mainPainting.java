package painting;

public class mainPainting{

    public static void main(String[] args) {
        try {
            // LOGIN NAMA PENGGUNA
            new LoginPainting().setVisible(true);
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan: " + e.getMessage());
        }
    }
   
}
