import java.io.*;
import java.util.*;

public class Main {
    private static FastScanner scanner;
    private static Main solver;

    public static void main(String[] args) {
        solver = new Main();
        scanner = solver.new FastScanner();
        try {
            solver.solve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void solve() {
        System.out.println();
    }

    private class FastScanner implements Closeable {
        private InputStream inputStream = System.in;
        private byte[] buffer = new byte[1024];
        private int pointer = 0;
        private int bufferLength = 0;
        private boolean[] isSpace = new boolean[128];

        public FastScanner() {
            this(System.in);
        }

        public FastScanner(InputStream inputStream, char...spaces) {
            this.setSpace(spaces);
            this.inputStream = inputStream;
        }

        public FastScanner(String string, char...spaces) {
            this.setSpace(spaces);
            this.buffer = string.getBytes();
            this.bufferLength = this.buffer.length;
        }

        public void setSpace(char...spaces) {
            Arrays.fill(isSpace, false);
            isSpace[' '] = isSpace['\n'] = isSpace['\r'] = isSpace['\t'] = true;
            for (char space : spaces) isSpace[space] = true;
        }

        @Override
        public void close() {
            try {
                if (this.inputStream != null) this.inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int read() {
            if (this.bufferLength == -1) return -1;
            if (this.pointer >= this.bufferLength) {
                this.pointer = 0;
                if (this.inputStream == null) return this.bufferLength = -1;
                try {
                    this.bufferLength = this.inputStream.read(this.buffer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (this.bufferLength <= 0) return -1;
            }
            return this.buffer[this.pointer++];
        }

        public int peek() {
            int c = this.read();
            if (c >= 0) this.pointer--;
            return c;
        }

        public boolean hasNext() {
            int c = this.read();
            while (c >= 0 && this.isSpace[c]) c = this.read();
            if (c == -1) return false;
            this.pointer--;
            return true;
        }

        public boolean hasNextInLine() {
            if (this.pointer > 0 && this.buffer[this.pointer - 1] == '\n') return false;
            int c = this.read();
            while (c >= 0 && this.isSpace[c] && c != '\n' && c != '\r') c = this.read();
            if (c == -1) return false;
            this.pointer--;
            return c != '\n' && c != '\r';
        }

        public void skipLine() {
            if (this.pointer > 0 && this.buffer[this.pointer - 1] == '\n') {
                this.buffer[this.pointer - 1] = ' ';
                return;
            }
            int c = this.read();
            if (c < 0) return;
            while (c >= 0 && c != '\n' && c != '\r') {
                c = this.read();
            }
            if (c == '\r') this.read();
            if (this.pointer > 0) this.buffer[this.pointer - 1] = ' ';
        }

        public String next() {
            if (!this.hasNext()) throw new InputMismatchException();
            StringBuilder sb = new StringBuilder();
            int c = this.read();
            while (c >= 0 && !this.isSpace[c]) {
                sb.append((char)c);
                c = this.read();
            }
            return sb.toString();
        }

        public String nextLine() {
            return this.nextLine(true);
        }

        public String nextLine(boolean skipLine) {
            if (skipLine) this.skipLine();
            StringBuilder sb = new StringBuilder();
            if (this.pointer > 0 && this.buffer[this.pointer - 1] == '\n') {
                this.buffer[this.pointer - 1] = ' ';
                return "";
            }
            int c = this.read();
            if (c < 0) throw new InputMismatchException();
            while (c >= 0 && c != '\n' && c != '\r') {
                sb.append((char)c);
                c = this.read();
            }
            if (c == '\r') this.read();
            if (this.pointer > 0) this.buffer[this.pointer - 1] = ' ';
            return sb.toString();
        }

        public int nextChar(){
            if (!this.hasNext()) throw new InputMismatchException();
            int c = 0;
            do {
                c = this.read();
            } while (this.isSpace[c]);
            return c;
        }

        public int nextChars(char[] chars) {
            return this.nextChars(chars, true);
        }

        public int nextChars(char[] chars, boolean skipLine) {
            if (skipLine) this.skipLine();
            int l = 0;
            while (l < chars.length && this.hasNextInLine()) {
                chars[l++] = (char) this.nextChar();
            }
            if (this.hasNextInLine()) return -1;
            this.skipLine();
            return l;
        }

        public int nextInt() {
            if (!this.hasNext()) throw new InputMismatchException();
            int c = this.read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = this.read();
            }
            int acc = 0;
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                acc *= 10;
                acc += c - '0';
                c = this.read();
            } while (c >= 0 && !this.isSpace[c]);
            return acc * sgn;
        }

        public int nextInts(int[] ints) {
            return this.nextInts(ints, true);
        }

        public int nextInts(int[] ints, boolean skipLine) {
            if (skipLine) this.skipLine();
            int l = 0;
            while (l < ints.length && this.hasNextInLine()) {
                ints[l++] = this.nextInt();
            }
            if (this.hasNextInLine()) return -1;
            this.skipLine();
            return l;
        }

        public long nextLong() {
            if (!this.hasNext()) throw new InputMismatchException();
            int c = this.read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = this.read();
            }
            long acc = 0;
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                acc *= 10;
                acc += c - '0';
                c = this.read();
            } while (c >= 0 && !this.isSpace[c]);
            return acc * sgn;
        }

        public int nextLongs(long[] longs) {
            return this.nextLongs(longs, true);
        }

        public int nextLongs(long[] longs, boolean skipLine) {
            if (skipLine) this.skipLine();
            int l = 0;
            while (l < longs.length && this.hasNextInLine()) {
                longs[l++] = this.nextLong();
            }
            if (this.hasNextInLine()) return -1;
            this.skipLine();
            return l;
        }

        public double nextDouble() {
            return Double.parseDouble(this.next());
        }

        public int nextDoubles(double[] doubles) {
            return this.nextDoubles(doubles, true);
        }

        public int nextDoubles(double[] doubles, boolean skipLine) {
            if (skipLine) this.skipLine();
            int l = 0;
            while (l < doubles.length && this.hasNextInLine()) {
                doubles[l++] = this.nextDouble();
            }
            if (this.hasNextInLine()) return -1;
            this.skipLine();
            return l;
        }
    }
}