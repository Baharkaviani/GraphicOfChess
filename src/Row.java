public enum Row {
    A, B, C, D, E, F, G, H;

    public static int valueOf(char charAt) {
        switch (charAt) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
        }
        return -1;
    }
}
