package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by Valerie on 09.08.2014.
 */
@ManagedBean
@SessionScoped
public class AjaxSessionBean implements Serializable
{
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String makeMeSandwich()
    {
        return "Sometext: " + text;
    }
}
