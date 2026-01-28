package network;

/**
 * Communication protocol for client-server messaging.
 */
public class Protocol {
    public static final String DELIMITER = "|";
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    
    // Commands
    public static final String REQUEST_RIDE = "REQUEST_RIDE";
    public static final String VIEW_PENDING_REQUESTS = "VIEW_PENDING";
    public static final String VIEW_ALL_DRIVERS = "VIEW_DRIVERS";
    public static final String SET_DRIVER_STATUS = "SET_STATUS";
    public static final String VIEW_AVAILABLE_DRIVERS = "VIEW_AVAILABLE";
    public static final String DISPATCH_REQUESTS = "DISPATCH";
    public static final String VIEW_STATISTICS = "VIEW_STATS";
    public static final String ADD_DRIVER = "ADD_DRIVER";
    public static final String DISCONNECT = "DISCONNECT";
    
    public static String buildMessage(String command, String... params) {
        StringBuilder sb = new StringBuilder(command);
        for (String param : params) {
            sb.append(DELIMITER).append(param);
        }
        return sb.toString();
    }
    
    public static String[] parseMessage(String message) {
        if (message == null || message.isEmpty()) {
            return new String[0];
        }
        return message.split("\\" + DELIMITER);
    }
    
    public static String getCommand(String[] parts) {
        return parts.length > 0 ? parts[0] : "";
    }
    
    public static String success(String message) {
        return buildMessage(SUCCESS, message);
    }
    
    public static String error(String message) {
        return buildMessage(ERROR, message);
    }
}
