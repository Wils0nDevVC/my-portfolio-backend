package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SkillRepositoryImpl implements ISkillRepository{

    private final JdbcTemplate jdbcTemplate;

    //RowMapper : Interface funcional que tiene un solo metodo abtracto
    private final RowMapper<Skill> skillRowMapperInfoRowMapper = (rs, numRow)->{
        Skill skill = new Skill();
        skill.setId(rs.getLong("id"));
        skill.setName(rs.getString("name"));
        skill.setLevelPercentage(rs.getObject("level_percentage", Integer.class));
        skill.setIconClass(rs.getString("icon_class"));
        skill.setPersonalInfoId(rs.getLong("personal_info_id"));
        return skill;
    };


    @Override
    public Skill save(Skill skill) {
        if(skill.getId() ==  null ){
            String sql = "INSERT INTO skills  (name, level_percentage, icon_class, personal_info_id) " +
                    "VALUES (?,?,?,?)";

            //Ayuda a recueperar de manera rapida el dato insertado
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update( connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String []{"id"});
                ps.setString(1, skill.getName());
                ps.setInt(2, skill.getLevelPercentage());
                ps.setString(3, skill.getIconClass());
                ps.setLong(4, skill.getPersonalInfoId());
                return ps;
            }, keyHolder);
            skill.setId(Objects.requireNonNull(keyHolder.getKey().longValue()));
        }else {
            String sql = "UPDATE skills SET name=?, level_percentage=?, icon_class=?, personal_info_id=? WHERE id=?";
            jdbcTemplate.update(sql,
                    skill.getName(),
                    skill.getLevelPercentage(),
                    skill.getIconClass(),
                    skill.getPersonalInfoId()
            );
        }
        return skill;
    }

    @Override
    public Optional<Skill> findById(Long id) {
        String sql = "SELECT * FROM skills WHERE id = ? ";
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, skillRowMapperInfoRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Skill> findAll() {
        String sql = "SELECT * FROM skills ";
        return jdbcTemplate.query(sql,skillRowMapperInfoRowMapper);
    }

    @Override
    public void deletById(Long id) {
        String sql = "DELETE FROM skills WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public List<Skill> findSkillsByPersonalInfoId(Long personalInfoId) {
        String sql = "SELECT name, level_percentage, icon_class, personal_info_id FROM skills WHERE personal_info_id = ? ";
         return   jdbcTemplate.query(sql, skillRowMapperInfoRowMapper, personalInfoId);
    }
}
