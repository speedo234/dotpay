package africa.dotpay.dotpay.utility;



public class Util {

    private Util() {
    }

    public static String generateComment(int requestLimit){
        return new StringBuilder()
                .append("exceeded limit of requests which is ")
                .append(requestLimit).toString();
    }


}
