package listbuy.me.listbuy.entities;

import java.sql.Date;

/**
 * Created by mgovea on 16/11/2016.
 */

public class ProdutoCadastrado {
    private Integer ID_CONSUMIDOR;
    private Produtos PRODUTO;
    private Date DATA_ICS;

    public Integer getID_CONSUMIDOR() {
        return ID_CONSUMIDOR;
    }

    public void setID_CONSUMIDOR(Integer ID_CONSUMIDOR) {
        this.ID_CONSUMIDOR = ID_CONSUMIDOR;
    }

    public Produtos getPRODUTO() {
        return PRODUTO;
    }

    public void setPRODUTO(Produtos PRODUTO) {
        this.PRODUTO = PRODUTO;
    }

    public Date getDATA_ICS() {
        return DATA_ICS;
    }

    public void setDATA_ICS(Date DATA_ICS) {
        this.DATA_ICS = DATA_ICS;
    }
}
