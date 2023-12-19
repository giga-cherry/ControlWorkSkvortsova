package org.example.dao;

import org.example.dao.database.PostgresConnectionProvider;
import org.example.maper.RowMapper;
import org.example.model.Exhibition;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExhibitionDao {

    private final static String SQL_FIND_ON_TIME_FROM_PC = "select * from exhibition where start_working_time =< ? and end_working_time >= ? and start_date <= ? and end_date >= ?;";

    private final static String SQL_FIND_ALL = "select * from exhibition;";

    private final static String SQL_FIND_BY_ID = "select * from exhibition where id =?;";

    private final static String SQL_CREATE_PC = "insert into exhibition (start_working_time, end_working_time, start_date, end_date) values (?,?,?,?);";

    private final static String SQL_UPDATE_PC = "update exhibition set start_working_time = ?, end_working_time = ?, start_date = ?, end_date = ? where id = ?;";

    private final static String SQL_DELETE_PC = "delete from exhibition where id = ?;";


    private ExhibitionDao(){}

    public void create(Exhibition exhibition){
        try(Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_PC);
            statement.setTime(1, Time.valueOf(exhibition.getStartWorkingTime()));
            statement.setTime(2, Time.valueOf(exhibition.getEndWorkingTime()));
            statement.setDate(3, Date.valueOf(exhibition.getStartDate()));
            statement.setDate(4, Date.valueOf(exhibition.getEndDate()));
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Exhibition exhibition){
        try(Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PC);
            statement.setTime(1, Time.valueOf(exhibition.getStartWorkingTime()));
            statement.setTime(2, Time.valueOf(exhibition.getEndWorkingTime()));
            statement.setDate(3, Date.valueOf(exhibition.getStartDate()));
            statement.setDate(3, Date.valueOf(exhibition.getEndDate()));
            statement.setLong(5, exhibition.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long pcId){
        try(Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PC);
            statement.setLong(1,pcId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Exhibition> findById(Long pcId){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, pcId);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                return Optional.of(Pc_ROW_MAPPER.mapRow(set));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Exhibition> findAll(){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet set = statement.executeQuery();
            ArrayList<Exhibition> list = new ArrayList<>();
            while (set.next()){
                list.add(Pc_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Exhibition> findOnTime(LocalDateTime from, LocalDateTime to){
        try(Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ON_TIME_FROM_PC);
            statement.setObject(1, from);
            statement.setObject(2, to);
            statement.setObject(3, from.toLocalDate());
            statement.setObject(4, to.toLocalDate());
            ResultSet set = statement.executeQuery();
            ArrayList<Exhibition> list = new ArrayList<>();
            while (set.next()){
                list.add(Pc_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final RowMapper<Exhibition> Pc_ROW_MAPPER = resultSet -> {
        try {
            return new  Exhibition(
                    resultSet.getLong("id"),
                    resultSet.getTime("start_working_time").toLocalTime(),
                    resultSet.getTime("end_working_time").toLocalTime(),
                   resultSet.getDate("start_date").toLocalDate(),
                    resultSet.getDate("end_date").toLocalDate());
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    };

    private static class SingletonHolder{
        public static final ExhibitionDao INSTANCE = new ExhibitionDao();
    }

    public static ExhibitionDao getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
