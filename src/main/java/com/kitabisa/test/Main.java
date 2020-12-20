package com.kitabisa.test;

public class Main {

    public static void main(String[] args) {
        System.out.println(eval(args[0]));
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean skip(int charToSkip) {
                while (ch == ' ') nextChar();
                if (ch == charToSkip) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Validation Error: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = calc();
                for (;;) {
                    if      (skip('+')) x += calc(); // addition
                    else if (skip('-')) x -= calc(); // subtraction
                    else return x;
                }
            }

            double calc() {
                if (skip('+')) return calc(); // unary plus
                if (skip('-')) return -calc(); // unary minus

                double x;
                int startPos = this.pos;
                if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Validation Error: " + (char)ch);
                }
                return x;
            }
        }.parse();
    }
}
