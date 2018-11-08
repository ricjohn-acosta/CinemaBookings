/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricjo
 */
public class AdultReservation extends SeatReservation{
    public double adultPrice;
    
    public AdultReservation(char row, int col) {
        // super will invoke Parent class constructor
        // super(row,col) will be equivalent to:
        // this.row = row;
        // this.col = col;
        super(row,col);
        this.adultPrice = 12.50;
    }
    
    @Override
    public float getTicketPrice() {
        return (float)this.adultPrice;
    }
}
