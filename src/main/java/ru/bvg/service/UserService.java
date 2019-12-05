package ru.bvg.service;


import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.bvg.model.User;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Transactional
    public void importUsers() {
        List<User> users = namedParameterJdbcTemplate.query(
                "select u.id, ud.name, email, password, ud.city, ud.birth_date,  ud.email_subscriber, ud.avatar, ud.spiritual_name from users u join user_data ud on ud.user_id=u.id",
                (resultSet, i) -> {
                    return new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6),
                            resultSet.getBoolean(7),
                            resultSet.getString(8),
                            resultSet.getString(9)
                            );
                });
        Path path = Paths.get("G:/javaProjects/users.sql");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (User user : users) {
                writer.write(String.format("INSERT INTO public.user (email,username,password,birth_date,city,email_subscriber,img_url) " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s', %s, '%s');",
                        user.getEmail(),
                        StringUtils.isEmpty(user.getSpiritual()) ? user.getUsername() : user.getSpiritual(),
                        user.getPassword(),
                        user.getBirthDate() != null ? sdf.format(user.getBirthDate()) : sdf.format(new Date()),
                        user.getCity(),
                        BooleanUtils.toString(user.isEmailSubscriber(), "true", "false"),
                        StringUtils.isEmpty(user.getAvatar()) ? "user/default.jpg" : "user/" + user.getAvatar()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

