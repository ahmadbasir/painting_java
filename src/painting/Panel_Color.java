/*
    PANEL UNTUK MENGUBAH WARNA OBJEK YANG DIGAMBAR
    DAN MENGHAPUS SEMUA OBJEK
 */
package painting;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class Panel_Color extends JPanel{
    private JButton bersihkan, colorObjek;
    private JPanel colorObjek_color;
    private Color color_colorObjek;
    private JPanel Canvas_Draw;

    public Panel_Color(JPanel Canvas_Draw) {
        this.Canvas_Draw = Canvas_Draw;
        
        colorObjek = new JButton("Pilih Warna Objek");
        bersihkan = new JButton("Bersihkan");
        colorObjek_color = new JPanel();
        
        color_colorObjek = Color.BLACK;

        GroupLayout layout_colorObjek_color = new GroupLayout(colorObjek_color);
        colorObjek_color.setLayout(layout_colorObjek_color);
        colorObjek_color.setBackground(color_colorObjek);
        layout_colorObjek_color.setHorizontalGroup(
            layout_colorObjek_color.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        layout_colorObjek_color.setVerticalGroup(
            layout_colorObjek_color.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bersihkan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorObjek)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorObjek_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bersihkan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(colorObjek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(colorObjek_color, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        this.setLayout(layout);
        
        // FUNGSI UNTUK MEMBERSIHKAN CANVAS
        bersihkan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Canvas_Draw.setBackground(Color.BLACK);
                Canvas_Draw.setBackground(Color.WHITE);
            }
        });
        
        // FUNGSI UNTUK MEMILIH WARNA OBJEK
        colorObjek.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color old_color_colorObjek = color_colorObjek;
                color_colorObjek = JColorChooser.showDialog(Canvas_Draw, "Pilih Warna", color_colorObjek);
                
                if(color_colorObjek == null)
                    color_colorObjek = old_color_colorObjek;
                
                colorObjek_color.setBackground(color_colorObjek);
            }
        });
    }
    
    public Color getColor_colorObjek() {
        return color_colorObjek;
    }
    
}
