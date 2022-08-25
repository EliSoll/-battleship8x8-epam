package com.epam.rd.autotasks;



public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
int indexString = 0;
        String[][] shotArray = new String[][]{{"A1-0 ", "B1-1 ", "C1-2 ", "D1-3 ", "E1-4 ", "F1-5 ", "G1-6 ", "H1-7 "},
                {"A2-8 ", "B2-9 ", "C2-10", "D2-11", "E2-12", "F2-13", "G2-14", "H2-15"},
                {"A3-16", "B3-17", "C3-18", "D3-19", "E3-20", "F3-21", "G3-22", "H3-23"},
                {"A4-24", "B4-25", "C4-26", "D4-27", "E4-28", "F4-29", "G4-30", "H4-31"},
                {"A5-32", "B5-33", "C5-34", "D5-35", "E5-36", "F5-37", "G5-38", "H5-39"},
                {"A6-40", "B6-41", "C6-42", "D6-43", "E6-44", "F6-45", "G6-46", "H6-47"},
                {"A7-48", "B7-49", "C7-50", "D7-51", "E7-52", "F7-53", "G7-54", "H7-55"},
                {"A8-56", "B8-57", "C8-58", "D8-59", "E8-60", "F8-61", "G8-62", "H8-63"}};
        for (int i = 0; i < shotArray.length; i++) {
            for (int j = 0; j < shotArray[0].length; j++) {
                if(shotArray[i][j].contains(shot)) {
                    indexString = Integer.parseInt(shotArray[i][j].substring(3).trim());
                    shotArray[i][j] = "1";
                }
                else {
                    shotArray[i][j] = "0";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shotArray.length; i++) {
            for (int j = 0; j < shotArray[0].length; j++) {
                sb.append(shotArray[i][j]);
            }
        }
        String shotStr = sb.toString();
        shots += Long.parseUnsignedLong(shotStr, 2);
        String str = Long.toBinaryString(shots);
        str = padLeftZeros(str, 64);
        String check = getMap();
        return String.valueOf(check.charAt(indexString)).contains("☐");
    }

    public String state() {
        String str = Long.toBinaryString(shots);
        str = padLeftZeros(str, 64);
        String check = getMap();

        for (int i = 0; i < check.length(); i++) {
            boolean contains = String.valueOf(str.charAt(i)).contentEquals("1");
            if(String.valueOf(check.charAt(i)).contentEquals("☐") && contains) {
                check = check.substring(0,i) + "☒" + check.substring(i+1);
            }
            else if(String.valueOf(check.charAt(i)).contentEquals(".") && contains) {
                check = check.substring(0,i) + "×" + check.substring(i+1);
            }

        }
        check = "" + check.substring(0,8) + System.lineSeparator() + check.substring(8,16) + System.lineSeparator() + check.substring(16,24) + System.lineSeparator() + check.substring(24,32) + System.lineSeparator() + check.substring(32,40) + System.lineSeparator() + check.substring(40,48) + System.lineSeparator() + check.substring(48,56) + System.lineSeparator() + check.substring(56);
return check;

    }


    public String getMap() {
        String map = Long.toBinaryString(ships);
        map = padLeftZeros(map, 64);

        map = map.replaceAll("1", "☐");
        map = map.replaceAll("0", ".");
        return map;
    }
    public static String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }
    }

