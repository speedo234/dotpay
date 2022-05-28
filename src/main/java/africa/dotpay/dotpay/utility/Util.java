package africa.dotpay.dotpay.utility;



public class Util {

    private Util() {
    }

    public static String generateComment(int requestLimit, Long requestNumber){
        return new StringBuilder()
                .append("blocked because request exceeded limit of ")
                .append(requestLimit)
                .append(" with a total request of ")
                .append( requestNumber ).toString();
    }


}
