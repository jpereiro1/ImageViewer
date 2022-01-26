package imageviewer.controller;

import imageviewer.persistance.ImageLoader;
import imageviewer.ui.ImageDisplay;
import imageviewer.ui.SwingImageDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame{
    
    private ImageDisplay imageDisplay;
    final private ImageLoader imageLoader;
 
    public MainFrame(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
        
        this.setTitle("My Image Viewer");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(imageDisplay(),BorderLayout.CENTER);
        this.getContentPane().add(toolBar(),BorderLayout.SOUTH);
        
        this.setVisible(true);
        
    }

    private JPanel imageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    private Component toolBar() {
        JPanel panel = new JPanel();
        panel.add(prevButton());
        panel.add(nextButton());
        return panel;
    }
    

    private JButton prevButton() {
        //Puedo añadir un icono al boton (OPCIONAL)
        JButton button = new JButton("");
        button.setIcon(new ImageIcon(getClass().getResource("/imagenes/arrow-left.png")));
        configButton(button);
        button.addActionListener(prevImage());
        return button;
    }

    private JButton nextButton() {
        JButton button = new JButton("");
        button.setIcon(new ImageIcon(getClass().getResource("/imagenes/arrow-right.png")));
        configButton(button);
        button.addActionListener(nextImage());
        return button;
    }

    private ActionListener prevImage() {
        return (ActionEvent e) -> {
            imageDisplay.show(imageLoader.prev());
            setTamano();
            
        };
    }

    private ActionListener nextImage() {
        return (ActionEvent e) -> {
            imageDisplay.show(imageLoader.next());
            setTamano();
            
        };
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private void configButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }
    
    private void setTamano(){
        int[] size = imageDisplay.getTamano();
        size = reduceSize(size);
        System.out.println(size[0]+ " y " +  size[1]);
        this.setSize(size[0]+0, size[1]+80);
    }
    
    private int[] reduceSize(int[] size){
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
