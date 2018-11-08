/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricjo
 */
public class ChildReservation extends SeatReservation{
    private double childPrice;
    
    public ChildReservation(char row, int col) {
        super(row,col);
        this.childPrice = 8.0;
    }
    
    @Override
    public float getTicketPrice() {
        return (float)this.childPrice;
    }
}
