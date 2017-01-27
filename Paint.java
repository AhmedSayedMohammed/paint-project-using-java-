
package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Paint extends JPanel{
private int currentx,currenty,oldx,oldy;
private String pass;
int  drawimage=0;
Toolkit toolkit = Toolkit.getDefaultToolkit();
        int xSize=(int) toolkit.getScreenSize().getWidth();
        int ySize=(int) toolkit.getScreenSize().getHeight();
private int Image_sizeX=xSize;
private int Image_sizeY=ySize;
 
private int choose=0;
private int tool_size=20;
 BufferedImage image;
 BufferedImage a ;
 
 Graphics2D g2,g3;
 boolean choice=true;
 boolean choice2=true;
 JLabel label_image;
public Paint()
{
   
        a =new BufferedImage(Image_sizeX, Image_sizeY, BufferedImage.TYPE_INT_ARGB);
        image =new BufferedImage(Image_sizeX, Image_sizeY, BufferedImage.TYPE_INT_ARGB);
        // label_image=new JLabel(new ImageIcon(image.getScaledInstance(Image_sizeX, Image_sizeY, Image.SCALE_SMOOTH)));
       // label_image.setSize(new Dimension(Image_sizeX,Image_sizeY));
     setDoubleBuffered(false);
     addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e); 
           repaint();
           updateUI();
            oldx=e.getX();
            oldy=e.getY();
            System.out.println("mouse pressed");
        }

      
             
});
   
    addMouseMotionListener(new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.
            currentx=e.getX();
            currenty=e.getY();
         
           if(choose==1)
            {      g2.fillOval(currentx, currenty, tool_size, tool_size);}
             else if(choose==2){
                   g2.fillRect(oldx, oldy, tool_size, tool_size);
                                 
             }
              else if(choose==3){
                  g3.fillRect(oldx, oldy, 40, 40);
             }
               else if(choose==4){
                  g2.drawRect(oldx, oldy, tool_size, tool_size);
                  
             }
               
                else if(choose==5){
                  g2.drawOval(oldx, oldy, tool_size, tool_size);
             }
                 else if(choose==6){
                   g3.setColor(new Color(40, 40, 40));
                   g3.fillRect(oldx, oldy, tool_size, tool_size);
                   g2.fillRect(oldx+10, oldy-10, tool_size+20, tool_size+10);
                  
                  
             }
           else
              { g2.drawLine(oldx, oldy, currentx, currenty);
              }
             repaint();
             oldx=currentx;
             oldy=currenty;
        }
  
        
});
}


 @Override
protected void paintComponent(Graphics g) {
     
      
      //  g.drawImage(image, 0, 0, this);
      if(drawimage==1)
      {
          if(choice==false){
        g2=(Graphics2D) a.getGraphics();
        g3=(Graphics2D) a.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
        repaint();
        choice=true;
          }
          g.drawImage(a, 0,0, Image_sizeX,Image_sizeY, this);
          
      }
      else{
       if(choice2==true){
        image=(BufferedImage) createImage(Image_sizeX,Image_sizeY);
        g2=(Graphics2D) image.getGraphics();
        g3=(Graphics2D) image.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
     
        clear();
        choice2=false;
       }
             g.drawImage(image, 0,0 ,  Image_sizeX,Image_sizeY, this);
      }
    }

    public void clear() {
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.BLACK);
        repaint();
       
       
       
    }

    
           public void setGraphicsColor( int r,int g,int b)
           {
               try{
               g2.setPaint(new Color(r, g, b));
               
               }
               catch(Exception ex)
               {
                   System.out.println(ex);
               }
           }
           
   public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    } 
   
  public int getSizee() {
        return tool_size;
    }

    public void setSizee(int size) {
        this.tool_size = size;
    }
 
    
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
  public int getImage_sizeX() {
        return Image_sizeX;
    }

    public void setImage_sizeX(int Image_sizeX) {
        this.Image_sizeX = Image_sizeX;
    }

    public int getImage_sizeY() {
        return Image_sizeY;
    }

    public void setImage_sizeY(int Image_sizeY) {
        this.Image_sizeY = Image_sizeY;
    }
}