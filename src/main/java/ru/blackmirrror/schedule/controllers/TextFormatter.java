package ru.blackmirrror.schedule.controllers;

public class TextFormatter {

    public String getTimeByPair(int pair) {
        switch (pair) {
            case 1: return "09:00-10:30";
            case 2: return "10:40-12:10";
            case 3: return "12:40-14:10";
            case 4: return "14:20-15:50";
            case 5: return "16:20-17:50";
            case 6: return "18:00-19:30";
            default: return "19:40-21:00";
        }
    }

    public String getDayByNumber(int number) {
        switch (number) {
            case 1: return "Понедельник";
            case 2: return "Вторник";
            case 3: return "Среда";
            case 4: return "Четверг";
            case 5: return "Пятница";
            default: return "Суббота";
        }
    }
}
