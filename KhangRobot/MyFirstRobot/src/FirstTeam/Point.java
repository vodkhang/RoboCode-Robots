/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FirstTeam;

import java.io.Serializable;

/**
 *
 * @author Khang Vo
 */
public class Point implements Serializable{
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
}
