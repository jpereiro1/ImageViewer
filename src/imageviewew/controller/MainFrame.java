package imageviewew.controller;

import imageviewer.persistance.ImageLoader;
import imageviewer.ui.ImageDisplay;
import imageviewer.ui.SwingImageDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.getContentPane().add(imageDisplay());
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
        JButton button = new JButton("<");
        button.addActionListener(prevImage());
        return button;
    }

    private JButton nextButton() {
        JButton button = new JButton(">");
        button.addActionListener(nextImage());
        return button;
    }

    private ActionListener prevImage() {
        return (ActionEvent e) -> {
            imageDisplay.show(imageLoader.next());
        };
    }

    private ActionListener nextImage() {
        return (ActionEvent e) -> {
            imageDisplay.show(imageLoader.prev());
        };
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }
    
    
    
    
}
