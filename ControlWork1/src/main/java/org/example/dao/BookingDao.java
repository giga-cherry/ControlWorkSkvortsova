package org.example.dao;

import org.example.maper.RowMapper;
import org.example.model.Booking;
import org.example.dao.database.PostgresConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDao {

    private final static String SQL_INSERT_INTO_BOOKING = "insert into booking (user_id, exhibition_id, from_time, to_time, type) values (?,?,?,?,?);";

    private final static String SQL_UPDATE_INTO_BOOKING = "update booking set user_id = ?, exhibition_id = ?, from_time = ?, to_time = ?, type = ? where id = ?;";

    private final static String SQL_DELETE_FROM_BOOKING = "delete from booking where id = ?;";

    private final static String SQL_FIND_BY_ID_FROM_BOOKING = "select * from booking where id = ?;";

    private final static String SQL_FIND_BY_USER_ID_FROM_BOOKING = "select * from booking where user_id = ?;";

    private final static String SQL_FIND_BY_EXHIBITION_ID_FROM_BOOKING = "select * from booking where exhibition_id = ?;";

    private final static String SQL_FIND_BY_USER_ID_AND_EXHIBITION_ID_FROM_BOOKING = "select * from booking where user_id = ? and exhibition_id = ?;";

    private final static String SQL_FIND_ALL_FROM_BOOKING = "select * from booking;";

    private BookingDao() {
    }

    public void create(Booking booking) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_BOOKING);
            statement.setLong(1, booking.getUserId());
            statement.setLong(2, booking.getExhibitionId());
            statement.setObject(3, booking.getTimeFrom());
            statement.setObject(4, booking.getTimeTo());
            statement.setLong(5, booking.getType());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Booking> findById(Long bookingId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID_FROM_BOOKING);
            statement.setLong(1, bookingId);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return Optional.of(BOOKING_ROW_MAPPER.mapRow(set));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> findByUserId(Long userId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID_FROM_BOOKING);
            statement.setLong(1, userId);
            ResultSet set = statement.executeQuery();
            ArrayList<Booking> list = new ArrayList<>();
            while (set.next()) {
                list.add(BOOKING_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_FROM_BOOKING);
            ResultSet set = statement.executeQuery();
            ArrayList<Booking> list = new ArrayList<>();
            while (set.next()) {
                list.add(BOOKING_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> findByExhibitionId(Long pcId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EXHIBITION_ID_FROM_BOOKING);
            statement.setLong(1, pcId);
            ResultSet set = statement.executeQuery();
            ArrayList<Booking> list = new ArrayList<>();
            while (set.next()) {
                list.add(BOOKING_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> findByUserIdAndExhibitionId(Long userId, Long pcId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID_AND_EXHIBITION_ID_FROM_BOOKING);
            statement.setLong(1, userId);
            statement.setLong(2, pcId);
            ResultSet set = statement.executeQuery();
            ArrayList<Booking> list = new ArrayList<>();
            while (set.next()) {
                list.add(BOOKING_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long bookingId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FROM_BOOKING);
            statement.setLong(1, bookingId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public void update(Booking booking) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_INTO_BOOKING);
            statement.setLong(1, booking.getUserId());
            statement.setLong(2, booking.getExhibitionId());
            statement.setObject(3, booking.getTimeFrom());
            statement.setObject(4, booking.getTimeTo());
            statement.setLong(5, booking.getType());
            statement.setLong(6, booking.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final RowMapper<Booking> BOOKING_ROW_MAPPER = resultSet -> {
        try {
            return new Booking(
                    resultSet.getLong("id"),
                    resultSet.getLong("user_id"),
                    resultSet.getLong("pc_id"),
                    resultSet.getTimestamp("from_time").toLocalDateTime(),
                    resultSet.getTimestamp("to_time").toLocalDateTime(),
                    resultSet.getLong("type"));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    };


    private static class SingletonHolder {
        public static final BookingDao INSTANCE = new BookingDao();
    }

    public static BookingDao getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
