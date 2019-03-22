package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.database.Technology;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService extends DatabaseService {

    public List<Member> getMembers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM library_members")
                        .mapToBean(Member.class)
                        .list()
        );
    }



}
