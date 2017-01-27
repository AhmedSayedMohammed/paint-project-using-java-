package paint;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.*;
import static javax.swing.KeyStroke.getKeyStroke;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicMenuUI;

public class swingpaint extends JFrame {

    private JButton b_colorchoser = new JButton("Choose Color");
    private JButton b_clear = new JButton("Clear");
    private JButton b_bigline = new JButton("Circl Line");
    private JButton b_rectangleline = new JButton("Rectangle Line");
    private JButton b_delet = new JButton("Delete");
    private JButton b_shapes = new JButton("Shapes");
    private JButton b_size = new JButton("Size");
    private JButton b_default = new JButton("Default");
    JFileChooser jFileChooser = new JFileChooser();
    JDialog d_jFileChooser = new JDialog();

    //menue bar
   static JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenu jMenu_file_open = new JMenu("Open");
    JMenuItem jMenuItem_increase = new JMenuItem();
    JMenuItem jMenuItem_decrease = new JMenuItem();
    JMenuItem jMenuItem_file_open = new JMenuItem();
    JMenuItem jMenuItem_file_save = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    // JToolBar jToolBar1 = new JToolBar();

    JTextField t_size = new JTextField();
    //dialoge shapes buttons
    private JButton b_circlpass = new JButton("circl pass");
    private JButton b_shapes_shade = new JButton("Shade");
    private JButton b_rectanglepass = new JButton("rectangle pass");
    JColorChooser jColorChooser = new JColorChooser();
    JDialog d_colorchooser = new JDialog();
    JDialog d_shapes = new JDialog();
    JDialog d_size = new JDialog();
   static Paint p;
    // panels
    JPanel panel_image = new JPanel();
    JPanel panel_down_slider = new JPanel();
    JPanel controls = new JPanel();
   // private JSlider slider_image = new JSlider(200, 3000, 800);
   JScrollPane scroll_image;
   // JFrame frame = new JFrame("Swing paint");
    public swingpaint() {
             p = new Paint();
            
           //  setPreferredSize(new Dimension(2000,2000));
             scroll_image = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
             this.add(scroll_image.add(p));
             //events 
        //Slider Event
    /*    slider_image.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int x = slider_image.getValue();
                p.setImage_sizeX(x);
                p.setImage_sizeY(x);
                repaint();
               p.updateUI();
           
              
            }
        });
*/
        jColorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Color c = jColorChooser.getColor();
                p.setGraphicsColor(c.getRed(), c.getGreen(), c.getBlue());

            }
        });

        b_colorchoser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                d_colorchooser.add(jColorChooser);
                d_colorchooser.pack();
                d_colorchooser.setVisible(true);

            }
        });
        b_shapes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                d_shapes.add(b_rectanglepass, BorderLayout.NORTH);
                d_shapes.add(b_circlpass, BorderLayout.WEST);
                d_shapes.add(b_shapes_shade, BorderLayout.CENTER);
                d_shapes.setSize(200, 200);
                d_shapes.setVisible(true);
            }
        });
        b_default.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                p.g3.setColor(Color.WHITE);
                p.setChoose(0);
                // Graphics g=null;

                //   p.paint(g);
            }
        });

        b_size.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                d_size.add(t_size);
                d_size.pack();
                d_size.setVisible(true);
                t_size.setText("" + p.getSizee());

            }
        });
        b_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.clear();
                p.choice = true;
                p.choice2 = true;
                p.drawimage = 0;
            }
        });

        b_bigline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                p.setChoose(1);

            }
        });
        b_rectangleline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                p.setChoose(2);

            }
        });
        b_delet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.g3.setColor(Color.WHITE);
                p.setChoose(3);
            }
        });
        b_rectanglepass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                p.setChoose(4);

            }
        });
        b_circlpass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                p.setChoose(5);

            }
        });
        b_shapes_shade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                p.setChoose(6);

            }
        });

        //menue bar events   
        // jToolBar1.setRollover(true);
        //  jToolBar1.setBounds(0, 0, 980, 20);
        jMenu1.setText("Size");
        jMenuItem_increase.setText("increase size");
        jMenuItem_decrease.setText("decrease size");
        jMenuItem_file_open.setText("Open");
        jMenuItem_file_save.setText("Save");
        jMenuItem_increase.setAccelerator(getKeyStroke(KeyEvent.VK_ADD, InputEvent.CTRL_MASK));
        jMenuItem_decrease.setAccelerator(getKeyStroke(KeyEvent.VK_SUBTRACT, InputEvent.CTRL_MASK));
        jMenuItem_file_open.setAccelerator(getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        jMenuItem_file_save.setAccelerator(getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        jMenuItem_file_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p.drawimage == 1) {
                    try {

                        File f = jFileChooser.getSelectedFile();
                        boolean correct = ImageIO.write(p.a, "jpg", f);
                        if (correct) {
                            JOptionPane.showMessageDialog(null, "Saved in : " + f.getAbsolutePath());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } else {
                    try {

                        JFileChooser f_chooser = new JFileChooser();
                        f_chooser.showSaveDialog(null);

                        File f = f_chooser.getSelectedFile();
                        JOptionPane.showMessageDialog(null, "Saved in : " + f.getCanonicalPath());
                        boolean correct = ImageIO.write(p.image, "jpg", f);
                        if (correct) {
                            JOptionPane.showMessageDialog(null, "Saved in : " + f.getAbsolutePath());
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, e);
                        System.out.println(e);
                    }
                }

            }
        });

        jMenuItem_increase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = p.getSizee();
                x += 1;
                p.setSizee(x);

            }
        });

        jMenuItem_decrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = p.getSizee();
                x -= 1;
                p.setSizee(x);

            }
        });

        jMenuItem_file_open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                d_jFileChooser.setVisible(true);
                d_jFileChooser.add(jFileChooser);
                d_jFileChooser.pack();

            }
        });
        jFileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = jFileChooser.getSelectedFile();

                String pass = f.getAbsolutePath();
                try {
                    p.a = ImageIO.read(f);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, pass);
                }
                p.choice2 = false;
                p.choice = false;
                p.drawimage = 1;
                d_jFileChooser.dispose();
            }
        });

        jMenu1.add(jMenuItem_increase);
        jMenu1.add(jMenuItem_decrease);
        jMenu2.setText("File");
        jMenu2.add(jMenuItem_file_open);
        jMenu2.add(jMenuItem_file_save);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu1);

        controls.add(b_colorchoser);
        controls.add(b_clear);
        controls.add(b_bigline);
        controls.add(b_rectangleline);
        controls.add(b_delet);
        controls.add(b_shapes);
        controls.add(b_size);
        controls.add(b_default);

        controls.setBackground(Color.red);
        this.add(controls, BorderLayout.NORTH);
    //  panel_down_slider.add(slider_image, BorderLayout.CENTER);
        panel_down_slider.setBackground(Color.red);
        this.add(panel_down_slider, BorderLayout.SOUTH);
   

    }

    public static void main(String[] args) {
     JFrame frame =   new swingpaint();
       frame.setUndecorated(true); //remove title
     //  frame.setAlwaysOnTop(true); 
        frame.setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int xSize=(int) toolkit.getScreenSize().getWidth();
        int ySize=(int) toolkit.getScreenSize().getHeight();
        frame.setSize(xSize, ySize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setJMenuBar(jMenuBar1);
        frame.setResizable(true);
       
    }
}
