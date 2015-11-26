package tcp;

/**
 * Created by shuhanzhang on 11/26/15.
 */
public class MyTransition {
    public String getNextState(String status,String event) {
        switch (status.toUpperCase()) {
            case "CLOSED":
                if (event.equals( "PASSIVE")) {
                    return "Listen";
                } else if (event.equals( "ACTIVE")) {
                    return "SYN_SENT";
                }
                break;
            case "LISTEN":
                if (event.equals( "CLOSE")) {
                    return "CLOSED";
                } else if (event.equals( "SEND")) {
                    return "SYN_SENT";
                } else if (event.equals( "SYN")) {
                    return "SYN_RCVD";
                }
                break;
            case "SYN_SENT":
                if (event.equals( "CLOSE")) {
                    return "CLOSED";
                } else if (event.equals( "SYNACK")) {
                    return "ESTABLISHED";
                }
                break;
            case "SYN_RCVD":
                if (event.equals( "CLOSE")) {
                    return "FIN_WAIT_1";
                } else if (event.equals( "ACK")) {
                    return "ESTABLISHED";
                }
                break;
            case "ESTABLISHED":
                if (event.equals( "CLOSE")) {
                    return "FIN_WAIT_1";
                } else if (event.equals( "RDATA")) {
                    return "ESTABLISHED";
                } else if (event.equals( "SDATA")) {
                    return "ESTABLISHED";
                } else if (event.equals( "FIN")) {
                    return "";
                }
                break;
            case "":
                if (event.equals( "CLOSE")) {
                    return "LAST_ACK";
                }
                break;
            case "LAST_ACK":
                if (event.equals( "ACK")) {
                    return "CLOSED";
                }
                break;
            case "FIN_WAIT_1":
                if (event.equals( "ACK")) {
                    return "FIN_WAIT_2";
                } else if (event.equals( "FIN")) {
                    return "CLOSING";
                }
                break;
            case "CLOSING":
                if (event.equals( "ACK")) {
                    return "TIME_WAIT";
                }
                break;
            default:
                return "error";
        }
        return "error";
    }
}
