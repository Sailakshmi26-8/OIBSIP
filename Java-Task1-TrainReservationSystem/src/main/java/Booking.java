public class Booking {

    private String pnr;
    private String passengerName;
    private String trainNumber;
    private String trainName;
    private String travelClass;
    private String journeyDate;
    private String source;
    private String destination;

    public Booking(
            String pnr,
            String passengerName,
            String trainNumber,
            String trainName,
            String travelClass,
            String journeyDate,
            String source,
            String destination) {

        this.pnr = pnr;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.travelClass = travelClass;
        this.journeyDate = journeyDate;
        this.source = source;
        this.destination = destination;
    }

    public String getPnr() {
        return pnr;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
