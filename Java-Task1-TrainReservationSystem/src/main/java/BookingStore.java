import java.util.HashMap;
import java.util.Map;

public class BookingStore {

    private static final Map<String, Booking> bookings = new HashMap<>();

    public static void addBooking(Booking booking) {
        bookings.put(booking.getPnr(), booking);
    }

    public static Booking getBooking(String pnr) {
        return bookings.get(pnr);
    }

    public static void removeBooking(String pnr) {
        bookings.remove(pnr);
    }
}

