package listbuy.me.listbuy.lista;

/**
 * Created by Talitadossantoscastr on 17/10/2016.
 */

public class Validacoes {
    public static final boolean isNullOrEmpty(String str){
        return str == null || str.length() == 0;
    }
    public static final boolean isNumeric (String str){
        return str.matches("[a-zA-Z]+") || str.length() == 0;
    }
    public static final boolean isCharacterSpecial(String str){
        return str == "?";
    }
}
