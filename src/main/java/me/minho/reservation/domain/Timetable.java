package me.minho.reservation.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Timetable {
    private Map<LocalDateTime, Boolean> table;

    private Timetable(Map<LocalDateTime, Boolean> table) {
        this.table = table;
    }

    public static Timetable of(Map<LocalDateTime, Boolean> table) {
        return new Timetable(table);
    }
}
