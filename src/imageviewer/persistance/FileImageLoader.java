package imageviewer.persistance;

import imageviewer.model.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class FileImageLoader implements ImageLoader{
    private final File[] files;
    private int index;

    public FileImageLoader(File folder) {
        this.files = folder.listFiles(imageTypes());
        index = 0;
    }
    
    private FileFilter imageTypes(){

        return (File pathname) -> {
            String path = pathname.getName();
            if(path.endsWith(".png")==true || path.endsWith(".jpg")==true){
                return true;
            }else{
                return false;
            }
        };
    }
    
    

    @Override
    public Image load() {
        return new Image(){
            @Override
            public String name(){
                return files[index].getName();     
            }

            @Override
            public InputStream stream() {
                try {
                    return new BufferedInputStream(new FileInputStream(files[index]));
                } catch (FileNotFoundException e) {
                    return null;
                }
            }

            @Override
            public int[] size() {
                try{
                    BufferedImage bfim = ImageIO.read(files[index]);
                    int x = bfim.getWidth();
                    int y = bfim.getHeight();
                    return new int[]{x,y};
                }catch(IOException e){
                    return null;
                }
            }

        };
    }
    
    
                



    @Override
    public Image next() {
        if(index==files.length-1){
            index=0;
        }else{
            index++;
        }
        return load();
    }

    @Override
    public Image prev() {
        if(index==0){
            index=files.length-1;
        }else{
            index--;
        }
        return load();
    };



        
    
}
