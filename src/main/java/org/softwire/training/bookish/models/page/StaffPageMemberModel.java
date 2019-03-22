package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Member;

import java.util.List;

public class StaffPageMemberModel {
    private List<Member> memberList;


    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }
}
