/*
    ERROR MENYIMPAN KE DATABASE JIKA FILE ADA SPASI >= 2
*/


package painting;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.QuadCurve2D;
import java.awt.image.*;
import java.io.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Painting extends JFrame{
    private String nama;
    private Container contentPane;
    private JPanel Canvas_Draw;
    private Panel_Tools tools;
    private Panel_Color color;
    private int last_x, last_y, npoint;
    private int[] pointX, pointY;

    public Painting(String name){
        nama = name;
        
        this.setTitle("Selamat Datang " + name + " di Painting Objek");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        last_x = last_y = npoint = 0;
        pointX = new int[3]; 
        pointY = new int[3];
        
        contentPane = getContentPane();
        Canvas_Draw = new JPanel();
        
        tools = new Panel_Tools();
        tools.setBorder(BorderFactory.createLoweredBevelBorder());
        
        color = new Panel_Color(Canvas_Draw);
        color.setBorder(BorderFactory.createLoweredBevelBorder());

        GroupLayout layout_canvas = new GroupLayout(Canvas_Draw);
        Canvas_Draw.setLayout(layout_canvas);
        layout_canvas.setHorizontalGroup(
            layout_canvas.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout_canvas.setVerticalGroup(
            layout_canvas.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        Canvas_Draw.setBackground(Color.WHITE);
        
        JButton save = new JButton("SIMPAN GAMBAR");
        JPanel color_panel = new JPanel(new BorderLayout());
        color_panel.add(color, BorderLayout.WEST);
        color_panel.add(save, BorderLayout.EAST);
        
        contentPane.setLayout(new BorderLayout());
        contentPane.add(Canvas_Draw, BorderLayout.NORTH);
        contentPane.add(tools, BorderLayout.CENTER);
        contentPane.add(color_panel, BorderLayout.SOUTH);
        
        Canvas_Draw.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX(), y = e.getY();
                Graphics g = Canvas_Draw.getGraphics();
                g.setColor(color.getColor_colorObjek());

                switch (tools.getChooser_Tools()) {
                    case 2:
                        Graphics2D g2 = (Graphics2D) g;
                        pointX[npoint] = x;
                        pointY[npoint] = y;
                        npoint++;
                        if (npoint == 3) {
                            QuadCurve2D.Double curve = new QuadCurve2D.Double(
                                    pointX[0], pointY[0],
                                    pointX[1], pointY[1],
                                    pointX[2], pointY[2]);
                            g2.draw(curve);
                            npoint = 0;
                        }
                        break;
                    case 3:
                        g.fillRect(x - 25, y - 25, 50, 50);
                        g.dispose();
                        break;
                    case 4:
                        g.fillRect(x - 50, y - 25, 100, 50);
                        g.dispose();
                        break;
                    case 5:
                        g.fillOval(x - 50, y - 50, 100, 100);
                        break;
                    default:
                        g.drawLine(x, y, x, y);
                        break;
                }

                g.dispose();
            }

            public void mousePressed(MouseEvent e) {
                int x = e.getX(), y = e.getY();
                Graphics g = Canvas_Draw.getGraphics();
                g.setColor(color.getColor_colorObjek());

                if (tools.getChooser_Tools() == 0 || tools.getChooser_Tools() == 1) {
                    g.drawLine(x, y, x, y);
                }

                g.dispose();

                last_x = x;
                last_y = y;
            }

            public void mouseReleased(MouseEvent e) {
                int x = e.getX(), y = e.getY();
                Graphics g = Canvas_Draw.getGraphics();
                g.setColor(color.getColor_colorObjek());

                if (tools.getChooser_Tools() == 1) {
                    g.drawLine(last_x, last_y, x, y);
                }
                g.dispose();
            }

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}
        });
        
        Canvas_Draw.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getX(), y = e.getY();

                if (tools.getChooser_Tools() == 0) {
                    Graphics g = Canvas_Draw.getGraphics();
                    g.setColor(color.getColor_colorObjek());
                    g.drawLine(last_x, last_y, x, y);
                    g.dispose();
                    last_x = x;
                    last_y = y;
                }
            }
            public void mouseMoved(MouseEvent e) {}
        });
        
        // FUNGSI MENYIMPAN GAMBAR
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // SETTING PANEL KE GAMBAR
                    Point p = rootPane.getLocationOnScreen();
                    Dimension d = new Dimension(Canvas_Draw.getWidth(), Canvas_Draw.getHeight());
                    Rectangle r = new Rectangle(p, d);
                    BufferedImage BI = ScreenImage.createImage(r);

                    // DIALOG BOX UNTUK TEMPAT SIMPAN FILE
                    File file;
                    JFileChooser chooser = new JFileChooser();
                    chooser.setFileFilter(new FileNameExtensionFilter("bmp file (.bmp)", "bmp"));
                    if (chooser.showSaveDialog(Canvas_Draw) == JFileChooser.APPROVE_OPTION) {
                        
                        // MENGAMBIL NAMA FILE PADA DIALOG
                        file = chooser.getSelectedFile();
                        String filename = nama + "_" + file.getName();
                        
                        // MENGUBAH SPASI MENJADI '-' JIKA ADA
                        if(filename.contains(" ")){
                            String[] tmp = filename.split(" ");
                            filename = "";
                            for(String a:tmp){
                                if(filename.isEmpty()){
                                    filename = a;
                                } else {
                                    filename = filename + "-" + a;
                                }
                            }
                        }
                        
                        // MENAMBAHKAN .bmp JIKA BELUM ADA
                        if(!filename.endsWith(".bmp"))
                            filename += ".bmp";
                                
                        String fullPathFile = chooser.getCurrentDirectory().toString()
                                + "/" + filename;
                        
                        // OUTPUT GAMBAR FILE
                        ScreenImage.writeImage(BI, fullPathFile);
                        
                        //SIMPAN KE DATABASE MYSQL
                        boolean saveDb = true;
                        simpanDB db = new simpanDB();
                        while(saveDb) {
                            try {
                                db.tambah(nama, filename, file.getName(), fullPathFile);
                                saveDb = false;
                            } catch (SQLException se) {
                                db.buatTabel();
                            }
                        }
                        db.closeAll();
                        
                        // NOTIFIKASI JIKA BERHASIL DISIMPAN
                        StringBuffer notif = new StringBuffer("BERHASIL DISIMPAN\n");
                        notif.append("NAMA FILE: " + filename + "\n");
                        notif.append("LOCATION : " + fullPathFile + "\n");
                        JOptionPane.showMessageDialog(Canvas_Draw, notif);
                    } else {
                        // NOTIFIKASI JIKA GAGAL DISIMPAN
                        JOptionPane.showMessageDialog(Canvas_Draw, "SIMPAN GAMBAR DIBATALKAN");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
}
