package bean;

import dao.DAO;
import dao.DAOImpl;
import model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Valerie on 09.08.2014.
 */
@ManagedBean
@RequestScoped
public class RequestBean implements Serializable
{
    private DAO dao;
    private List<User> userList;

    public RequestBean() throws NamingException {
        dao = new DAOImpl();
    }

    public void selectUserList()
    {
        userList = dao.selectAllUsers();
    }

    public List<User> getUserList() {
        selectUserList();
        return userList;
    }

    public DAO getDao() {
        return dao;
    }
}
