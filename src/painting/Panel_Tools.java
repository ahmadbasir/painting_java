/*
    PANEL TOOLS UNTUK MENENTUKAN FUNGSI GAMBAR YANG AKAN DIGUNAKAN
    TERMASUK WARNA TOMBOL JIKA DITEKAN
*/

package painting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel_Tools extends JPanel implements ActionListener{
    private JButton garis_bebas, garis, kurva, persegi, persegi_panjang, lingkaran;
    private int chooser_tools; // SEBAGAI INDEX TOOLS YANG DIPILIH 
    
    //WARNA AWAL
    private final Color awal = new Color(238, 238, 238);
    //WARNA DIKLIK
    private final Color diklik = new Color(204, 255, 204);

    public Panel_Tools() {
        garis_bebas = new JButton("Garis Bebas");           // index 0
        garis = new JButton("Garis");                       // index 1
        kurva = new JButton("Kurva");                       // index 2
        persegi = new JButton("Persegi");                   // index 3
        persegi_panjang = new JButton("Persegi Panjang");   // index 4
        lingkaran = new JButton("Lingkaran");               // index 5
        makeDefaultBackgroudButton();
        garis_bebas.setBackground(diklik);
        chooser_tools = 0;

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(garis_bebas);
        this.add(garis);
        this.add(kurva);
        this.add(persegi);
        this.add(persegi_panjang);
        this.add(lingkaran);
        
        garis_bebas.addActionListener(this);
        garis.addActionListener(this);
        kurva.addActionListener(this);
        persegi.addActionListener(this);
        persegi_panjang.addActionListener(this);
        lingkaran.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            makeDefaultBackgroudButton();
            
            // JIKA DIKLIK MAKA WARNA TOMBOL TOOLS BERUBAH
            
            if(clickedButton == garis_bebas){
                garis_bebas.setBackground(diklik);
                chooser_tools = 0;
            } else if(clickedButton == garis) {
                garis.setBackground(diklik);
                chooser_tools = 1;
            } else if(clickedButton == kurva) {
                kurva.setBackground(diklik);
                chooser_tools = 2;
            } else if(clickedButton == persegi) {
                persegi.setBackground(diklik);
                chooser_tools = 3;
            } else if(clickedButton == persegi_panjang) {
                persegi_panjang.setBackground(diklik);
                chooser_tools = 4;
            } else if(clickedButton == lingkaran) {
                lingkaran.setBackground(diklik);
                chooser_tools = 5;
            }
        }
    }
    
    public void makeDefaultBackgroudButton() {
        
        // MENSET SEMUA WARNA TOMBOL TOOLS KE DEFAULT AWAL
        garis_bebas.setBackground(awal);
        garis.setBackground(awal);
        kurva.setBackground(awal);
        persegi.setBackground(awal);
        persegi_panjang.setBackground(awal);
        lingkaran.setBackground(awal);
    }
    
    public int getChooser_Tools() {
        return chooser_tools;
    }
    
}
