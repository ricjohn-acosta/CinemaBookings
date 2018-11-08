/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricjo
 */
public class ElderlyReservation extends AdultReservation{
    private double elderlyPrice;
    
    public ElderlyReservation(char row, int col) {
        super(row,col);
        if(complementary) {
            elderlyPrice = 0;
        } else {
            double percentOff = adultPrice * 0.3;
            elderlyPrice = adultPrice - percentOff;
        }
    }
    
    @Override
    public float getTicketPrice() {
        return (float)this.elderlyPrice;
    }
}
