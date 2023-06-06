/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phamn
 */
public class Booking {

    public String id, destination_id, booking_date, journey_date, train_id, seat_numbers, passenger_id, number_of_seat, payment_status, status, note;

    public Booking(String id, String destination_id, String booking_date, String journey_date, String train_id, String seat_numbers, String passenger_id, String number_of_seat, String payment_status, String status, String note) {
        this.id = id;
        this.destination_id = destination_id;
        this.booking_date = booking_date;
        this.journey_date = journey_date;
        this.train_id = train_id;
        this.seat_numbers = seat_numbers;
        this.passenger_id = passenger_id;
        this.number_of_seat = number_of_seat;
        this.payment_status = payment_status;
        this.status = status;
        this.note = note;
    }

    public Booking() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getJourney_date() {
        return journey_date;
    }

    public void setJourney_date(String journey_date) {
        this.journey_date = journey_date;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getSeat_numbers() {
        return seat_numbers;
    }

    public void setSeat_numbers(String seat_numbers) {
        this.seat_numbers = seat_numbers;
    }

    public String getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(String passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getNumber_of_seat() {
        return number_of_seat;
    }

    public void setNumber_of_seat(String number_of_seat) {
        this.number_of_seat = number_of_seat;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    

}
