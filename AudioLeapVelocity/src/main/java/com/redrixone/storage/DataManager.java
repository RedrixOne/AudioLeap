package com.redrixone.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class DataManager {
    private static Connection connection = null;

    public DataManager(Connection connection) {
        this.connection = connection;
    }

    public static void saveCoordinates(UUID uuid, int x, int y, int z, boolean request) {
        String query = "INSERT INTO audioleap (uuid, x, y, z) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setInt(2, x);
            statement.setInt(3, y);
            statement.setInt(4, z);
            statement.setBoolean(5, request);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getX(UUID uuid, int x) {
        String query = "SELECT x FROM audioleap WHERE uuid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setInt(2, x);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public static int getY(UUID uuid, int y) {
        String query = "SELECT y FROM audioleap WHERE uuid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setInt(3, y);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return y;
    }

    public static int getZ(UUID uuid, int z) {
        String query = "SELECT z FROM audioleap WHERE uuid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setInt(4, z);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return z;
    }

    public static boolean getRequest(UUID uuid, boolean request) {
        String query = "SELECT request FROM audioleap WHERE uuid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setBoolean(5, request);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static boolean setPlaysoundRequest(UUID uuid, boolean request) {
        String query = "UPDATE audioleap SET playreq = ? WHERE uuid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setBoolean(6, request);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static boolean setRequest(UUID uuid, boolean request) {
        String query = "UPDATE audioleap SET request = ? WHERE uuid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(5, request);
            statement.setString(1, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static boolean getPlaysoundRequest(UUID uuid, boolean request) {
        String query = "SELECT playreq FROM audioleap WHERE uuid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setBoolean(6, request);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static void setSound(UUID uuid, String sound, int pitch, int volume) {
        String query = "INSERT INTO audioleap (uuid, sound, pitch, volume) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setString(7, sound);
            statement.setInt(8, volume);
            statement.setInt(9, pitch);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getSound(UUID uuid) {
        String sound = null;
        String query = "SELECT sound FROM audioleap WHERE uuid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setString(7, sound);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sound;
    }

    public static int getVolume(UUID uuid) {
        int volume = 0;
        String query = "SELECT volume FROM audioleap WHERE uuid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setInt(8, volume);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return volume;
    }

    public static int getPitch(UUID uuid) {
        int pitch = 0;
        String query = "SELECT pitch FROM audioleap WHERE uuid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.setInt(9, pitch);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pitch;
    }

}
