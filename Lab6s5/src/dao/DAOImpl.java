package dao;

import connection.ConnectionGlassFish;
import model.Message;
import model.User;


import javax.naming.NamingException;
import javax.sound.sampled.DataLine;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valerie on 09.08.2014.
 */
public class DAOImpl implements DAO
{
    Connection connection;
    ConnectionGlassFish connectionGlassFish;
    DataSource dataSource;

    public DAOImpl() throws NamingException {
        connectionGlassFish = new ConnectionGlassFish();
        dataSource = connectionGlassFish.createConnection();

    }


    @Override
    public List<User> selectAllUsers()
    {
        List<User> userList = new ArrayList<User>();
        PreparedStatement statement;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM User");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int userId = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                User user = new User (userId, login, password);
                userList.add(user);
            }
            statement.close();
            connection.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<Message> selectUserMessages(int userId)
    {
        List<Message> messageList = new ArrayList<Message>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement;
            statement = connection.prepareStatement("SELECT * FROM Message where user_idUser = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int messageId = resultSet.getInt(1);
                String text = resultSet.getString(2);
                int user_userId = resultSet.getInt(3);
                messageList.add(new Message(messageId, text, user_userId));
            }

            statement.close();
            connection.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return messageList;
    }

    public boolean insertMessage(Message message)
    {
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO Message(Text, user_idUser) VALUES (?,?)");
            preparedStatement.setString(1,message.getText());
            preparedStatement.setInt(2,message.getUser_UserId());
            int count = preparedStatement.executeUpdate();
            if (count>0)
                return true;
            else return false;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }

    }


}
