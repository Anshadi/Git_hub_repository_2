package com.asthana.JDBCDemo.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.asthana.JDBCDemo.model.Alien;
import java.util.List;


@Repository
public class AlienRepo {

    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public void save(Alien alien) {
        // Save to db
        String sql = "Insert into alien (id , name , tech) values(?,?,?)";

        int rows = template.update(sql, alien.getId(), alien.getName(), alien.getTech());
        System.out.println(rows + " rows Affected");
    }

    public List<Alien> findAll() {
        String sql = "Select * from alien";

        List<Alien> aliens = template.query(sql, ( rs, i) -> {
            Alien a = new Alien();

            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setTech(rs.getString("tech"));
            return a;
    }
    );

        return aliens;
    }

    public JdbcTemplate getTemplate() {
        return template;
    }


}
