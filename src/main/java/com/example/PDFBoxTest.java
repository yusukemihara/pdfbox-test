package com.example;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PDFBoxTest extends JPanel {

    private PDDocument document;
    private PDFRenderer renderer;
    
    public PDFBoxTest() {
        document = null;
        renderer = null;
    }

    public void load(String file) {
        try {
            document = PDDocument.load(new File(file));
            renderer = new PDFRenderer(document);
        } catch (IOException ex) {
            Logger.getLogger(PDFBoxTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paint(Graphics g) {
        try {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(renderer.renderImage(0,5.0f),0,0,this);
        } catch (IOException ex) {
            Logger.getLogger(PDFBoxTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        PDFBoxTest box = new PDFBoxTest();
        box.load(args[0]);
        f.add(box);
        f.setVisible(true);
        f.pack();
        f.setSize(200, 200);
        f.validate();
    }
}
