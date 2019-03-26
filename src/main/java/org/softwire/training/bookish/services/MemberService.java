package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.database.Technology;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService extends DatabaseService {

    public List<Member> getMembers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM library_members")
                        .mapToBean(Member.class)
                        .list()
        );
    }


    public void addMember(Member member) {
        jdbi.useHandle(handle ->
                // TODO
                handle.createUpdate("INSERT INTO library_members (forename, surname, librarian) VALUES (:forename, :surname, :librarian)")
                        .bind("forename", member.getForename())
                        .bind("surname", member.getSurname())
                        .bind("librarian", member.isLibrarian())
                        .execute()
        );
    }

    public void editMember(Member member){
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE library_members SET forename = :forename, surname = :surname, librarian = :librarian WHERE member_id = :id")
                        .bind("forename", member.getForename())
                        .bind("surname", member.getSurname())
                        .bind("librarian", member.isLibrarian())
                        .bind("id", member.getMemberId())
                        .execute()
        );
    }

    public void deleteMember(int memberId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM library_members WHERE id = :id")
                        .bind("id", memberId)
                        .execute()
        );
    }


}
