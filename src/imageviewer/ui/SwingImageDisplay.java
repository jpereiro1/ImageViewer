
package imageviewer.ui;

import imageviewer.model.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class SwingImageDisplay extends JPanel implements ImageDisplay {
    
    private Image currentImage;
    private int x;
    private int y;
    
    @Override
    public Image current() {
        return currentImage;
    }

    @Override
    public void show(Image image) {
        this.currentImage = image;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        if(currentImage != null){
            super.paint(g);
            int[] size = getTamano();
            size = reduceSize(size);
            g.drawImage(imageOf(currentImage),0,0,size[0],size[1],null);
        }
            
    }
        

    private BufferedImage imageOf(Image image) {
        try {
            return ImageIO.read(image.stream());
        } catch (IOException ex) {
            return null;
        }
    }
    
    @Override
    public int[] getTamano(){
        return currentImage.size();
    }

    private int[] reduceSize(int[] size) {
        if(size[0]>1000 || size[1] >1000){
                size[0] = size[0]/2;
                size[1] = size[1]/2;
        }
        
        if(size[0]>1500 || size[1] >1500){
                size[0] = size[0]/4;
                size[1] = size[1]/4;
        }
        
        return size;
    }
    
    

    
}
