package painting;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.List;
import java.util.Arrays;
import javax.imageio.*;

public class ScreenImage {
    
    // LIST YANG BERISI EXTENSI TYPE GAMBAR YANG VALID
    private static List<String> types = Arrays.asList(ImageIO.getWriterFileSuffixes());

    public static BufferedImage createImage(Rectangle region)
            throws AWTException {
        BufferedImage image = new Robot().createScreenCapture(region);
        return image;
    }

    public static void writeImage(BufferedImage image, String fileName)
            throws IOException {
        if (fileName == null) {
            return;
        }
        
        // MENGECEK APAKAH FILE MEMILIKI EKSTENSI TYPE GAMBAR
        int offset = fileName.lastIndexOf(".");
        if (offset == -1) {
            String message = "file tidak memiliki extensi";
            throw new IOException(message);
        }
        
        // MENGECEK EKSTENSI TYPE GAMBAR YANG ADA PADA FILENAME
        String type = fileName.substring(offset + 1);
        if (types.contains(type)) {
            ImageIO.write(image, type, new File(fileName));
        } else {
            String message = "ekstensi type gambar (" + type + ") tidak diketahui";
            throw new IOException(message);
        }
    }

}
