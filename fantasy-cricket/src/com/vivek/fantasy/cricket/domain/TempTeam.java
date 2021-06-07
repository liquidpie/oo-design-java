package com.vivek.fantasy.cricket.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TempTeam {

    private int userBudget;

    private final Set<Member> members;

    public TempTeam(int userBudget) {
        this.userBudget = userBudget;
        members = new HashSet<>();
    }

    public void addPlayer(String playerId, int credit) {
        members.add(new Member(playerId, credit));
        userBudget -= credit;
    }

    // Todo add remove method

    public int getUserBudget() {
        return userBudget;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public static final class Member {
        String playerId;
        int credit;

        public Member(String playerId, int credit) {
            this.playerId = playerId;
            this.credit = credit;
        }

        public String getPlayerId() {
            return playerId;
        }

        public int getCredit() {
            return credit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Member member = (Member) o;
            return credit == member.credit && Objects.equals(playerId, member.playerId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(playerId, credit);
        }
    }



}
