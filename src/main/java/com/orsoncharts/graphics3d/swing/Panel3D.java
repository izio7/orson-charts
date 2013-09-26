/**
 * (C)opyright 2013, by Object Refinery Limited
 */
package com.orsoncharts.graphics3d.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import com.orsoncharts.ArgChecks;
import com.orsoncharts.graphics3d.Drawable3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import java.awt.Rectangle;

/**
 * A panel that displays a set of 3D objects from some viewing point.
 */
public class Panel3D extends JPanel implements ActionListener, MouseListener, 
        MouseMotionListener, MouseWheelListener {
  
    /**
     * The object that is displayed in the panel.
     */
    private Drawable3D drawable;

    /** 
     * The (screen) point of the last mouse click (will be null initially).  
     * Used to calculate the mouse drag distance and direction.
     */
    private Point lastClickPoint;

    private ViewPoint3D lastViewPoint;

    /**
     * Creates a new panel with the specified {@link Drawable3D} to
     * display.
     *
     * @param drawable  the content to display (<code>null</code> not permitted).
     */
    public Panel3D(Drawable3D drawable) {
        super(new BorderLayout());
        ArgChecks.nullNotPermitted(drawable, "drawable");
        this.drawable = drawable;
//        this.drawable.setViewPoint(new ViewPoint3D((float) (3 * Math.PI / 2.0), 
//                (float) Math.PI / 2, 40.0f));
//        this.viewPoint = new ViewPoint3D((float) (3 * Math.PI / 2.0), 
//                (float) Math.PI / 6, 40.0f);
        this.lastViewPoint = this.drawable.getViewPoint();
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    public Drawable3D getDrawable() {
        return this.drawable;
    }

    /**
     * Returns the current world viewpoint.
     *
     * @return  The view point.
     */
    public ViewPoint3D getViewPoint() {
        return this.drawable.getViewPoint();  // TODO : remove this method and
        // replace by getDrawable().getViewPoint();  Or should we leave it in?
    }

    /**
     * Sets a new view point and repaints the panel.
     *
     * @param vp  the view point (<code>null</code> not permitted).
     */
    public void setViewPoint(ViewPoint3D vp) {
        ArgChecks.nullNotPermitted(vp, "vp");
        this.drawable.setViewPoint(vp);  // 
        repaint();
    }

    /**
     * Paints a 2D projection of the objects.
     *
     * @param g  the graphics target (assumed to be an instance of
     *           <code>Graphics2D</code>).
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Dimension size = getSize();
        Rectangle drawArea = new Rectangle(size.width, size.height);
        this.drawable.draw(g2, drawArea);
    }
  
    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // nothing to do
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // nothing to do
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // nothing to do
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
        this.lastClickPoint = e.getPoint();
        this.lastViewPoint = this.drawable.getViewPoint();
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        Point currPt = e.getPoint();
        int dx = currPt.x - this.lastClickPoint.x;
        int dy = currPt.y - this.lastClickPoint.y;

        float valTheta = this.lastViewPoint.getTheta() + (float) (dx * Math.PI / 100);
        float valRho = this.lastViewPoint.getRho();
        float valPhi = this.lastViewPoint.getPhi() + (float) (dy * Math.PI / 100);
        setViewPoint(new ViewPoint3D(valTheta, valPhi, valRho));
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        // nothing to do
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        float units = mwe.getUnitsToScroll();
        float valRho = Math.max(10.0f, this.drawable.getViewPoint().getRho() + units);
        float valTheta = this.drawable.getViewPoint().getTheta();
        float valPhi = this.drawable.getViewPoint().getPhi();
        setViewPoint(new ViewPoint3D(valTheta, valPhi, valRho));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
    }
    
    private static Font FONT_AWESOME;
    
    public static Font getFontAwesomeFont(int size) {
        InputStream in = Panel3D.class.getResourceAsStream("fontawesome-webfont.ttf");
        if (FONT_AWESOME == null) {
            try {
                FONT_AWESOME = Font.createFont(Font.TRUETYPE_FONT, in);
            } catch (FontFormatException ex) {
                Logger.getLogger(Panel3D.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Panel3D.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return FONT_AWESOME.deriveFont(Font.PLAIN, size);
    }

}
