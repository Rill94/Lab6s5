package bean;

import dao.DAO;
import model.Message;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Valerie on 09.08.2014.
 */

@ManagedBean
@SessionScoped
public class SessionBean implements Serializable
{
    private List<Message> messageList;
    private int userId;
    @Size(min = 3, max = 20, message = "Too long")
    private String text;

    @ManagedProperty(value = "#{dAOHelperBean}")
    private DAOHelperBean daoHelperBean;


    public String selectUserMessages(int userId)
    {
        this.userId = userId;
        messageList = daoHelperBean.getDao().selectUserMessages(userId);
        return "messages";
    }

    public String addMessage()
    {
        daoHelperBean.getDao().insertMessage(new Message(0, text, userId));
        return selectUserMessages(userId);
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public DAOHelperBean getDaoHelperBean() {
        return daoHelperBean;
    }

    public void setDaoHelperBean(DAOHelperBean daoHelperBean) {
        this.daoHelperBean = daoHelperBean;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }
}
