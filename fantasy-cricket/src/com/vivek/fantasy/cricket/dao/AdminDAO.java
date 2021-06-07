package com.vivek.fantasy.cricket.dao;

import com.vivek.fantasy.cricket.database.Database;
import com.vivek.fantasy.cricket.domain.Admin;

import java.util.Set;

public class AdminDAO {

    public Set<Admin> getAllAdmins() {
        return Set.copyOf(Database.ADMINS.values());
    }

    public Admin getAdmin(String id) {
        return Database.ADMINS.get(id);
    }

    public void addAdmin(Admin admin) {
        Database.ADMINS.put(admin.getId(), admin);
    }

    public void removeAdmin(String id) {
        Database.ADMINS.remove(id);
    }

}
