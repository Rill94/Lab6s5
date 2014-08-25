package bean;

import dao.DAO;
import dao.DAOImpl;
import model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Valerie on 09.08.2014.
 */
@ManagedBean
@SessionScoped
public class DAOHelperBean implements Serializable
{
    private DAO dao;

    public DAOHelperBean() throws NamingException {
        dao = new DAOImpl();
    }

    public DAO getDao() {
        return dao;
    }
}
