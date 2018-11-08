/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricjo
 */
public class Time implements Comparable<Time> {
    private int hours;
    private int mins;
    private int secs;

    // constructor 1
    public Time() {
        this.hours = 0;
        this.mins = 0;
        this.secs = 0;
    }
    
    // contructor 2
    public Time(int hours) {
        this.hours = hours;
        this.mins = 0;
        this.secs = 0;
    }
    
    // constructor 3
    public Time(int hours, int mins) {
        this.hours = hours;
        this.mins = mins;
        this.secs = 0;
    }
    
    // constructor 4
    public Time(int hours, int mins, int secs) {
        this.hours = hours;
        this.mins = mins;
        this.secs = secs;
    }
    
    // compares two instances of time objects. If current Time object is  earlier
    // than the parameter object, a negative number is returned. 
    @Override
    public int compareTo(Time e) {
        int thisTime = (this.hours * 3600) + (this.mins * 60) + this.secs;
        int eTime = (e.hours * 3600) + (e.mins * 60) + e.secs;

        if(thisTime > eTime) {
            return 1;
        } else if (thisTime < eTime) {
            return -1;
        } else {
            return 0;
        }
    }
    
    // setSeconds changes seconds field.
    public void setSeconds(int secs) {
        this.secs = ((secs >= 0 && secs < 60 ? secs : 0));
    }
    
    // setMinutes changes mins field.
    public void setMinutes(int mins) {
        this.mins = ((mins >= 0 && mins < 60 ? mins : 0));
    }
    
    // setHours changes hours field.
    public void setHours(int hours) {
        this.hours = ((hours >= 0 && hours < 24 ? hours : 0));
    }
    
    // getSeconds retrieves secs field.
    public int getSeconds() {
        return this.secs;
    }
    
    // getMinutes retrieves mins field.
    public int getMinutes() {
        return this.mins;
    }
    
    // getHours retrieves hours field.
    public int getHours() {
        return this.hours;
    }
    
    // Overriding equals method checks if both instances of Time are equal.
    // equals method goes through a series of tests for the equals method to work
    @Override
    public boolean equals(Object otherTime) {
         if(this == otherTime) {
             return true;
         }
         if(otherTime == null) {
             return false;
         }
         if(this.getClass() != otherTime.getClass()) {
             return false; 
         }
         Time other = (Time) otherTime; 
         if(this.hours != other.hours) {
             return false;
         }
         if(this.mins != other.mins) {
             return false;
         }
         if(this.secs != other.secs) {
             return false;
         }
         return true;
    }
    
    // toString method
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", this.hours, this.mins, this.secs);
    }
    
}
