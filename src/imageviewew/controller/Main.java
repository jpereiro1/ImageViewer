
package imageviewew.controller;

import imageviewer.model.Image;
import imageviewer.persistance.FileImageLoader;
import java.io.File;


public class Main {
    
    
    public static void main(String[] args) {
        //OPCIONAL, mandar por pantalla en que carpeta quiere visualizar las fotos
        File folder = new File("C:\\Users\\jpere\\Desktop\\HOLA");
        FileImageLoader imageLoader = new FileImageLoader(folder);
        MainFrame mainFrame = new MainFrame(imageLoader);
        Image image = imageLoader.load();
        mainFrame.getImageDisplay().show(image);
    }
    
}
