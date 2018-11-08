/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricjo
 */
    public abstract class SeatReservation {
    private char row;
    private int col;
    protected boolean complementary;
    
    // constructor takes in two fields
    public SeatReservation(char row, int col) {
        this.row = row;
        this.col = col;
    }
    
    // abstract method which is intended to be overriden by its subclasses
    public abstract float getTicketPrice();
    
    // indicates whether a seat is complementary
    public void setComplementary(boolean complementary) {
        if(complementary = true) {
            this.complementary = true;
            System.out.println(this.row+this.col+"is free.");
        } else {
            this.complementary = false;
            System.out.println(this.row+this.col+"is not free.");
        }
    }
    
    // getter method that returns seat row
    public char getRow() {
        return this.row;
    }
    
    // getter method that returns seat column
    public int getCol() {
        return this.col;
    }  
}
